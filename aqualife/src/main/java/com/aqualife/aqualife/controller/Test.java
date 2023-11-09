package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.service.FishbowlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class Test {
    private final FishbowlService fishbowlService;

    @GetMapping("fishbowlAllState")
    public String createFishbowl(HttpServletRequest httpServletRequest, Model model) throws Exception {
        Fishbowl fishbowl = fishbowlService.getFishbowl("test", "ab");
        model.addAttribute("test", fishbowl);

        return "test";
    }
}
