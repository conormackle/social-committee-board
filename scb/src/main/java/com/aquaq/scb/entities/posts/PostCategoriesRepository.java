package com.aquaq.scb.entities.posts;

import com.aquaq.scb.entities.audits.AuditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoriesRepository extends JpaRepository<PostCategoriesModel, Integer> {
}
