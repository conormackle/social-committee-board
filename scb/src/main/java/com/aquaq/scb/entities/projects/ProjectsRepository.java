package com.aquaq.scb.entities.projects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectsModel, Integer> {

    Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable pageable);

    Page<Object> findByCreatedDateTimeBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Pageable pageable);
}
