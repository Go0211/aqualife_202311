package com.aqualife.aqualife.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Filters {
    private String day;
    private String time;
    private int filterRange;

    @Builder
    public Filters(String day, String time, int filterRange) {
        this.day = day;
        this.time = time;
        this.filterRange = filterRange;
    }
}
