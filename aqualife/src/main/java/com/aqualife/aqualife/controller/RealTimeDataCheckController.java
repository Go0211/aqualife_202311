package com.aqualife.aqualife.controller;

import com.aqualife.aqualife.model.Fishbowl;
import com.aqualife.aqualife.service.RealTimeDataCheckService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class RealTimeDataCheckController {
    private final RealTimeDataCheckService realTimeDataCheckService;

    @GetMapping("realTimeDataCheck")
    public String realTimeDataCheck(HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession(true);

        realTimeDataCheckService.checkCo2Data();
        realTimeDataCheckService.checkLightData();
        realTimeDataCheckService.checkFilterData();
        realTimeDataCheckService.checkTemperatureData();
        realTimeDataCheckService.checkPhData();

        return "ok";
    }
}
