package com.aquaq.scb.entities.users.projects;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class UserProjectsKey implements Serializable {

    @Column(name ="project_id")
    private Integer projectId;

    @Column(name ="user_id")
    private Integer userId;

    public UserProjectsKey(Integer userId, Integer projectId) {
        this.projectId = projectId;
        this.userId = userId;
    }

}
