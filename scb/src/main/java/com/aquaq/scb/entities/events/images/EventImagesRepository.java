package com.aquaq.scb.entities.events.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventImagesRepository extends JpaRepository<EventImagesModel, Integer> {

    Optional<EventImagesModel> findFirstByEventIdAndThumbnailTrue(@Param("EventId") int id);
}
