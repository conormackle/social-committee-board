package com.aquaq.scb.entities.polls;


import com.aquaq.scb.entities.users.UsersModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="poll_option_vote")
public class PollOptionVoteModel implements Serializable {

    @Id
    PollOptionVoteId id;

}
