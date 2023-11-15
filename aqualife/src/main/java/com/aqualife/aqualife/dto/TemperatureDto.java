package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Temperature;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemperatureDto {
    private int tempStay;
    private int tempState;

    @Builder
    public TemperatureDto(int tempStay, int tempState) {
        this.tempStay = tempStay;
        this.tempState = tempState;
    }

    public Temperature toEntity() {
        return Temperature.builder()
                .tempStay(tempStay)
                .tempState(tempState)
                .build();
    }
}
