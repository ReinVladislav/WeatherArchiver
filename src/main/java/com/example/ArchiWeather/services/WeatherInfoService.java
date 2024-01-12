package com.example.ArchiWeather.services;

import com.example.ArchiWeather.Models.WeatherInfo;
import com.example.ArchiWeather.repositories.WeatherInfoRepository;
import com.example.ArchiWeather.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherInfoService {
    private final WeatherInfoRepository weatherInfoRepository;
    private final Parser parser;

    @Autowired
    public WeatherInfoService(WeatherInfoRepository weatherInfoRepository, Parser parser) {
        this.weatherInfoRepository = weatherInfoRepository;
        this.parser = parser;
    }

    @Transactional
    public List<WeatherInfo> showAllInfo(){
        return weatherInfoRepository.findAll();
    }

    @Transactional
    public List<WeatherInfo> showAllInfoByCoordinates(String coordinates){
        return weatherInfoRepository.findAllByCoordinates(coordinates);
    }

    @Transactional
    public void saveWeatherInfo(WeatherInfo weatherInfo){
        weatherInfoRepository.save(weatherInfo);
    }
}
