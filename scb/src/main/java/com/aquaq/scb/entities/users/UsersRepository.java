package com.aquaq.scb.entities.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    @Query(value="SELECT * FROM users WHERE email = :Email", nativeQuery=true)
    UsersModel getByEmail(@Param("Email") String email);
}
