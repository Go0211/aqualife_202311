package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Temperature;

public interface TemperatureService {
    public Temperature getTemperature(String email, String fishbowl) throws Exception;
    public void temperatureSetting(String email, String fishbowl, Temperature temperature) throws Exception;
}
