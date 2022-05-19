package com.aquaq.scb.entities.users.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRolesModel, Integer> {

    Optional<UserRolesModel> findByUserIdAndRoleId(int userId, int roleId);

    void deleteByUserIdAndRoleId(int userId, int roleId);

}
