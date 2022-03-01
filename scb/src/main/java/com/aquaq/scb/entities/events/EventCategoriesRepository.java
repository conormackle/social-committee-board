package com.aquaq.scb.entities.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoriesRepository extends JpaRepository<EventCategoriesModel, Integer> {
}
