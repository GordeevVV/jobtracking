package com.job_tracking_system.entity;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    public Task() {
    }

    public Task(String name, String status, double difficulty, String description) {
        this.name = name;
        this.status = status;
        this.difficulty = difficulty;
        this.description = description;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String status;
    private double difficulty;
    private String description;
    private long implementerId;
    private String report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getImplementerId() {
        return implementerId;
    }

    public void setImplementerId(long implementerId) {
        this.implementerId = implementerId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
