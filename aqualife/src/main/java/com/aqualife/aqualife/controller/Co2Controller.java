package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.Data.Times;
import com.aqualife.aqualife.model.Co2;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.service.Co2Service;
import com.aqualife.aqualife.service.FishbowlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Co2Controller {
    private final Co2Service co2Service;
    private final FishbowlService fishbowlService;

    @GetMapping("co2Main")
    public String co2Main(HttpServletRequest httpServletRequest,
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
//        model.addAttribute("fishbowlCo2", fishbowl.getState().get(0));
        model.addAttribute("fishbowlList", fishbowlService.getAllFishbowl(email));
        model.addAttribute("co2List", co2Service.getAllCo2(fishbowl));


        return "co2/co2Main";
    }

    @GetMapping("co2statechange")
    public String co2statechange(String co2state, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        System.out.println(co2state);

        fishbowlService.changeFishbowlCo2State(email, fishbowl, Boolean.parseBoolean(co2state));

        return "redirect:/co2Main";
    }

    @GetMapping("co2Create")
    public String co2Create() {
        return "co2/co2Create";
    }

    @PostMapping("co2Create")
    public String co2Create(@ModelAttribute("co2Time")Times times,
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

        co2Service.co2Create(email, fishbowl, times);

        return "redirect:/co2Main";
    }

//    @GetMapping("co2List")
//    public String co2List(HttpServletRequest httpServletRequest,
//                          Model model) throws Exception {
//        HttpSession session = httpServletRequest.getSession(true);
//        String email = (String)session.getAttribute("email");
//        String fishbowl = (String)session.getAttribute("fishbowl");
//
//        Fishbowl fishbowlData = fishbowlService.getFishbowl(email, fishbowl);
//        model.addAttribute("co2List", co2Service.getAllCo2(fishbowlData));
//
//        return "co2/co2List";
//    }

    @GetMapping("co2Setting")
    public String co2Setting(Model model,
                             @RequestParam String startTime,
                             @RequestParam String endTime,
                             @RequestParam String state,
                             @RequestParam String co2Index) {

        model.addAttribute("co2", new Co2(startTime, endTime, Boolean.valueOf(state)));
        model.addAttribute("co2Index", co2Index);

        return "co2/co2Setting";
    }

    @PostMapping("co2Setting")
    public String co2Setting(@ModelAttribute("co2Time")Times times,
                             @RequestParam String co2Index,
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


        co2Service.co2Change(email, fishbowl, Integer.parseInt(co2Index), times);

        return "redirect:/co2Main";
    }

    @GetMapping("co2ListStateChangeMain")
    public String co2ListStateChangeMain(@RequestParam int co2Index,
                                 HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        co2Service.co2ListStateChange(email, fishbowl, co2Index);

        return "redirect:/co2Main";
    }

    @GetMapping("co2ListStateChange")
    public String co2ListStateChange(@RequestParam int co2Index,
                                     HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        co2Service.co2ListStateChange(email, fishbowl, co2Index);

        return "redirect:/co2List";
    }

    @GetMapping("co2Delete")
    public String co2Delete(@RequestParam int co2Index,
                            HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        co2Service.co2Delete(email, fishbowl, co2Index);

        return "redirect:/co2Main";
    }
}
