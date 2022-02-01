package com.aquaq.scb.entities.mapper;

import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersDto;

public class UsersMapper {

    private UsersMapper(){}

    public static UsersDto toUserDto(UsersModel user){
        return UsersDto.builder()
                .email(user.getEmail())
                .emailVerified(user.getEmailVerified())
                .name(user.getName())
                .id(user.getId()).build();
    }

    public static UsersModel toUserModel(UsersDto user){
        return UsersModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .emailVerified(user.getEmailVerified())
                .name(user.getName())
                .build();
    }
}
