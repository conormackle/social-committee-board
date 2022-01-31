package com.aquaq.scb.entities.events;
import com.aquaq.scb.entities.users.UsersModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column(name="user_id")
    private UsersModel user;

}
