package io.unity;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class class_builder {


    public void build_object_repo_class(String location) {
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage("io.unity.object_repository.Web.login").setName("login_page_steps");

       create_class_object class_object = new create_class_object(javaClass);

        class_object.create_driver_class_variable();





        javaClass.addField()
                .setName("reader")
                .setType("locator_reader")
                .setLiteralInitializer("new locator_reader()");


        javaClass.addMethod().setConstructor(true).setParameters("WebDriver driver").setBody("this.driver = driver;\n" +
                "        element = new Element(driver);\n" +
                "        verify = new Verify(driver);").setPublic();


        System.out.println(javaClass);


        javaClass.addMethod().setBody("element.find(reader.get_locator_value(\"email_text_box\")).sendKeys(text_to_enter);");






    }

}
