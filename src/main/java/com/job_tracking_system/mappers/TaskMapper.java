package com.job_tracking_system.mappers;

import com.job_tracking_system.entity.Task;
import com.job_tracking_system.entity.TaskDTO;

public class TaskMapper {
    public static Task DtoToEntity(TaskDTO taskDTO) {
        return new Task().setName(taskDTO.getName())
                .setStatus(taskDTO.getStatus())
                .setDifficulty(taskDTO.getDifficulty())
                .setDescription(taskDTO.getDescription());
    }
    public static TaskDTO EntityToDto(Task task){
        return new TaskDTO().setName(task.getName())
                .setStatus(task.getStatus())
                .setDifficulty(task.getDifficulty())
                .setDescription(task.getDescription());
    }
}
