package com.aqualife.aqualife.service;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Users;

public interface UserService {

    public boolean login(String email, String password) throws Exception;
    public UsersDto getUsersDto(Users users) throws Exception;
    public String insertUser(UsersDto usersDto) throws Exception;
    public Users getUserDetail(String id) throws Exception;
    public String updateUser(UsersDto usersDto) throws Exception;
    public String deleteUser(String id) throws Exception;

}
