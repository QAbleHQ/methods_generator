package io.unity;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.Serializable;

public class object_generator {


    public static void main(String[] args) {
        class_builder builder = new class_builder();
        builder.build_object_repo_class("");
    }


}
