package com.job_tracking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name= "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    public Task(String name, EStatus status, double difficulty, String description) {
        this.name = name;
        this.status = status;
        this.difficulty = difficulty;
        this.description = description;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "taskSeq")
    @SequenceGenerator(name = "taskSeq", initialValue = 1, allocationSize = 1, sequenceName = "TASK_SEQUENCE")
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @NotBlank
    private double difficulty;

    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private String report;
}
