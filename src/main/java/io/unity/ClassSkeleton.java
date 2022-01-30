package io.unity;

import io.unity.discard.LocatorIdentifier;
import io.unity.class_utility.ClassVariableBuilder;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

public class ClassSkeleton {

    JavaClassSource java_class;

    public ClassSkeleton(JavaClassSource java_class) {
        this.java_class = java_class;
    }

    public String get_package_name(String destination_path) {
        String package_path = destination_path.replace("/",".");
        return "web.object_repository" + package_path.split("web.object_repository")[1];
    }


    public void create_skeleton(String destination_path, String className) {

        java_class.setPackage(get_package_name(destination_path)).setName(className);

        //   java_class.addImport("io.unity.framework.readers.locator_reader");
        java_class.addImport("io.unity.autoweb.Element");
        java_class.addImport("io.unity.autoweb.Verify");
        java_class.addImport("org.openqa.selenium.WebDriver");
        java_class.addImport("org.testng.Assert");

        java_class.addMethod().setConstructor(true).setParameters("WebDriver driver").setBody("this.driver = driver;\n" +
                "        element = new Element(driver);\n" +
                "        verify = new Verify(driver);").setPublic();


        ClassVariableBuilder class_object = new ClassVariableBuilder(java_class);
        LocatorIdentifier identifier = new LocatorIdentifier(java_class);

        class_object.create_driver_class_variable();
        class_object.create_element_class_variable();
        class_object.create_verify_class_variable();
        //    class_object.create_locator_reader_class_object();


    }

    public void write_java_file(String destination_path, JavaClassSource java_class) {
        try {
            FileWriter fw = new FileWriter(destination_path);
            fw.write(java_class.toString());
            fw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Java File Generated SuccessFully...");
    }


}
