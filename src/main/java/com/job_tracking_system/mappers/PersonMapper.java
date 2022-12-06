package com.job_tracking_system.mappers;

import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.PersonDTO;

public class PersonMapper {
    public static Person DtoToEntity(PersonDTO personDTO){
        return new Person().setPassword(personDTO.getPassword())
                .setLogin(personDTO.getLogin());
    }
    public static PersonDTO EntityToDto(Person person){
        return new PersonDTO()
                .setLogin(person.getLogin())
                .setPassword(person.getPassword());
    }
}
