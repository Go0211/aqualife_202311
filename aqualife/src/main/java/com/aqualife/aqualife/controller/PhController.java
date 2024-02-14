package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Ph;
import com.aqualife.aqualife.service.FishbowlService;
import com.aqualife.aqualife.service.PhService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhController {
    private final FishbowlService fishbowlService;
    private final PhService phService;

    @GetMapping("phMain")
    public String phMain(HttpServletRequest httpServletRequest,
                         Model model,
                         @RequestParam(required = false) String f_name) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String fishbowlName;

        String email = (String)session.getAttribute("email");
        if (f_name == null) {
            fishbowlName = (String) session.getAttribute("fishbowl");
        } else {
            fishbowlName = f_name;
            session.setAttribute("fishbowl", f_name);
        }

        Fishbowl fishbowl = fishbowlService.getFishbowl(email, fishbowlName);
        model.addAttribute("fishbowlPh", fishbowl.getPh());
        model.addAttribute("fishbowlList", fishbowlService.getAllFishbowl(email));

        return "design/phMain";
    }

    @GetMapping("phSetting")
    public String phSetting(Model model,
                            HttpServletRequest httpServletRequest) throws Exception {
        List<Double> list = new ArrayList<Double>();
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        for (double i = 0; i < 10; i++) {
            for (double j = 0; j < 10; j++) {
                list.add(i + (j / 10));
            }
        }

        model.addAttribute("phSetting", phService.getPh(email, fishbowl));
        model.addAttribute("value", list);

        return "design/phSetting";
    }
    @PostMapping("phSetting")
    public String phSetting(@ModelAttribute("phSetting") Ph ph,
                            HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        phService.phSetting(email, fishbowl, ph);

        return "redirect:/phMain";
    }
}
