package com.test.spring_data_neo4j.person;

import com.test.spring_data_neo4j.core.BasicService;
import com.test.spring_data_neo4j.core.exceptions.BadRequestException;
import com.test.spring_data_neo4j.core.exceptions.ConflictException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BasicService<Person,Long> {

    @Getter
    @Autowired
    private PersonRepository repository;

    public void create(Person person) {

        if (person.getName() == null || person.getName().isBlank()) {
            throw new BadRequestException("name is required");
        }

        if (repository.existsByName(person.getName())) {
            throw new ConflictException("person already exists");
        }

        repository.save(person);

    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public Person findByName(String name) {
        return repository.findByName(name);
    }
}
