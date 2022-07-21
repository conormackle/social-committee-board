package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.polls.images.PollsImagesModel;
import com.aquaq.scb.entities.posts.images.PostsImagesModel;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="polls")
public class PollsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="details")
    private String details;

    @Column(name="created_datetime")
    private LocalDateTime createdDateTime;

    @Column(name="updated_datetime")
    private LocalDateTime updatedDateTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "poll", fetch = FetchType.LAZY)
    private Set<PollOptionsModel> pollOptions = new HashSet<>();

    @Column(name="deleted")
    private boolean deleted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "poll")
    private Set<PollsImagesModel> pollImages;
}