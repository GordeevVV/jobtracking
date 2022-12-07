package com.job_tracking_system.controllers;

import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.PersonDTO;
import com.job_tracking_system.exceptions.PersonNotFoundException;
import com.job_tracking_system.mappers.PersonMapper;
import com.job_tracking_system.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    PersonJpaRepository personJpaRepository;

    @GetMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public @ResponseBody List<Person> getAll(){
        return personJpaRepository.findAll();
    }

    @GetMapping(value = "/persons/{id}")
    ResponseEntity<Person> getPersonById(@PathVariable("id") long id){
        Person person = personJpaRepository.findById(id).orElseThrow(()->new PersonNotFoundException("No such person with ID: "+id));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping(value = "/persons")
    ResponseEntity<?> registerUser(@RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.DtoToEntity(personDTO);

        Person savedPerson = personJpaRepository.save(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping(value="/products/{id}")
    ResponseEntity deletePerson( @PathVariable("id") long id) {
        Person person = personJpaRepository.findById(id)
                .orElseThrow(()->new PersonNotFoundException("No Person with ID : "+id));
        personJpaRepository.deleteById(person.getId());
        return ResponseEntity.ok().body("Person with ID : "+id+" deleted with success!");
    }
}