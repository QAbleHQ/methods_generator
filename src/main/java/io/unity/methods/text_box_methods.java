package io.unity.methods;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class text_box_methods {


    JavaClassSource java_class;

    public text_box_methods(JavaClassSource java_class) {
        this.java_class = java_class;
    }

    public void enter_text(String key_name)
    {
        java_class.addMethod().setName("enter_text_at_"+key_name).setBody("element.enter_text(\""+key_name+"\",text_to_enter);").setParameters("String text_to_enter").setPublic();

    }

    public void clear_text_box(String key_name)
    {
        java_class.addMethod().setName("clear_text_from_"+key_name).setBody("element.clear_text_field(\""+key_name+"\");\n").setPublic();

    }

    public void clear_and_enter_text_box(String key_name)
    {
        java_class.addMethod().setName("clear_text_and_enter_text_in_"+key_name).setBody("element.clear_and_enter_in_text_field(\""+key_name+"\",text_to_enter);").setParameters("String text_to_enter").setPublic();
    }



    public void verify_text_box_is_present(String key_name)
    {
        java_class.addMethod().setName("verify_"+key_name+"_is_present_on_page").setBody("Assert.assertTrue(verify.element_is_present(\""+key_name+"\"));").setPublic();
    }


}
