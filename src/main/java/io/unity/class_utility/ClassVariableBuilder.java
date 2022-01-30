package io.unity.class_utility;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class ClassVariableBuilder {

    JavaClassSource javaClass;

  public ClassVariableBuilder(JavaClassSource javaClass) {
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

    public void create_locator_reader_class_object()
    {
        javaClass.addField()
                .setName("reader")
                .setType("locator_reader")
                .setLiteralInitializer("new locator_reader()");
    }


}
