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
    @ManyToOne(optional = false)
    @JoinColumn(name="poll_id", nullable=false)
    private PollsModel poll;

    @JsonIgnore
    @OneToMany(mappedBy ="id.pollOption")
    private Set<PollOptionVoteModel> votes;

    @Transient
    public int numberOfVotes;

    public Integer getNumberOfVotes() {
        if(votes != null){return votes.size();}else{
            return 0;
        }
    }
}
