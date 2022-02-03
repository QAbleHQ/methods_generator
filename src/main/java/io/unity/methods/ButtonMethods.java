package io.unity.methods;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class ButtonMethods {


    JavaClassSource java_class;
    String key_name;
    String annotation_name = "io.unity.methods.MethodsData";
    private static final String METHOD_ID = "method_id";

    public ButtonMethods(JavaClassSource java_class, String key_name) {
        this.java_class = java_class;
        this.key_name = key_name;
    }

    @MethodsData(method_id = "button_1")
    public void verify_button_is_present() {
        java_class.addMethod().setName("verify_" + key_name + "_is_present_on_page").setBody(" verify.element_is_present(\"" + key_name + "\");").setPublic().addAnnotation(annotation_name).setStringValue(METHOD_ID, "button_1");
    }

    @MethodsData(method_id = "button_2")
    public void verify_button_is_clickable() {
        java_class.addMethod().setName("verify_" + key_name + "_is_clickable").setBody("verify.element_is_enable(\"" + key_name + "\");").setPublic().addAnnotation(annotation_name).setStringValue(METHOD_ID, "button_2");
    }

    @MethodsData(method_id = "button_3")
    public void click_on_button() {
        java_class.addMethod().setName("click_on_" + key_name).setBody("element.click(\"" + key_name + "\");").setPublic().addAnnotation(annotation_name).setStringValue(METHOD_ID, "button_3");
    }

    @MethodsData(method_id = "button_4")
    public void verify_button_text() {
        java_class.addMethod().setName("Verify_" + key_name + "_text_is_equal_to").setBody("verify.element_text_is_equal_to(\"" + key_name + "\",button_text);").setParameters("String button_text").setPublic().addAnnotation(annotation_name).setStringValue(METHOD_ID, "button_4");
    }

    @MethodsData(method_id = "button_5")
    public void verify_button_attribute_text() {
        java_class.addMethod()
                .setName("Verify_" + key_name + "_attribute_value_text_is_equal_to")
                .setBody("verify.element_attribute_is_equal_to(\"" + key_name + "\",attribute_name,expected_attribute_value);")
                .setParameters("String attribute_name,String expected_attribute_value")
                .setPublic().addAnnotation(annotation_name).setStringValue(METHOD_ID, "button_5");
    }

}
