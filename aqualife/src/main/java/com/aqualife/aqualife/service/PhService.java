package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Ph;

public interface PhService {
    void phSetting(String email, String fishbowl, Ph ph) throws Exception;

    public Ph getPh(String email, String fishbowl) throws Exception;
}
