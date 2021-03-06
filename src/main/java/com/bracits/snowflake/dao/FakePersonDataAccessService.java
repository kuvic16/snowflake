package com.bracits.snowflake.dao;

import com.bracits.snowflake.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Polash on 9/8/2020.
 */
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(String id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(String id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(String id) {
        Optional<Person> person = selectPersonById(id);
        if(person.isPresent()){
            DB.remove(person.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(String id, Person person) {
        return selectPersonById(id).map(p -> {
            int index = DB.indexOf(p);
            if(index >= 0) {
                DB.set(index, new Person(id, person.getName()));
                return  1;
            }
            return 0;
        }).orElse(0);
    }
}
