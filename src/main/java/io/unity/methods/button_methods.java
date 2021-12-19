package io.unity.methods;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public class button_methods {



    JavaClassSource java_class;

    public button_methods(JavaClassSource java_class) {
        this.java_class = java_class;
    }


    public void verify_button_is_present(String key_name)
    {
        java_class.addMethod().setName("verify_"+key_name+"_button_is_present_on_page").setBody("verify.element_is_present(reader.get_locator_value(\""+key_name+"\"));");
    }





}
