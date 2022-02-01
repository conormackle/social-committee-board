package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.projects.ProjectsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventsModel, Integer> {
}
