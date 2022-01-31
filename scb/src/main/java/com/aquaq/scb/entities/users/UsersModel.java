package com.aquaq.scb.entities.users;

import com.aquaq.scb.entities.events.EventsModel;
import com.aquaq.scb.entities.projects.ProjectsModel;
import com.aquaq.scb.entities.roles.RolesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.EventListenerProxy;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="email_verified")
    private int emailVerified;

    @ManyToMany(mappedBy = "user")
    private Set<ProjectsModel> projects = new HashSet<>();

    @ManyToMany(mappedBy = "user")
    private Set<RolesModel> roles = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<EventsModel> events;
}
