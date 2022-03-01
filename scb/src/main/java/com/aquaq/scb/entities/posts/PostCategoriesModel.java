package com.aquaq.scb.entities.posts;


import com.aquaq.scb.entities.events.EventsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="post_categories")
public class PostCategoriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="postCategory")
    private Set<PostsModel> posts;
}
