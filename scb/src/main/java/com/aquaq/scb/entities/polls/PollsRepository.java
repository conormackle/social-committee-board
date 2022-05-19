package com.aquaq.scb.entities.polls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PollsRepository extends JpaRepository<PollsModel, Integer> {

    Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable pageable);

    Page<Object> findByCreatedDateTime(LocalDateTime endDateTime, Pageable pageable);

    Page<Object> findByCreatedDateTimeBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Pageable pageable);
}
