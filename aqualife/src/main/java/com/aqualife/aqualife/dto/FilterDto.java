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
    private String date;
    private String time;
    private int filterRange;

    public FilterDto(String date, String time, int filterRange) {
        this.date = date;
        this.time = time;
        this.filterRange = filterRange;
    }

    public Filters toEntity() {
        return Filters.builder()
                .date(date)
                .time(time)
                .filterRange(filterRange)
                .build();
    }
}
