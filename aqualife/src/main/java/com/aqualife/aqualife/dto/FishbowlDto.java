package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
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

    @Builder
    public FishbowlDto(String email, String fishbowl, List<Co2> co2) {
        this.email = email;
        this.fishbowl = fishbowl;
        this.co2 = co2;
    }

    public Fishbowl toEntity() {
        return Fishbowl.builder()
                .email(email)
                .fishbowl(fishbowl)
                .co2(co2)
                .build();
    }
}
