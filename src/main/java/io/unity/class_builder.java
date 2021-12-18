package io.unity;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class class_builder {


    public void build_object_repo_class(String className, String folder_to_generate, JSONObject locator_file_object) {

        final JavaClassSource java_class = Roaster.create(JavaClassSource.class);

        java_class.setPackage("io.unity.object_repository.Web.login").setName(className);

        java_class.addImport("io.unity.framework.readers.locator_reader;");
        java_class.addImport("io.unity.autoweb.Element;");
        java_class.addImport("io.unity.autoweb.Verify;");
        java_class.addImport("org.openqa.selenium.WebDriver;");
        java_class.addImport("org.testng.Assert;");





        java_class.addMethod().setConstructor(true).setParameters("WebDriver driver").setBody("this.driver = driver;\n" +
                "        element = new Element(driver);\n" +
                "        verify = new Verify(driver);").setPublic();

        create_class_object class_object = new create_class_object(java_class);
        locator_identifier identifier = new locator_identifier(java_class);

        class_object.create_driver_class_variable();
        class_object.create_element_class_variable();
        class_object.create_verify_class_variable();
        class_object.create_locator_reader_class_object();


        Set<String> keys = locator_file_object.keySet();

        Iterator value = keys.iterator();


        while (value.hasNext()) {
            String key_name = (String) value.next();
            JSONObject single_object = (JSONObject) locator_file_object.get(key_name);
            identifier.identify_locator_and_generate_object(key_name, single_object);

        }


        System.out.println(java_class);

    }

}
