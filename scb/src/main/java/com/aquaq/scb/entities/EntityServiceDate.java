package com.aquaq.scb.entities;

import com.aquaq.scb.response.ScbResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EntityServiceDate {
    ScbResponse findByDate(String startDate, String endDate, Integer page, Integer size);
    Page<Object> findByDateBefore(LocalDateTime endDateTime, Pageable page);
    Page<Object> findByDate(LocalDateTime endDateTime, Pageable page);
    Page<Object> findByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page);
}
