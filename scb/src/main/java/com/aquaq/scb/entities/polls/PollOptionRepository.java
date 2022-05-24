package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.events.EventCategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollOptionRepository extends JpaRepository<PollOptionsModel, Integer> {
}
