package com.aquaq.scb.entities.projects.images;

import com.aquaq.scb.entities.projects.ProjectsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="projects_images")
public class ProjectsImagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "projects_id")
    @JsonIgnore
    private ProjectsModel project;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="thumbnail")
    private Boolean thumbnail;

}
