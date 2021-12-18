package io.unity.methods;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class text_box_methods {


    JavaClassSource java_class;

    public text_box_methods(JavaClassSource java_class) {
        this.java_class = java_class;
    }

    public void enter_text(String key_name)
    {
        java_class.addMethod().setName("enter_text_at_"+key_name+"_text_box").setBody("element.find(reader.get_locator_value(\""+key_name+"\")).sendKeys(text_to_enter);").setParameters("String text_to_enter");

    }

    public void clear_text_box(String key_name)
    {
        java_class.addMethod().setName("clear_text_from_"+key_name+"_text_box").setBody("element.find(reader.get_locator_value(\""+key_name+"\")).clear();");

    }

    public void clear_and_enter_text_box(String key_name)
    {
        java_class.addMethod().setName("clear_text_and_enter_text_in_"+key_name+"_text_box").setBody("element.clear_and_enter_in_text_field(reader.get_locator_value(\""+key_name+"\"),text_to_enter);").setParameters("String text_to_enter");
    }



    public void verify_text_box_is_present(String key_name)
    {
        java_class.addMethod().setName("verify_"+key_name+"_text_box_is_present_on_page").setBody("Assert.assertTrue(verify.element_is_present(reader.get_locator_value(\""+key_name+"\")));");
    }


}
