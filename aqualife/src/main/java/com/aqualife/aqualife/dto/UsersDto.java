package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto {
    private String email;
    private String password;
    private String phone;

    @Builder
    public UsersDto(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}