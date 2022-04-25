package com.aquaq.scb.entities.events.images;

import com.aquaq.scb.entities.events.EventsModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="event_images")
public class EventImagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private EventsModel event;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="thumbnail")
    private Boolean thumbnail;

}
