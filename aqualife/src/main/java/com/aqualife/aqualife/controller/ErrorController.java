package com.aqualife.aqualife.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController{
//  fishbowl parameter가 없을 때 발생하는 error처리
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleConflict(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(true);
        String fishbowl = (String)session.getAttribute("fishbowl");

        if (fishbowl == null || fishbowl.isEmpty()) {
            return "redirect:/login";
        } else {
            return "redirect:/?fishbowl="+fishbowl;
        }
    }
}
