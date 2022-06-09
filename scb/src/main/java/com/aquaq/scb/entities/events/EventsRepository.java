package com.aquaq.scb.entities.events;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventsRepository extends JpaRepository<EventsModel, Integer> {

    Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable pageable);

    Page<Object> findByCreatedDateTimeBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Pageable pageable);

    Page<Object> findByDateBefore(LocalDateTime endDateTime, Pageable pageable);

    Page<Object> findByDateBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Pageable pageable);


}
