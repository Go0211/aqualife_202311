package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.Fishbowl;

import java.util.ArrayList;
import java.util.List;

public interface FishbowlService {
    public boolean createFishbowl(String email, String fishbowl) throws Exception;
    public Fishbowl getFishbowl(String email,  String fishbowl) throws Exception;
    public ArrayList<Fishbowl> getAllFishbowl(String email) throws Exception;

    public boolean fishbowlDelete(String email, String fishbowl) throws Exception;

    public boolean changeFishbowlName
            (String email, String fishbowl_beforeName, String fishbowl_afterName) throws Exception;
}
