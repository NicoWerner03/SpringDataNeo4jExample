package com.test.spring_data_neo4j.movie;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    Movie findByTitle(String title);
    Boolean existsByTitle(String title);
}
