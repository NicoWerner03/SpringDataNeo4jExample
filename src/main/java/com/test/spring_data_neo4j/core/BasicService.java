package com.test.spring_data_neo4j.core;

import com.test.spring_data_neo4j.core.exceptions.NotFoundException;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BasicService<T, ID> {

    protected abstract Neo4jRepository<T, ID> getRepository();

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public ResponseEntity<T> findById(ID id) {
        if (getRepository().findById(id).isPresent()) {
            return ResponseEntity.ok(getRepository().findById(id).get());
        } else  {
            throw new NotFoundException("Entity not found");
        }
    }

    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(getRepository().findAll());
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    public Boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

}
