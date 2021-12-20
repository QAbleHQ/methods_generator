package io.unity.methods;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class button_methods {


    JavaClassSource java_class;
    String key_name;

    public button_methods(JavaClassSource java_class,String key_name) {
        this.java_class = java_class;
        this.key_name = key_name;
    }

    public void verify_button_is_present() {
        java_class.addMethod().setName("verify_" + key_name + "_button_is_present_on_page").setBody(" Assert.assertTrue(verify.element_is_present(\"" + key_name + "\"));").setPublic();
    }

    public void verify_button_is_clickable() {
        java_class.addMethod().setName("verify_" + key_name + "_button_is_clickable").setBody("Assert.assertTrue(verify.element_is_enable(\"" + key_name + "\"));").setPublic();
    }

    public void click_on_button() {
        java_class.addMethod().setName("click_on_" + key_name + "_button").setBody("element.click(\"" + key_name + "\");").setPublic();
    }

    public void verify_button_text()
    {
        java_class.addMethod().setName("click_on_" + key_name + "_button").setBody("verify.element_text_is_equal_to(\""+key_name+"\",button_text);").setParameters("String button_text").setPublic();
    }


}
