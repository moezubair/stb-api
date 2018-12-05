package com.stickyblueteam.traffic.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Muhammad Zubair <mzubair.ca> on 12/5/2018.
 */
@RestController

public class TrafficController {
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseBody
    public String getWeather() {
        return "";
    }
}
