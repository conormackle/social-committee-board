package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.users.UsersModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "poll_poll_option",
            joinColumns = { @JoinColumn(name = "poll_id") },
            inverseJoinColumns = { @JoinColumn(name = "poll_option_id") }
    )
    private Set<PollOptionsModel> pollOptions = new HashSet<>();

}