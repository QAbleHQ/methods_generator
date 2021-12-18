package io.unity;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;

public class object_generator {


    public static void main(String[] args) {
        class_builder builder = new class_builder();

        JSONParser parser = new JSONParser();
        try {
           JSONObject jsonObject = (JSONObject) parser.parse("{\n" +
                    "\"email\" : {\n" +
                    "  \"platform\" : \"web\",\n" +
                    "  \"element_type\" : \"text_box\",\n" +
                    "  \"locator_type\" : \"xpath\",\n" +
                    "  \"locator_value\" : \"//input[@name='email']\",\n" +
                    "  \"objectGenerate\" : \"generate\"\n" +
                    "},\n" +
                    "\n" +
                    "  \"password\" : {\n" +
                    "    \"platform\" : \"web\",\n" +
                    "    \"element_type\" : \"password\",\n" +
                    "    \"locator_type\" : \"xpath\",\n" +
                    "    \"locator_value\" : \"//input[@name='pwd']\",\n" +
                    "    \"objectGenerate\" : \"generate\"\n" +
                    "  }\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "}");

            builder.build_object_repo_class("Testing","Testing",jsonObject);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
