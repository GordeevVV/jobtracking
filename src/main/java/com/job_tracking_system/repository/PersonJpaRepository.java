package com.job_tracking_system.repository;

import com.job_tracking_system.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);
    Person findByPosition(String position);
}
