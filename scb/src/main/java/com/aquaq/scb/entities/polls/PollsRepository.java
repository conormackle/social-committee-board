package com.aquaq.scb.entities.polls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollsRepository extends JpaRepository<PollsModel, Integer> {
}
