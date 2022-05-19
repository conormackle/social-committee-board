package com.aquaq.scb.entities;

import com.aquaq.scb.response.ScbResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EntityService {
    ScbResponse findByCreatedDateTime(String startDate, String endDate, Integer page, Integer size);
    Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable page);
    Page<Object> findByCreatedDateTime(LocalDateTime endDateTime, Pageable page);
    Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page);

}
