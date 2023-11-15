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
    private Ph ph;
    private Temperature temperature;

    private List<Filters> filter;

    @Builder
    public Fishbowl(String email, String fishbowl, List<Co2> co2,
                    List<Light> light, Ph ph, Temperature temperature,
                    List<Filters> filter) {
        this.email = email;
        this.fishbowl = fishbowl;
        this.co2 = co2;
        this.light = light;
        this.ph = ph;
        this.temperature = temperature;
        this.filter = filter;
    }
}
