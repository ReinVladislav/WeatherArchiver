package com.example.ArchiWeather.Models;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "weather_info")
public class WeatherInfo
{
    @Id
    @Column(name = "id_weather_info")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idWeatherInfo;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "humidity")
    private String humidity;
    @Column(name = "wind_speed")
    private String windSpeed;
    @Column(name = "time_save")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSave;
    @Column(name = "coordinates")
    private String coordinates;

    public WeatherInfo() {
    }

    public WeatherInfo(String temperature, String humidity, String windSpeed) {
        this.temperature = temperature;
        this.humidity = trimString(humidity);
        this.windSpeed = windSpeed;
    }
    private static String trimString(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex != -1) {
            return input.substring(0, spaceIndex);
        } else {
            return input;
        }
    }

    public Date getTimeSave() {
        return timeSave;
    }
    public String getTimeSaveStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = formatter.format(this.timeSave);
        return strDate;
    }

    public void setTimeSave(Date timeSave) {
        this.timeSave = timeSave;
    }

    public String getIdWeatherInfo() {
        return idWeatherInfo;
    }

    public void setIdWeatherInfo(String idWeatherInfo) {
        this.idWeatherInfo = idWeatherInfo;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Objects.equals(idWeatherInfo, that.idWeatherInfo) && Objects.equals(temperature, that.temperature) && Objects.equals(humidity, that.humidity) && Objects.equals(windSpeed, that.windSpeed) && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWeatherInfo, temperature, humidity, windSpeed, coordinates);
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
