package com.aquaq.scb.entities.posts.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsImagesRepository extends JpaRepository<PostsImagesModel, Integer> {

    Optional<PostsImagesModel> findFirstByPostIdAndThumbnailTrue(@Param("PostsId") int id);
}
