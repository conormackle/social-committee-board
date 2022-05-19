package com.aquaq.scb.entities.users.roles;

import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user_roles")
public class UserRolesModel {

    @JsonIgnore
    @EmbeddedId
    UserRolesKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="role_id")
    RolesModel role;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="user_id")
    UsersModel user;
}