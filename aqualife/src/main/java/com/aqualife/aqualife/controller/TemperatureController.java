package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Ph;
import com.aqualife.aqualife.model.Temperature;
import com.aqualife.aqualife.service.FishbowlService;
import com.aqualife.aqualife.service.PhService;
import com.aqualife.aqualife.service.TemperatureService;
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
public class TemperatureController {
    private final FishbowlService fishbowlService;
    private final TemperatureService temperatureService;

    @GetMapping("temperatureMain")
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
        model.addAttribute("temperature", fishbowl.getTemperature());
        model.addAttribute("fishbowlList", fishbowlService.getAllFishbowl(email));

        return "design/temperatureMain";
    }

    @GetMapping("temperatureSetting")
    public String temperatureSetting(Model model,
                            HttpServletRequest httpServletRequest) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        model.addAttribute("temperatureSetting",
                temperatureService.getTemperature(email, fishbowl));

        return "design/temperatureSetting";
    }

    @PostMapping("temperatureSetting")
    public String temperatureSetting(@ModelAttribute("temperatureSetting") Temperature temperature,
                            HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        temperatureService.temperatureSetting(email, fishbowl, temperature);

        return "redirect:/temperatureMain";
    }
}
