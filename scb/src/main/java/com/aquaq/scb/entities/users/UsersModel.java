package com.aquaq.scb.entities.users;

import com.aquaq.scb.entities.audits.AuditModel;
import com.aquaq.scb.entities.events.EventAttendeeModel;
import com.aquaq.scb.entities.events.EventsModel;
import com.aquaq.scb.entities.polls.PollOptionVoteModel;
import com.aquaq.scb.entities.posts.PostsModel;
import com.aquaq.scb.entities.projects.ProjectsModel;
import com.aquaq.scb.entities.roles.RolesModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
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
    private Integer emailVerified;

    //@JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "user")
    private Set<ProjectsModel> projects;

    //@JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<RolesModel> roles;

    //@JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<EventsModel> events;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<AuditModel> audits;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<PostsModel> posts;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "id.user")
    private Set<PollOptionVoteModel> pollOptionVotes;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "id.user")
    private Set<EventAttendeeModel> eventAttendeeModels;

}
