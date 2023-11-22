package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Fishbowl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface RealTimeDataCheckService {
    List<String> checkCo2Data(List<String> co2FishbowlArr) throws Exception;
    List<String> checkLightData(List<String> lightFishbowlArr) throws Exception;
    boolean checkFilterData() throws ExecutionException, InterruptedException;
    boolean checkTemperatureData() throws ExecutionException, InterruptedException;
    boolean checkPhData() throws ExecutionException, InterruptedException;
}
