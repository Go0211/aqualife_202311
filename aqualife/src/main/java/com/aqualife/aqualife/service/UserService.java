package com.aqualife.aqualife.service;

import com.aqualife.aqualife.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    List<User> getUsers() throws ExecutionException, InterruptedException;

}
