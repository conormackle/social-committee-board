package com.aquaq.scb.entities.projects.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsImagesRepository extends JpaRepository<ProjectsImagesModel, Integer> {

    Optional<ProjectsImagesModel> findFirstByProjectIdAndThumbnailTrue(@Param("ProjectsId") int id);
}
