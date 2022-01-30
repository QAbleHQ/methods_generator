package io.unity;

import io.unity.class_utility.ClassVariableBuilder;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

public class ClassBuilder {


    public JSONObject read_locator_file_and_get_object(String file_path) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {

            json = (JSONObject) parser.parse(new String(Files.readAllBytes(Paths.get(file_path).toAbsolutePath())));

            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }








}


