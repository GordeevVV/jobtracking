package com.job_tracking_system.entity;

import javax.persistence.*;

@Entity
@Table(name= "persons")
public class Person {
    @Id
    @Column(nullable = false)
    private long personId;
    private String login;
    private String password;
    private String position;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
