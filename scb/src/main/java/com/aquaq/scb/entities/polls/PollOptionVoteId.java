package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PollOptionVoteId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsersModel user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "poll_option_id", referencedColumnName = "id")
    private PollOptionsModel pollOption;

}
