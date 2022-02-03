package io.unity.classutilities;

import io.unity.methods.LocatorType;
import io.unity.methods.MethodsData;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassMethodsValidator {
    /*
     *
     *
     * */
    public static void main(String[] args) {
        ClassMethodsValidator validator = new ClassMethodsValidator();
        validator.prepare_list_of_element_not_generated("/Users/viralpatel/Viral/object_repository_generator/src/test/java/web/object_repository/login_page/registration/search_page.json", "web.object_repository.login_page.registration.search_page_steps");
    }

    public static void deleteDirectory(File file) {

        try {
            Files.delete(Paths.get(file.getAbsolutePath()));
        } catch (Exception e) {
        }
    }

    public List<String> look_for_locator_json_file(String folder_path) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(folder_path))) {
            // We want to find only regular files
            result = walk.filter(Files::isRegularFile).filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(x -> x.toString()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String create_java_class_file_path(String json_file_path) {
        File file = new File(json_file_path);
        String java_class_file_path = file.getParent() + "/" + FilenameUtils.removeExtension(file.getName()) + ".java";
        return java_class_file_path;
    }

    public String create_java_class_name_without_extension(String json_file_path) {
        File file = new File(json_file_path);
        String java_class_file_path = FilenameUtils.removeExtension(file.getName());
        return java_class_file_path;
    }

    public boolean check_java_class_is_exist(String file_path) {
        return new File(file_path).exists();
    }

    public Map<String, List> prepare_list_of_element_not_generated(String json_file_path, String java_class_name) {
        Map<String, List> pending_method_list = new HashMap<String, List>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject whole_file = (JSONObject) parser.parse(new FileReader(json_file_path));

            Set<String> whole_file_set = whole_file.keySet();
            Iterator<String> iterator = whole_file_set.iterator();

            Path targetPath = Paths.get(getClass().getResource("/").toURI()).getParent();

            deleteDirectory(new File(targetPath + "/classes/web"));
            copyDirectory(targetPath + "/test-classes", targetPath + "/classes");


            while (iterator.hasNext()) {
                String locator_name = (String) iterator.next();
                String locator_type = ((JSONObject) whole_file.get(locator_name)).get("element_type").toString();

                Logger.info("Checking for missing methods for locator : " + locator_name);

                pending_method_list.put(locator_name, prepare_unavailable_method_tag_list(locator_type, java_class_name, locator_name));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return pending_method_list;
    }

    public List prepare_unavailable_method_tag_list(String locator_type, String java_class_name, String locator_name) {

        //Already declared Method list
        ArrayList available_method_tag = prepare_list_of_available_methods_for_single_locator(java_class_name, locator_name);


        Class cls = null;
        try {
            cls = look_up_class(locator_type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] m = cls.getMethods();

        ArrayList expected_method_tag_list = new ArrayList();

        for (int i = 0; i < m.length; i++) {


            if (m[i].isAnnotationPresent(MethodsData.class)) {
                MethodsData abc = m[i].getAnnotation(MethodsData.class);
                expected_method_tag_list.add(abc.method_id());

            }

        }

        List<String> result = (List<String>) expected_method_tag_list.stream()
                .filter(Predicate.not(new HashSet<>(available_method_tag)::contains))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            Logger.info("----- > No missing Method for locator" + locator_name);
        } else {
            Logger.info("Missing Method for " + locator_name + " are :" + result);
        }
        return result;
    }

    public Class look_up_class(String locator_type_name) throws ClassNotFoundException {
        Class cls = null;

        if (locator_type_name.equals("button")) {
            cls = Class.forName(LocatorType.BUTTON);
        }
        if (locator_type_name.equals("link")) {
            cls = Class.forName(LocatorType.LINK);
        }
        if (locator_type_name.equals("text_box")) {
            cls = Class.forName(LocatorType.TEXT_BOX);
        }
        if (locator_type_name.equals("check_box")) {
            cls = Class.forName(LocatorType.CHECK_BOX);
        }
        if (locator_type_name.equals("text")) {
            cls = Class.forName(LocatorType.TEXT);
        }
        if (locator_type_name.equals("email")) {
            cls = Class.forName(LocatorType.EMAIL);
        }
        if (locator_type_name.equals("password")) {
            cls = Class.forName(LocatorType.PASSWORD);
        }

        if (locator_type_name.equals("radio")) {
            cls = Class.forName(LocatorType.RADIO);
        }
        if (locator_type_name.equals("text_area")) {
            cls = Class.forName(LocatorType.TEXT_AREA);
        }

        if (locator_type_name.equals("drop_down")) {
            cls = Class.forName(LocatorType.DROP_DOWN);
        }
        if (locator_type_name.equals("file")) {
            cls = Class.forName(LocatorType.FILE);
        }

        return cls;
    }

    public ArrayList prepare_list_of_available_methods_for_single_locator(String java_class_name, String locator_name) {
        Class cls = null;
        try {
            cls = Class.forName(java_class_name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] m = cls.getMethods();

        ArrayList method_tag_list = new ArrayList();

        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().contains(locator_name)) {

                if (m[i].isAnnotationPresent(MethodsData.class)) {
                    MethodsData abc = m[i].getAnnotation(MethodsData.class);
                    method_tag_list.add(abc.method_id());
                }
            }
        }

        return method_tag_list;
    }

    public void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) {
        try {
            Files.walk(Paths.get(sourceDirectoryLocation))
                    .forEach(source -> {
                        Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                                .substring(sourceDirectoryLocation.length()));

                        try {
                            Files.copy(source, destination);
                        } catch (FileAlreadyExistsException e) {

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
