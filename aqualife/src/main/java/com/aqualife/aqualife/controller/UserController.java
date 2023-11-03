package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.User;
import com.aqualife.aqualife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/svc/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/insertUser")
    public String insertMember(@RequestParam User user) throws Exception{
        return userService.insertUser(user);
    }

    @GetMapping("/getUserDetail")
    public User getMemberDetail(@RequestParam String id) throws Exception{
        return userService.getUserDetail(id);
    }

    @GetMapping("/updateUser")
    public String updateMember(@RequestParam User user) throws Exception{
        return userService.updateUser(user);
    }

    @GetMapping("/deleteUser")
    public String deleteMember(@RequestParam String id) throws Exception{
        return userService.deleteUser(id);
    }
}
