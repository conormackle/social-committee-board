package com.aquaq.scb.entities.roles;

import com.aquaq.scb.entities.users.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Integer> {
}
