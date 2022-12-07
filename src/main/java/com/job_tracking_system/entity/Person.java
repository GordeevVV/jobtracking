package com.job_tracking_system.entity;

import com.job_tracking_system.entity.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "menuSeq")
    @SequenceGenerator(name = "menuSeq", initialValue = 1, allocationSize = 1, sequenceName = "MENU_SEQUENCE")
    private long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ERole role;
}
