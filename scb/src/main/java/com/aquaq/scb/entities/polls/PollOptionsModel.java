package com.aquaq.scb.entities.polls;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="poll_options")
public class PollOptionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private PollsModel poll;

    @JsonIgnore
    @OneToMany(mappedBy ="id.pollOption")
    private Set<PollOptionVoteModel> votes;

    @Transient
    public int numberOfVotes;

    public Integer getNumberOfVotes() {
        return votes.size();
    }
}
