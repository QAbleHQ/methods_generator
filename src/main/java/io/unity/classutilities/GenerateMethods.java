package io.unity.classutilities;

import io.unity.methods.MethodsData;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.pmw.tinylog.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class GenerateMethods {

    JavaClassSource class_source;

    public GenerateMethods(JavaClassSource class_source) {
        this.class_source = class_source;
    }


    public void generate_missing_methods(String json_file_path, String java_class_name) {

        Logger.info("adding Methods into " + java_class_name);
        ClassMethodsValidator validator = new ClassMethodsValidator();

        Map<String, List> missing_method_locator_list = validator.prepare_list_of_element_not_generated(json_file_path, java_class_name);
        JSONParser parser = new JSONParser();
        JSONObject whole_file = null;
        try {
            whole_file = (JSONObject) parser.parse(new FileReader(json_file_path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, List> entry : missing_method_locator_list.entrySet()) {

            String locator_name = entry.getKey();

            String locator_type = ((JSONObject) whole_file.get(locator_name)).get("element_type").toString();

            List<String> method_id_list = entry.getValue();
            Class cls = null;
            Object t = null;
            Constructor<?> cons = null;
            try {
                cls = validator.look_up_class(locator_type);
                cons = cls.getConstructor(JavaClassSource.class, String.class);
                t = cons.newInstance(class_source, locator_name);
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                Logger.error(e);
            }
            Method[] m = cls.getMethods();


            for (int i = 0; i < m.length; i++) {

                if (m[i].isAnnotationPresent(MethodsData.class)) {
                    MethodsData abc = m[i].getAnnotation(MethodsData.class);

                    for (String method_id : method_id_list) {
                        if (method_id.equals(abc.method_id())) {
                            try {
                                Logger.info("Adding methods with tag" + method_id);

                                m[i].invoke(t);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }

                    }


                }

            }


        }

    }


}