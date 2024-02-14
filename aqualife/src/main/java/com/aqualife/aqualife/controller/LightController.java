package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.model.Light;
import com.aqualife.aqualife.service.FishbowlService;
import com.aqualife.aqualife.service.LightService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LightController {
    private final LightService lightService;
    private final FishbowlService fishbowlService;

    @GetMapping("lightMain")
    public String lightMain(HttpServletRequest httpServletRequest,
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
//        model.addAttribute("fishbowlLight", fishbowl.getLight());
        model.addAttribute("fishbowlList", fishbowlService.getAllFishbowl(email));
        model.addAttribute("lightList", lightService.getAllLight(fishbowl));

        return "light/lightMain";
    }

    @GetMapping("lightCreate")
    public String lightCreate() {
        return "light/lightCreate";
    }

    @PostMapping("lightCreate")
    public String lightCreate(@ModelAttribute("lightTime") Times times,
                            HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        if (times.getStartAmPm().equals("pm")) {
            int t = Integer.parseInt(times.getStartTimeHour()) + 12;
            times.setStartTimeHour(String.valueOf(t));
        }
        if (times.getEndAmPm().equals("pm")) {
            int t = Integer.parseInt(times.getEndTimeHour()) + 12;
            times.setEndTimeHour(String.valueOf(t));
        }

        times.setStartTime(times.getStartTimeHour()+":"+times.getStartTimeMinute());
        times.setEndTime(times.getEndTimeHour()+":"+times.getEndTimeMinute());

        lightService.lightCreate(email, fishbowl, times);

        return "redirect:/lightMain";
    }

    @GetMapping("lightstatechange")
    public String lightStateChange(@RequestParam String lightState, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        fishbowlService.changeFishbowlLightState(email, fishbowl, Boolean.valueOf(lightState));

        return "redirect:/lightMain";
    }

//    @GetMapping("lightList")
//    public String lightList(HttpServletRequest httpServletRequest,
//                          Model model) throws Exception {
//        HttpSession session = httpServletRequest.getSession(true);
//        String email = (String)session.getAttribute("email");
//        String fishbowl = (String)session.getAttribute("fishbowl");
//
//        Fishbowl fishbowlData = fishbowlService.getFishbowl(email, fishbowl);
//        model.addAttribute("lightList", lightService.getAllLight(fishbowlData));
//
//        return "light/lightList";
//    }

    @GetMapping("lightSetting")
    public String lightSetting(Model model, @RequestParam String startTime,
                             @RequestParam String endTime, @RequestParam String state,
                             @RequestParam String lightIndex) {
        System.out.println();

        model.addAttribute("light", new Light(startTime, endTime, Boolean.valueOf(state)));
        model.addAttribute("lightIndex", lightIndex);

        return "light/lightSetting";
    }

    @PostMapping("lightSetting")
    public String lightSetting(@ModelAttribute("lightTime")Times times,
                             @RequestParam int lightIndex,
                             HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        if (times.getStartAmPm().equals("pm")) {
            int t = Integer.parseInt(times.getStartTimeHour()) + 12;
            times.setStartTimeHour(String.valueOf(t));
        }
        if (times.getEndAmPm().equals("pm")) {
            int t = Integer.parseInt(times.getEndTimeHour()) + 12;
            times.setEndTimeHour(String.valueOf(t));
        }

        times.setStartTime(times.getStartTimeHour()+":"+times.getStartTimeMinute());
        times.setEndTime(times.getEndTimeHour()+":"+times.getEndTimeMinute());

        lightService.lightChange(email, fishbowl, lightIndex, times);

        return "redirect:/lightMain";
    }

    @GetMapping("lightStateChange")
    public String lightStateChange(@RequestParam int lightIndex,
                                 HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        lightService.lightStateChange(email, fishbowl, lightIndex);

        return "redirect:/lightMain";
    }

    @GetMapping("lightDelete")
    public String lightDelete(@RequestParam int lightIndex,
                            HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        lightService.lightDelete(email, fishbowl, lightIndex);

        return "redirect:/lightMain";
    }
}
