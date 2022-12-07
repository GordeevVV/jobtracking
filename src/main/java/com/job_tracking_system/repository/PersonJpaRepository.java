package com.job_tracking_system.repository;

import com.job_tracking_system.entity.ERole;
import com.job_tracking_system.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Long> {

     Optional<Person> findByLogin(String login);
     Boolean existsByLogin(String login);
     List<Person> findByRole(ERole eRole);
     Person findPersonByLoginAndPassword(String login, String password);
}
