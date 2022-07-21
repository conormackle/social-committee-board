package com.aquaq.scb.entities.polls.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollsImagesRepository extends JpaRepository<PollsImagesModel, Integer> {

    Optional<PollsImagesModel> findFirstByPollIdAndThumbnailTrue(@Param("PollsId") int id);
}
