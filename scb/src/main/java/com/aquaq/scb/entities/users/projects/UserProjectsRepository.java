package com.aquaq.scb.entities.users.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProjectsRepository extends JpaRepository<UserProjectsModel, Integer> {

    Optional<UserProjectsModel> findByUserIdAndProjectId(int userId, int projectId);

}
