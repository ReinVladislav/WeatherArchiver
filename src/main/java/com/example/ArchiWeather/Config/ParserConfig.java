package com.example.ArchiWeather.Config;

import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.Models.WeatherInfo;
import com.example.ArchiWeather.services.RequestServices;
import com.example.ArchiWeather.services.WeatherInfoService;
import com.example.ArchiWeather.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class ParserConfig {
    private final RequestServices requestServices;
    private final WeatherInfoService weatherInfoService;
    private final Parser parser;

    @Autowired
    public ParserConfig(RequestServices requestServices, WeatherInfoService weatherInfoService, Parser parser) {
        this.requestServices = requestServices;
        this.weatherInfoService = weatherInfoService;
        this.parser = parser;
    }


    @Scheduled(fixedRate = 100000)
    public void parsAll(){

        List<Request> requestList = requestServices.showAllRequest();
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        WeatherInfo newWeatherInfo;

        try {
            for(Request i:requestList){
                newWeatherInfo = parser.parsOne(i.getCoordinates());
                if(newWeatherInfo != null){
                    weatherInfoList.add(newWeatherInfo);
                }

//                Thread.sleep(10000);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Parsing " + weatherInfoList.size() + " elements");
        for(WeatherInfo i:weatherInfoList){
            weatherInfoService.saveWeatherInfo(i);
        }
    }
}
