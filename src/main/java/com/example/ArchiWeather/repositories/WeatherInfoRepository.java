package com.example.ArchiWeather.repositories;


import com.example.ArchiWeather.Models.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Integer> {

    List<WeatherInfo> findAllByCoordinates(String coordinates);
}
