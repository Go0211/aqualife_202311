package com.aqualife.aqualife.service;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Users;

public interface UserService {
    public String login(String email, String password) throws Exception;
    public UsersDto getUsersDto(Users users) throws Exception;
    public boolean join(UsersDto usersDto) throws Exception;
    public Users getUserDetail(String id) throws Exception;
    public String updateUser(UsersDto usersDto) throws Exception;
    public String deleteUser(String id) throws Exception;

    public UsersDto checkUser(String email, String phone) throws Exception;

    public boolean changePassword(String beforePassword, String newPassword, String email, String phone) throws Exception;
}
