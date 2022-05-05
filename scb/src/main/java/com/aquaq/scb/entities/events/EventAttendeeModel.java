package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.polls.PollOptionVoteId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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