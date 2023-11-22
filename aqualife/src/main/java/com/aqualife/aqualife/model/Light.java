package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Light {
    private String startTime;
    private String endTime;
    private boolean state;

    @Builder
    public Light(String startTime, String endTime, boolean state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
