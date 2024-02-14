package com.aqualife.aqualife.dto;

import com.aqualife.aqualife.model.Filters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.html.HTMLImageElement;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    private String day;
    private String time;
    private int filterRange;

    public FilterDto(String day, String time, int filterRange) {
        this.day = day;
        this.time = time;
        this.filterRange = filterRange;
    }

    public Filters toEntity() {
        return Filters.builder()
                .day(day)
                .time(time)
                .filterRange(filterRange)
                .build();
    }
}
