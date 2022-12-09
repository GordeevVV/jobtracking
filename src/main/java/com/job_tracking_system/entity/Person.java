package com.job_tracking_system.entity;

import com.job_tracking_system.entity.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "persons",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "login")
        })
@EntityListeners(AuditingEntityListener.class)
public class Person {

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Person(String login, String password, ERole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "personSeq")
    @SequenceGenerator(name = "personSeq", initialValue = 1, allocationSize = 1, sequenceName = "PERSON_SEQUENCE")
    private long id;
    @NotBlank
    @Size(max = 20)
    private String login;
    @NotBlank
    @Size(max = 20)
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ERole role;
}
