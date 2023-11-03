package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Users;
import com.aqualife.aqualife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {
        boolean check;

        try {
            check = userService.login(email, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (check) {
            return "ok";
        } else {
            return "fault";
        }
    }

    @GetMapping("/insertUser")
    public String insertUser(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                               @RequestParam("phone") String phone) throws Exception{
//  public String insertMember(@RequestParam Users users) throws Exception{
        return userService.insertUser(
                UsersDto.builder()
                        .email(email)
                        .password(password)
                        .phone(phone)
                        .build()
        );
    }

    @GetMapping("/getUserDetail")
    public Users getUserDetail(@RequestParam String id) throws Exception{
        return userService.getUserDetail(id);
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("phone") String phone) throws Exception{
//    public String updateMember(@RequestParam Users users) throws Exception{
        return userService.updateUser(
                UsersDto.builder()
                        .email(email)
                        .password(password)
                        .phone(phone)
                        .build()
        );
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) throws Exception{
        return userService.deleteUser(id);
    }
}
