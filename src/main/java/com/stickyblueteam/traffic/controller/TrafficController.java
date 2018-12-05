package com.stickyblueteam.traffic.controller;

import com.stickyblueteam.traffic.model.Weather;
import com.stickyblueteam.traffic.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Muhammad Zubair <mzubair.ca> on 12/5/2018.
 */
@RestController

public class TrafficController {

    @Autowired
    QueryService queryService;
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseBody
    public List<Weather> getWeather() throws InterruptedException, IOException {
        return queryService.getWeather();
    }
}
