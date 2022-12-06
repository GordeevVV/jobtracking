package com.job_tracking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job_tracking_system.entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name= "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Person person;

    private String report;
}
