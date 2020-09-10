package com.bracits.snowflake.service;

import com.bracits.snowflake.dao.PersonDao;
import com.bracits.snowflake.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Polash on 9/8/2020.
 */
@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(String id) {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(String id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(String id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }
}
