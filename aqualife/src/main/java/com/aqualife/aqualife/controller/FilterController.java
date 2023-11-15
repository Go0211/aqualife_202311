package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Filters;
import com.aqualife.aqualife.service.FilterService;
import com.aqualife.aqualife.service.FishbowlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    private final FishbowlService fishbowlService;

    @GetMapping("filterMain")
    public String filterMain(Model model) throws Exception {
        return "filterMain";
    }

    @GetMapping("filterSetting")
    public String filterSetting(@RequestParam String dayId,
                                Model model,
                                HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

        System.out.println("fishbowl = " + fishbowl);
        System.out.println("email = " + email);

        model.addAttribute("dayId", dayId);

        Filters filterData = filterService.getFilter(fishbowlService.getFishbowl(email, fishbowl), dayId);

        if (filterData != null) {
            model.addAttribute("filterData", filterData.getTime().split("_"));
            return "filterUpdate";
        } else {
            return "filterSetting";
        }
    }

    @PostMapping("filterSetting")
    public String filterSetting(@RequestParam String startHour,
                                @RequestParam String startMinute,
                                @RequestParam String filterRange,
                                @RequestParam String dayId,
                                HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

//        String[] strings = dayId.split("_");

        filterService.filterSetting(email, fishbowl, dayId, startHour+"_"+startMinute, Integer.parseInt(filterRange));

        return "redirect:/filterMain";
    }

    @PostMapping("filterUpdate")
    public String filterUpdate(@RequestParam String startHour,
                               @RequestParam String startMinute,
                               @RequestParam String filterRange,
                               @RequestParam String dayId,
                                HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String email = (String)session.getAttribute("email");
        String fishbowl = (String)session.getAttribute("fishbowl");

//        String[] strings = dayId.split("_");

        filterService.filterUpdate(email, fishbowl, dayId, startHour, startMinute, Integer.parseInt(filterRange));

        return "redirect:/filterMain";
    }
}
