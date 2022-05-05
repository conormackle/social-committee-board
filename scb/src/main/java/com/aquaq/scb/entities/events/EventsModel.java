package com.aquaq.scb.entities.events;
import com.aquaq.scb.entities.events.images.EventImagesModel;
import com.aquaq.scb.entities.polls.PollOptionVoteModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="events")
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="details")
    private String details;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private UsersModel user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "event_category_id")
    private EventCategoriesModel eventCategory;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="event")
    private Set<EventImagesModel> eventImages;

    @JsonIgnore
    @OneToMany(mappedBy ="id.eventsModel")
    private Set<EventAttendeeModel> attendees;

}
