package io.unity;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.util.Scanner;

public class object_generator {

    public static void main(String[] args) {
        object_generator generator = new object_generator();
        generator.generate();
    }
    public void generate() {
        class_builder builder = new class_builder();

        JSONParser parser = new JSONParser();
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Locator File Path");

            String file_path = scanner.nextLine();

            System.out.println("Enter The destination Folder path");
            String destination_path = scanner.nextLine();

            System.out.println("Enter Class Name (Without Any Extension)");
            String destination_class_name = scanner.nextLine();

            JavaClassSource java_class = builder.build_object_repo_class(destination_class_name, builder.read_locator_file_and_get_object(file_path));

            builder.write_java_file(destination_path+"/"+destination_class_name+".java",java_class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
