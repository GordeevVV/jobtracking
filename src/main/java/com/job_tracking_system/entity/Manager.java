package com.job_tracking_system.entity;

import javax.persistence.Entity;

@Entity
public class Manager extends Person{
    public Manager(String login, String password, String position) {
        super(login, password, position);
    }

    public Manager() {
        super();
    }
}
