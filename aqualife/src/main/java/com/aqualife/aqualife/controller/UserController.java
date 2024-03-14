package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Users;
import com.aqualife.aqualife.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);

        String check = userService.login(email, password);

        if (check != null && check.equals("true")) {
            session.setAttribute("email", email);
            session.setMaxInactiveInterval(-1);
            return "redirect:/fishbowlList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(true);
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("passwordCheck") String passwordCheck,
                       @RequestParam("phone") String phone) throws Exception{
//  public String insertMember(@RequestParam Users users) throws Exception{
        if (password.equals(passwordCheck)) {
            boolean check = userService.join(
                    UsersDto.builder()
                            .email(email)
                            .password(password)
                            .phone(phone)
                            .build()
            );

            if (check) {
                return "redirect:/login";
            } else {
                return "fault";
            }

        } else {
            return "fault";
        }
    }

//    @GetMapping("findId")
//    public String findId(Model model) throws Exception {
//        return "findId";
//    }

//    @PostMapping("findId")
//    public String findId(@RequestParam("phone") String phone) {
//        return "findId";
//    }

    @GetMapping("/findPw")
    public String findPw() {
        return "user/findPw";
    }

    @PostMapping("/findPw")
    public String findPw(@RequestParam String email,
                         @RequestParam String phone,
                         Model model) throws Exception {
        UsersDto checkUser = userService.checkUser(email, phone);

        model.addAttribute("usersDto", checkUser);

        if (checkUser == null) {
            return "fault";
        } else {
            return "user/reSettingPw";
        }
    }

    @PostMapping("/reSettingPw")
    public String reSettingPw(@RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String beforePassword,
                              @RequestParam String newPassword) throws Exception {
        System.out.println("email = " + email);
        System.out.println("beforePassword = " + beforePassword);
        System.out.println("newPassword = " + newPassword);
        System.out.println("phone = " + phone);

        boolean check = userService.changePassword(beforePassword, newPassword, email, phone);

        if (check) {
            return "ok";
        } else {
            return "fault";
        }
    }

    @GetMapping("/getUserDetail")
    public Users getUserDetail(@RequestParam String email) throws Exception{
        return userService.getUserDetail(email);
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
