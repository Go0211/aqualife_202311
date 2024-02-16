package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Filters;
import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.service.FilterService;
import com.aqualife.aqualife.service.FishbowlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    private final FishbowlService fishbowlService;

    @GetMapping("filterMain")
    public String filterMain(HttpServletRequest httpServletRequest,
                             Model model,
                             @RequestParam(required = false) String f_name) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);
        String fishbowlName;

        String email = (String) session.getAttribute("email");
        if (f_name == null) {
            fishbowlName = (String) session.getAttribute("fishbowl");
        } else {
            fishbowlName = f_name;
            session.setAttribute("fishbowl", f_name);
        }

        Fishbowl fishbowl = fishbowlService.getFishbowl(email, fishbowlName);
//        model.addAttribute("fishbowlCo2", fishbowl.getState().get(0));
        model.addAttribute("fishbowlList", fishbowlService.getAllFishbowl(email));
        model.addAttribute("filter", filterService.getFilter(fishbowl));

        return "design/filterMain";
    }

    @GetMapping("filterSetting")
    public String filterSetting(Model model, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String) session.getAttribute("email");
        String fishbowl = (String) session.getAttribute("fishbowl");

        Filters filterData = filterService.getFilter(fishbowlService.getFishbowl(email, fishbowl));

        model.addAttribute("filter", filterData);

        return "design/filterSetting";
    }

    @PostMapping("filterSetting")
    public String filterSetting(@RequestParam String hours,
                                @RequestParam String minutes,
                                @RequestParam String inputampm,
                                @RequestParam String filterRange,
                                @RequestParam String day,
                                HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String) session.getAttribute("email");
        String fishbowl = (String) session.getAttribute("fishbowl");

        int h = Integer.parseInt(hours);

        if (inputampm.equals("pm")) {
            h += 12;
        }

        String time = h + ":" + minutes;

        int dayDecimalCode = Integer.parseInt(day);

        filterService.filterSetting(email, fishbowl, dayDecimalCode, time, Integer.parseInt(filterRange));

        return "redirect:/filterMain";
    }

    @PostMapping("filterUpdate")
    public String filterUpdate(@RequestParam String hours,
                               @RequestParam String minutes,
                               @RequestParam String filterRange,
                               @RequestParam String day,
                               HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String) session.getAttribute("email");
        String fishbowl = (String) session.getAttribute("fishbowl");

        String time = hours + ":" + minutes;
        int dayDecimalCode = Integer.parseInt(day);

        filterService.filterUpdate(email, fishbowl, dayDecimalCode, time, Integer.parseInt(filterRange));

        return "redirect:/filterMain";
    }
}
