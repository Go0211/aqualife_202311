package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Filters;
import com.aqualife.aqualife.model.Fishbowl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FilterService {
    public void filterSetting(String email, String fishbowl, int dayDecimalCode, String time, int filterRange) throws Exception;

    public void filterUpdate(String email, String fishbowl, int dayDecimalCode,
                             String time, int filterRange) throws Exception;

    Filters getFilter(Fishbowl fishbowl);

    int nowFilterValue(String email, String fishbowlName) throws ExecutionException, InterruptedException;
}
