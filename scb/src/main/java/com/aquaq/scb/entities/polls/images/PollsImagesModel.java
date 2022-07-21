package com.aquaq.scb.entities.polls.images;

import com.aquaq.scb.entities.polls.PollsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="polls_images")
public class PollsImagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "polls_id")
    @JsonIgnore
    private PollsModel poll;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="thumbnail")
    private Boolean thumbnail;

}
