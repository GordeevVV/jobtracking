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
        if (Objects.nonNull(task.getBeginTime())) {
            if(Objects.nonNull(task.getEndTime())) {
              customTime = TaskMapper.getCustomTime(LocalDateTime.from(task.getBeginTime()), task.getEndTime());
            } else {
                customTime = TaskMapper.getCustomTime(LocalDateTime.from(task.getBeginTime()), LocalDateTime.now());
            }
        }
        return new TaskDTO().setName(task.getName())
                .setStatus(task.getStatus().toString())
                .setDifficulty(task.getDifficulty())
                .setDescription(task.getDescription())
                .setImplementerId(Objects.isNull(task.getPerson())? null: task.getPerson().getId())
                .setReport(task.getReport())
                .setDuration(customTime);
    }
    private static CustomTime getCustomTime(LocalDateTime tempDateTime, LocalDateTime endTime){
        CustomTime customTime = new CustomTime();
        long hours = tempDateTime.until(endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(endTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(endTime, ChronoUnit.SECONDS);
        customTime.setHours(hours);
        customTime.setMinutes(minutes);
        customTime.setSeconds(seconds);
        return customTime;
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
