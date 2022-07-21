package com.aquaq.scb.entities.posts.images;

import com.aquaq.scb.entities.posts.PostsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="posts_images")
public class PostsImagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "posts_id")
    @JsonIgnore
    private PostsModel post;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="thumbnail")
    private Boolean thumbnail;

}
