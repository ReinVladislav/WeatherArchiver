package com.example.ArchiWeather.Controllers;

import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.Models.WeatherInfo;
import com.example.ArchiWeather.services.RequestServices;
import com.example.ArchiWeather.services.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/showPage")
@CrossOrigin
public class ShowPageController {

    private final WeatherInfoService weatherInfoService;
    private final RequestServices requestServices;
    @Autowired
    public ShowPageController(WeatherInfoService weatherInfoService, RequestServices requestServices) {
        this.weatherInfoService = weatherInfoService;
        this.requestServices = requestServices;
    }



    @GetMapping("/byCoordinates/{coordinates}")
    public List<WeatherInfo> getAllWeatherInfo(@PathVariable("coordinates") String coordinates){
        List<WeatherInfo> weatherInfoList = weatherInfoService.showAllInfoByCoordinates(coordinates);
        return weatherInfoList;
    }

    @GetMapping("/getRequest")
    public List<Request> getRequest(){
        List<Request> requests = requestServices.showAllRequest();
        if(requests.isEmpty()){
            requests.add(new Request("Нет сохранённых координат"));
            return requests;
        }
        return requestServices.showAllRequest();
    }


}
