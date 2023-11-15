package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ph {
    private double maxPh;
    private double minPh;
    private double phState;

    @Builder
    public Ph(double maxPh, double minPh, double phState) {
        this.maxPh = maxPh;
        this.minPh = minPh;
        this.phState = phState;
    }
}
