package com.bracits.snowflake.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.Id;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
public class Person {

    @Id
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
