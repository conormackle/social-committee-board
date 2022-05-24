package com.aquaq.scb.entities.users.roles;

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
@Embeddable
@Builder
public class UserRolesKey implements Serializable {

    @Column(name ="role_id")
    private Integer roleId;

    @Column(name ="user_id")
    private Integer userId;

    public UserRolesKey(Integer userId, Integer roleId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
