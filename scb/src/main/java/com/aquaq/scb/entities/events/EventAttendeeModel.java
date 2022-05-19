package com.aquaq.scb.entities.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="event_attendee")
public class EventAttendeeModel {

    @Id
    EventAttendeeId id;

    @Column(name="rsvp")
    private String rsvp;

    @Column(name="comment")
    private String comment;
}