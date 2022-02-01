package com.aquaq.scb.entities.projects;

import com.aquaq.scb.entities.users.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectsModel, Integer> {
}
