package com.aqualife.aqualife.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Filters {
    private String date;
    private String time;
    private int filterRange;

    @Builder
    public Filters(String date, String time, int filterRange) {
        this.date = date;
        this.time = time;
        this.filterRange = filterRange;
    }
}
