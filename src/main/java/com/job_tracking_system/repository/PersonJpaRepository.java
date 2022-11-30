package com.job_tracking_system.repository;

import com.job_tracking_system.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);
    Person findByPosition(String position);
    Person findPersonByLoginAndPassword(String login, String password);
}
