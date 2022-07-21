package com.aquaq.scb.entities.projects;

import com.aquaq.scb.entities.events.images.EventImagesModel;
import com.aquaq.scb.entities.projects.images.ProjectsImagesModel;
import com.aquaq.scb.entities.users.UsersModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="projects")
public class ProjectsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="details")
    private String details;

    @Column(name="created_datetime")
    private LocalDateTime createdDateTime;

    @Column(name="updated_datetime")
    private LocalDateTime updatedDateTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_projects",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<UsersModel> user;

    @Column(name="created_by_user_id")
    private Integer createdByUserId;

    @Column(name="deleted")
    private boolean deleted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "project")
    private Set<ProjectsImagesModel> projectImages;

}
