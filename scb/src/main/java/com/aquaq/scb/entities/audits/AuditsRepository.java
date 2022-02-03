package com.aquaq.scb.entities.audits;

import com.aquaq.scb.entities.events.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditsRepository extends JpaRepository<AuditModel, Integer> {
}
