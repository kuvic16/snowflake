package com.bracits.snowflake.dao;

import com.bracits.snowflake.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Polash on 9/8/2020.
 */
public interface PersonDao {
    int insertPerson(String id, Person person);

    default int insertPerson(Person person) {
        String id = UUID.randomUUID().toString();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(String id);

    int deletePersonById(String id);

    int updatePersonById(String id, Person person);
}
