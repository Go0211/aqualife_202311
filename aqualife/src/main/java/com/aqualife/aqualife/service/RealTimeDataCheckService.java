package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Fishbowl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface RealTimeDataCheckService {
    void checkCo2Data() throws Exception;
    void checkLightData() throws Exception;
    boolean checkFilterData() throws ExecutionException, InterruptedException;
    boolean checkTemperatureData() throws ExecutionException, InterruptedException;
    boolean checkPhData() throws ExecutionException, InterruptedException;
}
