package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Light {
    private int startTime;
    private int endTime;
    private boolean state;

    @Builder
    public Light(int startTime, int endTime, boolean state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
