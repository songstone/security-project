package com.example.securityproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRegisterDto {

    private String userName;
    private String password;
}
