package com.job_tracking_system.mappers;

import com.job_tracking_system.entity.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public static TaskDTO EntityToDto(Task task) {
        CustomTime customTime = new CustomTime();
        if (Objects.nonNull(task.getBeginTime()) && Objects.nonNull(task.getEndTime())) {
            LocalDateTime tempDateTime = LocalDateTime.from(task.getBeginTime());
            long hours = tempDateTime.until(task.getEndTime(), ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plusHours(hours);

            long minutes = tempDateTime.until(task.getEndTime(), ChronoUnit.MINUTES);
            tempDateTime = tempDateTime.plusMinutes(minutes);

            long seconds = tempDateTime.until(task.getEndTime(), ChronoUnit.SECONDS);
            customTime.setHours(hours);
            customTime.setMinutes(minutes);
            customTime.setSeconds(seconds);
        }
        return new TaskDTO().setName(task.getName())
                .setStatus(task.getStatus().toString())
                .setDifficulty(task.getDifficulty())
                .setDescription(task.getDescription())
                .setImplementerId(task.getId())
                .setReport(task.getReport())
                .setDuration(customTime);
    }

    public static EStatus toEStatus(String status) {
        return switch (status) {
            case "STATUS_CREATED" -> EStatus.STATUS_CREATED;
            case "STATUS_ASSIGNED" -> EStatus.STATUS_ASSIGNED;
            case "STATUS_COMPLETED" -> EStatus.STATUS_COMPLETED;
            default -> throw new RuntimeException("Can't resolve task status");
        };
    }
}
