package com.job_tracking_system.repository;

import com.job_tracking_system.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);
    List<Person> findByPosition(String position);
    Person findPersonByLoginAndPassword(String login, String password);
}
