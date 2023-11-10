package com.aqualife.aqualife.service;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Co2Service {

    public List<Co2> getAllCo2(Fishbowl fishbowlData);

    public void co2Change(String email, String fishbowl, int co2Stat, Times times) throws Exception;

    public void co2Delete(String email, String fishbowl, int co2Index) throws Exception;

    public void co2Create(String email, String fishbowl, Times times) throws Exception;

    public void co2StateChange(String email, String fishbowl, int co2Index) throws Exception;
}
