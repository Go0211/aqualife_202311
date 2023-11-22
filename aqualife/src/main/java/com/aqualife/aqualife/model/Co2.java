package com.aqualife.aqualife.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Co2 {
    private String startTime;
    private String endTime;
    private boolean state;

    @Builder
    public Co2(String startTime, String endTime, boolean state) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
