package com.bracits.snowflake.dao;

import com.bracits.snowflake.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Polash on 9/9/2020.
 */
@Repository("mysql")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(String id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "select id, `name` from person";
        return jdbcTemplate.query(sql , (resultSet, i) -> {
            return new Person(resultSet.getString("id"), resultSet.getString("name"));
        });

        //return jdbcTemplate.queryForList(sql , Person.class);

//        List<Person> list = new ArrayList<>();
//        list.add(new Person(UUID.randomUUID(), "From posgres db"));
//        return list;
    }

    @Override
    public Optional<Person> selectPersonById(String id) {
        final String sql = "select id, name from person where id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id} , (resultSet, i) -> {
            return new Person(resultSet.getString("id"), resultSet.getString("name"));
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(String id) {
        return 0;
    }

    @Override
    public int updatePersonById(String id, Person person) {
        return 0;
    }
}
