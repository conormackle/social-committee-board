package com.aquaq.scb.entities.users.projects;

import com.aquaq.scb.entities.projects.ProjectsModel;
import com.aquaq.scb.entities.users.UsersModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user_projects")
public class UserProjectsModel {

    @EmbeddedId
    UserProjectsKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="project_id")
    ProjectsModel project;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="user_id")
    UsersModel user;
}