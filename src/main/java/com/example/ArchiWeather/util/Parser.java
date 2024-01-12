package com.example.ArchiWeather.util;

import com.example.ArchiWeather.Models.WeatherInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Parser {


       public WeatherInfo parsOne(String url) throws Exception {
        String postUrl = "https://yandex.ru/pogoda/?";
        WeatherInfo weatherInfo = null;


        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get(postUrl+url);



        Document doc = Jsoup.parse(webDriver.getPageSource());
//       System.out.println(doc.body());
        var elements = doc.getElementsByClass("fact");
        for(Element i:elements){
            weatherInfo = new WeatherInfo(i.getElementsByClass("temp fact__temp fact__temp_size_s").text(),
                    i.getElementsByClass("term term_orient_v fact__humidity").text(),
                    i.getElementsByClass("wind-speed").text());
        }
        webDriver.quit();
        if(weatherInfo == null){
         System.out.println("Not pars");
         return null;
        }
        System.out.println("Pars");
        weatherInfo.setCoordinates(url);
        weatherInfo.setTimeSave(new Date());
        return weatherInfo;
    }



}
