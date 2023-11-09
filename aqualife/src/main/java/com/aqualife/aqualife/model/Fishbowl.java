package com.aqualife.aqualife.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Fishbowl {
    private String email;
    private String fishbowl;
    private List<Co2> co2;

    @Builder
    public Fishbowl(String email, String fishbowl, List<Co2> co2) {
        this.email = email;
        this.fishbowl = fishbowl;
        this.co2 = co2;
    }
}
