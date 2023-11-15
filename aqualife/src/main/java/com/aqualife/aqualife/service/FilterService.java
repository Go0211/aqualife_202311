package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Filters;
import com.aqualife.aqualife.model.Fishbowl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilterService {
    public void filterSetting(String email, String fishbowl, String localDate, String localTime, int filterRange) throws Exception;

    public void filterUpdate(String email, String fishbowl, String dayId,
                             String startHour, String startMinute, int filterRange) throws Exception;

    Filters getFilter(Fishbowl fishbowl, String dayId);

    public List<Filters> getAllFilter(Fishbowl fishbowlData) throws Exception;
}
