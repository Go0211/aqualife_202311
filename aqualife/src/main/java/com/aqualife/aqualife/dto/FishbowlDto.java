package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FishbowlDto {
    private String email;
    private String fishbowl;
    private List<Co2> co2;
    private List<Light> light;
    private Ph ph;
    private Temperature temperature;
    private List<Filters> filter;
    private List<Object> state;

    @Builder
    public FishbowlDto(String email, String fishbowl, List<Co2> co2,
                       List<Light> light, Ph ph, Temperature temperature,
                       List<Filters> filter, List<Object> state) {
        this.email = email;
        this.fishbowl = fishbowl;
        this.co2 = co2;
        this.light = light;
        this.ph = ph;
        this.temperature = temperature;
        this.filter = filter;
        this.state = state;
    }

    public Fishbowl toEntity() {
        return Fishbowl.builder()
                .email(email)
                .fishbowl(fishbowl)
                .co2(co2)
                .light(light)
                .ph(ph)
                .temperature(temperature)
                .filter(filter)
                .state(state)
                .build();
    }
}
