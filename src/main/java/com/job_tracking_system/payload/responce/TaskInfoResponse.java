package com.job_tracking_system.payload.responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskInfoResponse {
    private long id;
    private String name;
    private String status;
    private double difficulty;
    private String description;

    private long implementerId;

    private String report;
}
