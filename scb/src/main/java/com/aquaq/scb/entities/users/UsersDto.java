package com.aquaq.scb.entities.users;

import lombok.*;

@Data
@Builder
public class UsersDto {
    private Integer id;
    private String name;
    private String email;
    private int emailVerified;
}
