package io.unity.discard;

import io.unity.methods.ButtonMethods;
import io.unity.methods.TextBoxMethods;
import io.unity.methods.TextMethods;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;

public class LocatorIdentifier {

    JavaClassSource java_class;

    public LocatorIdentifier(JavaClassSource java_class) {
        this.java_class = java_class;
    }



    public void identify_locator_and_generate_methods(String key_name, JSONObject single_object) {

        TextBoxMethods text_box_method = new TextBoxMethods(java_class);
        ButtonMethods button_methods = new ButtonMethods(java_class, key_name);
        TextMethods text_methods = new TextMethods(java_class, key_name);

        String element_type = (String) single_object.get("element_type");


        if (element_type.equals("text_box")) {

            text_box_method.enter_text(key_name);
            text_box_method.clear_text_box(key_name);
            text_box_method.verify_text_box_is_present(key_name);
            text_box_method.clear_and_enter_text_box(key_name);

        } else if (element_type.equals("email")) {
            /*
            * Common Methods of the text Box
            * */
            text_box_method.enter_text(key_name);
            text_box_method.clear_text_box(key_name);
            text_box_method.verify_text_box_is_present(key_name);
            text_box_method.clear_and_enter_text_box(key_name);

            /* TODO()
            * Add Some Email Related Methods
            * */


        } else if (element_type.equals("password")) {
            /*
             * Common Methods of the text Box
             * */
            text_box_method.enter_text(key_name);
            text_box_method.clear_text_box(key_name);
            text_box_method.verify_text_box_is_present(key_name);
            text_box_method.clear_and_enter_text_box(key_name);

            /* TODO()
             * Add Some Password Related Methods
             * */


        } else if (element_type.equals("button")) {
            button_methods.verify_button_is_present();
            button_methods.click_on_button();
            button_methods.verify_button_is_clickable();
            button_methods.verify_button_text();

        } else if (element_type.equals("check_box")) {


        } else if (element_type.equals("text")) {
            text_methods.verify_text();


        } else if (element_type.equals("radio")) {

        } else if (element_type.equals("text_area")) {

        } else if (element_type.equals("drop_down")) {

        } else if (element_type.equals("file")) {

        } else if (element_type.equals("link")) {

        }


    }






}
