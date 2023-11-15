package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Ph;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhDto {
    private double maxPh;
    private double minPh;
    private double phState;

    @Builder
    public PhDto(double maxPh, double minPh, double phState) {
        this.maxPh = maxPh;
        this.minPh = minPh;
        this.phState = phState;
    }

    public Ph toEntity() {
        return Ph.builder()
                .maxPh(maxPh)
                .minPh(minPh)
                .phState(phState)
                .build();
    }
}
