package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Temperature {
    private int tempStay;
    private int tempState;

    @Builder
    public Temperature(int tempStay, int tempState) {
        this.tempStay = tempStay;
        this.tempState = tempState;
    }
}
