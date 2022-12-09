package com.job_tracking_system.controllers;

import com.job_tracking_system.entity.Person;
import com.job_tracking_system.exceptions.PersonNotFoundException;
import com.job_tracking_system.mappers.PersonMapper;
import com.job_tracking_system.payload.responce.UserInfoResponse;
import com.job_tracking_system.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    PersonJpaRepository personJpaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok().body(personJpaRepository.findAll().stream().map(PersonMapper::EntityToDto));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    ResponseEntity<?> getPersonById(@PathVariable("id") long id) {
        Person person = personJpaRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("No such person with ID: " + id));
        return ResponseEntity.ok().body(new UserInfoResponse(person.getId(),
                person.getLogin(),
                List.of(person.getRole().toString())));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        Person person = personJpaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("No Person with ID : " + id));
        personJpaRepository.deleteById(person.getId());
        return ResponseEntity.ok().body("Person with ID : " + id + " deleted with success!");
    }
}