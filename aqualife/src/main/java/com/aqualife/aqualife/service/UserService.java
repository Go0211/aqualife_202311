package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.User;

import java.lang.reflect.Member;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    public String insertUser(User user) throws Exception;
    public User getUserDetail(String id) throws Exception;
    public String updateUser(User user) throws Exception;
    public String deleteUser(String id) throws Exception;

}
