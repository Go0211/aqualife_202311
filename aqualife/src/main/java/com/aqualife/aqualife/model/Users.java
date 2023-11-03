package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {

    private String email;
    private String password;
    private String phone;

    @Builder
    public Users(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}