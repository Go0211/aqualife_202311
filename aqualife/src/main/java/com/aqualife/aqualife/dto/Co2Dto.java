package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Co2Dto {
    private String startTime;
    private String endTime;
    private boolean state;

    @Builder
    public Co2Dto(String startTime, String endTime, boolean state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }

    public Co2 toEntity() {
        return Co2.builder()
                .startTime(startTime)
                .endTime(endTime)
                .state(state)
                .build();
    }
}
