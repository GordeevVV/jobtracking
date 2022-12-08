package com.job_tracking_system.repository;

import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {
    Task findByName(String login);
    List<Task> findByStatus(EStatus status);
    List<Task> findByDifficulty(Double difficulty);
    List<Task> findByPersonId(Long id);
}
