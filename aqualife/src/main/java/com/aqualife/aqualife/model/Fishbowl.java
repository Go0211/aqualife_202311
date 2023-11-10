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
    private List<Light> light;

    @Builder
    public Fishbowl(String email, String fishbowl, List<Co2> co2, List<Light> light) {
        this.email = email;
        this.fishbowl = fishbowl;
        this.co2 = co2;
        this.light = light;
    }
}
