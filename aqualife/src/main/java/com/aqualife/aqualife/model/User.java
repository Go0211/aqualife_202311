package com.aqualife.aqualife.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String id;
    private String name;
    private String email;
}