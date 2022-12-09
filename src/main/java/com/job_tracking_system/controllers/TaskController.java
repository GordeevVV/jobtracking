package com.job_tracking_system.controllers;

import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import com.job_tracking_system.exceptions.TaskNotFoundException;
import com.job_tracking_system.mappers.TaskMapper;
import com.job_tracking_system.payload.request.TaskAssignRequest;
import com.job_tracking_system.payload.request.TaskCompleteRequest;
import com.job_tracking_system.payload.request.TaskUploadRequest;
import com.job_tracking_system.payload.responce.MessageResponse;
import com.job_tracking_system.repository.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @GetMapping
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok().body(taskJpaRepository.findAll().stream().map(TaskMapper::EntityToDto));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('IMPLEMENTER') or hasRole('MANAGER')")
    public ResponseEntity<?> getTaskById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(TaskMapper.EntityToDto(taskJpaRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("No task with id: " + id))));
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> createTask(@Valid @RequestBody TaskUploadRequest taskUploadRequest){
        Task task = new Task().setName(taskUploadRequest.getName())
                .setDifficulty(taskUploadRequest.getDifficulty())
                .setDescription(taskUploadRequest.getDescription())
                .setStatus(EStatus.STATUS_CREATED);
        taskJpaRepository.save(task);
        return ResponseEntity.ok().body(new MessageResponse("Task successfully created"));
    }

    //1 Ассайн таски на имплементера с изменением статуса и началом отсчета времени выполнения(если было null раньше)
    @PostMapping(value = "/assign")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> assignTask(@Valid@RequestBody TaskAssignRequest taskAssignRequest){
        Task task = taskJpaRepository.findById(taskAssignRequest.getId()).orElseThrow(() -> new TaskNotFoundException("No task with id: " + taskAssignRequest.getId()));
        task.setPerson(new Person().setId(taskAssignRequest.getImplementerId()));
        task.setStatus(EStatus.STATUS_ASSIGNED);
        if(Objects.isNull(task.getBeginTime())) {
            task.setBeginTime(LocalDateTime.now());
        }
        taskJpaRepository.save(task);
        return ResponseEntity.ok().body(new MessageResponse("Task with id: " + task.getId() + "successfully assigned"));
    }

    //2 Смена статуса задачи на complete и регистрация времени окончания работы над задачей
    @PostMapping(value = "/complete")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> completeTask(@Valid@RequestBody TaskCompleteRequest taskCompleteRequest){
        Task task = taskJpaRepository.findById(taskCompleteRequest.getId()).orElseThrow(() -> new TaskNotFoundException("No task with id: " + taskCompleteRequest.getId()));
        task.setReport(task.getReport());
        task.setStatus(EStatus.STATUS_COMPLETED);
        task.setEndTime(LocalDateTime.now());
        return ResponseEntity.ok().body(new MessageResponse("Task with id: " + task.getId() + "successfully completed"));
    }

    //3 Смена полей в задаче сложности/описания/названия
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> updateTask(@PathVariable("id") long id, @Valid@RequestBody TaskUploadRequest taskUploadRequest){
        Task task = taskJpaRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No task with id: " + id));
        task.setName(taskUploadRequest.getName())
                .setDifficulty(taskUploadRequest.getDifficulty())
                .setDescription(taskUploadRequest.getDescription());
        taskJpaRepository.save(task);
        return ResponseEntity.ok().body(new MessageResponse("Task successfully updated"));
    }
}
