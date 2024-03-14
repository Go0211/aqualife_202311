package com.aqualife.aqualife.service;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Light;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LightService {
    public List<Light> getAllLight(Fishbowl fishbowlData);

    public void lightChange(String email, String fishbowl, int lightStat, Times times) throws Exception;

    public void lightDelete(String email, String fishbowl, int lightIndex) throws Exception;

    public void lightCreate(String email, String fishbowl, Times times) throws Exception;

    public void lightStateChange(String email, String fishbowl, int lightIndex) throws Exception;

    void lightListStateChange(String email, String fishbowl, int lightIndex) throws ExecutionException, InterruptedException;

    void lightStateChange(String email, String fishbowl, boolean state) throws Exception;
}
