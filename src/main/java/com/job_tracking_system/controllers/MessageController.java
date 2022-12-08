package com.job_tracking_system.controllers;

import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import com.job_tracking_system.entity.TaskDTO;
import com.job_tracking_system.exceptions.PersonNotFoundException;
import com.job_tracking_system.exceptions.TaskNotFoundException;
import com.job_tracking_system.mappers.PersonMapper;
import com.job_tracking_system.mappers.TaskMapper;
import com.job_tracking_system.payload.request.TaskUploadRequest;
import com.job_tracking_system.payload.responce.MessageResponse;
import com.job_tracking_system.payload.responce.UserInfoResponse;
import com.job_tracking_system.repository.PersonJpaRepository;
import com.job_tracking_system.repository.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    PersonJpaRepository personJpaRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @GetMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok().body(personJpaRepository.findAll().stream().map(PersonMapper::EntityToDto));
    }

    @GetMapping(value = "/persons/{id}")
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    ResponseEntity<?> getPersonById(@PathVariable("id") long id) {
        Person person = personJpaRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("No such person with ID: " + id));
        return ResponseEntity.ok().body(new UserInfoResponse(person.getId(),
                person.getLogin(),
                List.of(person.getRole().toString())));
    }

    @DeleteMapping(value = "/persons/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
        Person person = personJpaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("No Person with ID : " + id));
        personJpaRepository.deleteById(person.getId());
        return ResponseEntity.ok().body("Person with ID : " + id + " deleted with success!");
    }

    @GetMapping(value = "/tasks")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok().body(taskJpaRepository.findAll().stream().map(TaskMapper::EntityToDto));
    }

    @GetMapping(value = "/tasks/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getTaskById(@PathVariable("id") long id){
        Task task = taskJpaRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("No task with id: " + id));
        return ResponseEntity.ok().body(new TaskDTO(task.getName(),
                task.getStatus().toString(),
                task.getDifficulty(),
                task.getDescription(),
                task.getPerson().getId(),
                task.getReport()));
    }

    @PostMapping(value = "/tasks/create")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> createTask(@Valid@RequestBody TaskUploadRequest taskUploadRequest){
        Task task = new Task().setName(taskUploadRequest.getName())
                .setDifficulty(taskUploadRequest.getDifficulty())
                .setDescription(taskUploadRequest.getDescription())
                .setStatus(EStatus.STATUS_CREATED);
        taskJpaRepository.save(task);
        return ResponseEntity.ok().body(new MessageResponse("Task successfully created"));
    }
    //1 Ассайн таски на имплементера с изменением статуса и началом отсчета времени выполнения(если было null раньше)
    //2 Смена статуса задачи на complete и регистрация времени окончания работы над задачей
    //3 Получение времени выполнения задания
    //4 Смена полей в задаче сложности/описания/названия
}