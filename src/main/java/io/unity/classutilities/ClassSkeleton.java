package io.unity.classutilities;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.pmw.tinylog.Logger;

import javax.annotation.CheckForNull;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassSkeleton {

    JavaClassSource java_class;

    public ClassSkeleton(JavaClassSource java_class) {
        this.java_class = java_class;
    }

    public String get_package_name(String destination_path) {
        String package_path = destination_path.replace("/", ".").replace("\\",".");
        return "web.object_repository" + package_path.split("web.object_repository")[1];
    }


    public void create_skeleton(String destination_path, String className) {

        String package_name = get_package_name(destination_path);
        Logger.info("Setting up the package name :" + package_name);
        java_class.setPackage(package_name).setName(className);


        java_class.addImport("io.unity.autoweb.Element");
        java_class.addImport("io.unity.autoweb.Verify");
        java_class.addImport("org.openqa.selenium.WebDriver");
        java_class.addImport("org.testng.Assert");

        Logger.info("Adding Required class Imports :" + java_class.getImports());
        Logger.info("Adding Constructor");
        java_class.addMethod().setConstructor(true).setParameters("WebDriver driver").setBody("this.driver = driver;\n" +
                "        element = new Element(driver);\n" +
                "        verify = new Verify(driver);").setPublic();


        ClassVariableBuilder class_object = new ClassVariableBuilder(java_class);

        class_object.create_driver_class_variable();
        class_object.create_element_class_variable();
        class_object.create_verify_class_variable();


    }

    @CheckForNull
    public void write_java_file(String destination_path, JavaClassSource java_class) {
        Logger.info("Creating a Java File on :" + destination_path);

        try (FileWriter fw = new FileWriter(destination_path)) {

            fw.write(java_class.toString());

        } catch (IOException e) {
            e.printStackTrace();
            Logger.error(e);
        }


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null,
                destination_path);

        move_file(destination_path);
    }

    public void move_file(String java_file) {

        String source_class_file = java_file.replace(".java", ".class");
        String destination_file_path = source_class_file.replace("/src/test/java", "/target/test-classes");


        while (!new File(source_class_file).exists()) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Logger.info(e);
                Thread.currentThread().interrupt();
            }
        }

        Path temp = null;
        try {
            Path path = Paths.get(destination_file_path);
            Files.createDirectories(path.getParent());

            temp = Files.move
                    (Paths.get(source_class_file),
                            Paths.get(destination_file_path));
        } catch (FileAlreadyExistsException e) {
            try {
                Files.delete(Paths.get(source_class_file));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (temp != null) {
            Logger.info("File renamed and moved successfully");
        }
    }


}
