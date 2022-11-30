package com.job_tracking_system.repository;

import com.job_tracking_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {
    Task findByName(String login);
    Task findByStatus(String status);
    Task findByDifficulty(Float difficulty);
    Task findByImplementerId(Integer implementerId);
}
