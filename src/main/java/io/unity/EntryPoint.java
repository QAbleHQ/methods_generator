package io.unity;

import io.unity.class_utility.ClassMethodsValidator;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.parser.JSONParser;

import java.util.List;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        EntryPoint generator = new EntryPoint();
        generator.generate();
        //    System.out.println(  generator.check_java_class_is_exist(generator.create_java_class_name("/Users/viralpatel/Viral/object_repository_generator/src/test/java/web/object_repository/login_page/search_page.json")));
    }

    public void generate() {
        ClassBuilder builder = new ClassBuilder();
        ClassMethodsValidator validator = new ClassMethodsValidator();
        GenerateMethods methods = null;
        JavaFileReader file_reader = new JavaFileReader();

        JSONParser parser = new JSONParser();
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Locator file folder");

            String file_path = scanner.nextLine();

            List<String> total_files = validator.look_for_locator_json_file(file_path);

            for (String json_file_path : total_files) {

                JavaClassSource javaClass = null;
                ClassSkeleton skeleton = null;
                String java_class_file_path = validator.create_java_class_file_path(json_file_path);
                String only_class_name = validator.create_java_class_name_without_extension(json_file_path);

                if (!validator.check_java_class_is_exist(java_class_file_path)) {
                    javaClass = Roaster.create(JavaClassSource.class);
                    skeleton = new ClassSkeleton(javaClass);
                    skeleton.create_skeleton(file_path, only_class_name);
                } else {
                    javaClass =
                            Roaster.parse(JavaClassSource.class, file_reader.read_java_file(java_class_file_path));
                    skeleton = new ClassSkeleton(javaClass);
                }

                methods = new GenerateMethods(javaClass);

                methods.generate_missing_methods(json_file_path, skeleton.get_package_name(file_path) + "." + only_class_name);

                skeleton.write_java_file(java_class_file_path, javaClass);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
