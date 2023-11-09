package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.dto.UsersDto;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Users;
import com.aqualife.aqualife.service.FishbowlService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class FishbowlController {
    private final FishbowlService fishbowlService;

    @GetMapping("/")
    public String main(@RequestParam String fishbowl,
                       HttpServletRequest httpServletRequest,
                       Model model) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("fishbowl", fishbowl);

        String email = (String)session.getAttribute("email");

        model.addAttribute("fishbowlData",
                fishbowlService.getFishbowl(email, fishbowl));

        return "main";
    }

    @GetMapping("createFishbowl")
    public String createFishbowl() {
        return "fishbowlCreate";
    }

    @PostMapping("createFishbowl")
    public String createFishbowl(HttpServletRequest httpServletRequest,
                                 @RequestParam String fishbowl) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);

        String email = (String)session.getAttribute("email");
        System.out.println("email = " + email);

        boolean fishbowlCheck = fishbowlService.createFishbowl(email, fishbowl);

        if (fishbowlCheck) {
            return "redirect:/fishbowlList";
        } else {
            return "fault";
        }
    }

    @GetMapping("fishbowlList")
    public String fishbowlList(Model model,
                               HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");

        ArrayList<Fishbowl> fishbowlList = fishbowlService.getAllFishbowl(email);

        model.addAttribute("fishbowlList", fishbowlList);

        return "fishbowlList";
    }

    @GetMapping("fishbowlDelete")
    public String fishbowlDelete(@RequestParam String fishbowl,
                               HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");

        boolean checkDelete = fishbowlService.fishbowlDelete(email, fishbowl);

        if (checkDelete) {
            return "redirect:/fishbowlList";
        } else {
            return "fault";
        }
    }

    @GetMapping("fishbowlRename")
    public String fishbowlRename(Model model,
                                 @RequestParam String fishbowl) {
        model.addAttribute("fishbowlName", fishbowl);

        return "fishbowlRename";
    }


    @PostMapping("fishbowlRename")
    public String fishbowlRename(@RequestParam String fishbowl_beforeName,
                                 @RequestParam String fishbowl_afterName,
                                 HttpServletRequest httpServletRequest) throws Exception{
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");

        fishbowlService.changeFishbowlName(email, fishbowl_beforeName, fishbowl_afterName);

        return "redirect:/fishbowlList";
    }
}