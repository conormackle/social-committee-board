package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    public Set<PollOptionsModel> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(Set<PollOptionsModel> pollOptions) {
        this.pollOptions = pollOptions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Column(name="name")
    private String name;

    @Column(name="details")
    private String details;

    //@JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "poll_poll_option",
            joinColumns = { @JoinColumn(name = "poll_id") },
            inverseJoinColumns = { @JoinColumn(name = "poll_option_id") }
    )
    private Set<PollOptionsModel> pollOptions = new HashSet<>();

}