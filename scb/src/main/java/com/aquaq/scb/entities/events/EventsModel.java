package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.events.images.EventImagesModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "events")
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "details")
    private String details;

    @Column(name = "created_datetime")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_datetime")
    private LocalDateTime updatedDateTime;

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
    @OneToMany(mappedBy = "event")
    private Set<EventImagesModel> eventImages;

    @JsonIgnoreProperties(value = "id.pollOption", allowSetters = true)
    @OneToMany(mappedBy = "id.eventsModel", cascade = CascadeType.ALL)
    private Set<EventAttendeeModel> attendees;

    public void addAttendee(EventAttendeeModel eventAttendeeModel) {
        if (attendees == null) {
            attendees = new HashSet<>();
        }
        if (attendees.contains(eventAttendeeModel)) {
            return;
        }
        eventAttendeeModel.getId().setEventsModel(this);
        attendees.add(eventAttendeeModel);
    }

    @Column(name="deleted")
    private boolean deleted;

}
