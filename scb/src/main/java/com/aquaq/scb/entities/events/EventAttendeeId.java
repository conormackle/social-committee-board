package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class EventAttendeeId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsersModel user;

    @ManyToOne
    @JsonIgnoreProperties(value = "attendees", allowSetters = true)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventsModel eventsModel;

}