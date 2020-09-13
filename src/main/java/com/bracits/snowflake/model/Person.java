package com.bracits.snowflake.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;


import java.util.UUID;

/**
 * Created by Polash on 9/8/2020.
 */
public class Person {

    private final String id;
    private final String name;

    public Person(@JsonProperty("id") String id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
