package com.aquaq.scb.entities.posts;

import com.aquaq.scb.entities.users.UsersModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="posts")
public class PostsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id_posted_by")
    private UsersModel user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "post_category_id")
    private PostCategoriesModel postCategory;

    @Column(name="created_datetime")
    private LocalDateTime createdDateTime;

    @Column(name="updated_datetime")
    private LocalDateTime updatedDateTime;


}
