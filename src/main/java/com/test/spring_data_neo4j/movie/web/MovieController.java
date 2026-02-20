package com.test.spring_data_neo4j.movie.web;


import com.test.spring_data_neo4j.movie.Movie;
import com.test.spring_data_neo4j.movie.MovieService;
import com.test.spring_data_neo4j.movie.Role;
import com.test.spring_data_neo4j.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Movie movie) {
        service.create(movie);
    }

    @PostMapping("/{title}/roles")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addRoleToMovie(@PathVariable String title, @RequestBody Role role) {
        service.addRoleToMovie(title, role);
    }

    @PostMapping("/{title}/directors")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addDirectorToMovie(@PathVariable String title, @RequestBody Person person) {
        service.addDirectorToMovie(title, person);
    }
}
