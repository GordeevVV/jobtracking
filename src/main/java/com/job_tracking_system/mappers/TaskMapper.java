package com.job_tracking_system.mappers;

import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import com.job_tracking_system.entity.TaskDTO;

import java.util.Objects;

public class TaskMapper {
    public static Task DtoToEntity(TaskDTO taskDTO) {
        return new Task().setName(taskDTO.getName())
                .setStatus(toEStatus(taskDTO.getStatus()))
                .setDifficulty(taskDTO.getDifficulty())
                .setDescription(taskDTO.getDescription())
                .setPerson(new Person().setId(taskDTO.getImplementerId()))
                .setReport(taskDTO.getReport());
    }
    public static TaskDTO EntityToDto(Task task){
        return new TaskDTO().setName(task.getName())
                .setStatus(task.getStatus().toString())
                .setDifficulty(task.getDifficulty())
                .setDescription(task.getDescription())
                .setImplementerId(task.getId())
                .setReport(task.getReport());
    }

    public static EStatus toEStatus(String status){
        return switch (status) {
            case "STATUS_CREATED" -> EStatus.STATUS_CREATED;
            case "STATUS_ASSIGNED" -> EStatus.STATUS_ASSIGNED;
            case "STATUS_COMPLETED" -> EStatus.STATUS_COMPLETED;
            default -> throw new RuntimeException("Can't resolve task status");
        };
    }
}
