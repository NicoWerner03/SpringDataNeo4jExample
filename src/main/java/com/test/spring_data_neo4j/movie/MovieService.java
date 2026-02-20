package com.test.spring_data_neo4j.movie;

import com.test.spring_data_neo4j.core.BasicService;
import com.test.spring_data_neo4j.core.exceptions.BadRequestException;
import com.test.spring_data_neo4j.core.exceptions.NotFoundException;
import com.test.spring_data_neo4j.person.Person;
import com.test.spring_data_neo4j.person.PersonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends BasicService<Movie, Long> {

    @Getter
    @Autowired
    private MovieRepository repository;

    @Autowired
    private PersonService personService;

    public void create(Movie request) {

       if (request.getTitle() == null || request.getTitle().equals("")) {
           throw new BadRequestException("Title is required");
       }

       if (request.getDescription() == null || request.getDescription().equals("")) {
           throw new BadRequestException("Description is required");
       }

       if (request.getActorsAndRoles() == null) {
           throw new BadRequestException("Actors are required");
       }

       if (request.getActorsAndRoles().stream().noneMatch(role -> personService.existsByName(role.getPerson().getName()))) {
           throw new NotFoundException("One or several Actors do not exist");
       }

       if (request.getDirectors().stream().noneMatch(director -> personService.existsByName(director.getName()))) {
           throw new NotFoundException("One or several Directors do not exist");
       }

       Movie movie = new Movie(request.getTitle(), request.getDescription());
       List<Person> directors = request.getDirectors().stream().map(d -> personService.findByName(d.getName())).toList();
       List<Role> roles = request.getActorsAndRoles().stream().map(r -> {
           Role role = new Role();
           role.setPerson(personService.findByName(r.getPerson().getName()));
           role.setRoles(r.getRoles());
           return role;
       }).toList();
       movie.getDirectors().addAll(directors);
       movie.getActorsAndRoles().addAll(roles);

       repository.save(movie);
    }

    public void addRoleToMovie(String title, Role role) {
        if (repository.existsByTitle(title) && personService.existsByName(role.getPerson().getName())) {
            Movie movie = repository.findByTitle(title);
            Person person = personService.findByName(role.getPerson().getName());
            role.setPerson(person);
            movie.getActorsAndRoles().add(role);
            repository.save(movie);
        } else {
            throw new NotFoundException("Director or Movie does not exist");
        }
    }

    public void addDirectorToMovie(String title, Person person) {
        if (repository.existsByTitle(title) && personService.existsByName(person.getName())) {
            Movie movie = repository.findByTitle(title);
            movie.getDirectors().add(personService.findByName(person.getName()));
            repository.save(movie);
        } else {
            throw new NotFoundException("Director or Movie does not exist");
        }
    }

}
