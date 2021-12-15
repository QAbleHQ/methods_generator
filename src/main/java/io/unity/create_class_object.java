package io.unity;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class create_class_object {

    JavaClassSource javaClass;

    create_class_object(JavaClassSource javaClass) {
        this.javaClass = javaClass;
    }


    public void create_driver_class_variable() {

        javaClass.addField()
                .setName("driver")
                .setType("WebDriver")
                .setLiteralInitializer("null");
    }

    public void create_element_class_variable() {

        javaClass.addField()
                .setName("element")
                .setType("Element")
                .setLiteralInitializer("null");
    }

    public void create_verify_class_variable() {

        javaClass.addField()
                .setName("verify")
                .setType("Verify")
                .setLiteralInitializer("null");
    }


}
