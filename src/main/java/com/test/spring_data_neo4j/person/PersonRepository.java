package com.test.spring_data_neo4j.person;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {
    boolean existsByName(String name);
    Person findByName(String name);
}
