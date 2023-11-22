package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Light;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LightDto {
    private String startTime;
    private String endTime;
    private boolean state;

    @Builder
    public LightDto(String startTime, String endTime, boolean state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }

    public Light toEntity() {
        return Light.builder()
                .startTime(startTime)
                .endTime(endTime)
                .state(state)
                .build();
    }
}
