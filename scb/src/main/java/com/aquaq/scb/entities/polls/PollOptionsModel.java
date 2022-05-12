package com.aquaq.scb.entities.polls;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
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

    @JsonIgnoreProperties(value = "id.pollOption", allowSetters = true)
    @OneToMany(mappedBy ="id.pollOption", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PollOptionVoteModel> votes;

    @Transient
    public int numberOfVotes;

    public Integer getNumberOfVotes() {
        if(votes != null){return votes.size();}else{
            return 0;
        }
    }

    public void addVote(PollOptionVoteModel pollOptionVoteModel){
        if(votes == null){
            votes = new HashSet<>();
        }
        pollOptionVoteModel.getId().setPollOption(this);
        votes.add(pollOptionVoteModel);
    }

    public boolean deleteVote(PollOptionVoteModel pollOptionVoteModel){
        if(votes == null){
            return false;
        }
        for(Iterator<PollOptionVoteModel> it = votes.iterator(); it.hasNext();){
            PollOptionVoteModel poll = it.next();
            if(poll.getId().getUser().getId().equals(pollOptionVoteModel.getId().getUser().getId()) && poll.getId().getPollOption().getId().equals(pollOptionVoteModel.getId().getPollOption().getId())){
                votes.remove(poll);
                return true;
            }
        }
        return false;
    }
}
