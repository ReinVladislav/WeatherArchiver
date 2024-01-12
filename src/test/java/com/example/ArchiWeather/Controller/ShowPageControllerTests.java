package com.example.ArchiWeather.Controller;



import com.example.ArchiWeather.Controllers.ShowPageController;
import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.Models.WeatherInfo;
import com.example.ArchiWeather.services.RequestServices;
import com.example.ArchiWeather.services.WeatherInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShowPageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestServices requestServices;
    @MockBean
    private WeatherInfoService weatherInfoService;

    @Autowired
    private ShowPageController showPageController;


    @Test
    void get6Request() throws Exception{
        List<Request> requests = new ArrayList<>();
        requests.add(new Request("214"));
        requests.add(new Request("215"));
        requests.add(new Request("216"));
        requests.add(new Request("217"));
        requests.add(new Request("218"));
        requests.add(new Request("219"));


        when(requestServices.showAllRequest()).thenReturn(requests);

        this.mockMvc.perform(get("/showPage/getRequest")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates").value("214"))
                .andExpect(jsonPath("$[1].coordinates").value("215"))
                .andExpect(jsonPath("$[2].coordinates").value("216"))
                .andExpect(jsonPath("$[3].coordinates").value("217"))
                .andExpect(jsonPath("$[4].coordinates").value("218"))
                .andExpect(jsonPath("$[5].coordinates").value("219"));
    }

    @Test
    void get1Request() throws Exception{
        List<Request> requests = new ArrayList<>();
        requests.add(new Request("1243246"));
        when(requestServices.showAllRequest()).thenReturn(requests);
        this.mockMvc.perform(get("/showPage/getRequest")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates").value("1243246"));
    }
    @Test
    void get0Request() throws Exception{
        List<Request> requests = new ArrayList<>();
        when(requestServices.showAllRequest()).thenReturn(requests);
        this.mockMvc.perform(get("/showPage/getRequest")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].coordinates").value("Нет сохранённых координат"));
    }

    @Test
    void get5WeatherInfo() throws Exception {

        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        weatherInfoList.add(new WeatherInfo("+1", "23%", "8m/s"));
        weatherInfoList.add(new WeatherInfo("+21", "5%", "4m/s"));
        weatherInfoList.add(new WeatherInfo("+3", "3%", "2m/s"));
        weatherInfoList.add(new WeatherInfo("+5", "23%", "14m/s"));
        weatherInfoList.add(new WeatherInfo("+16", "2%", "6m/s"));

        String coordinates = "lat=48.706355&lon=44.489114";

        when(weatherInfoService.showAllInfoByCoordinates(coordinates)).thenReturn(weatherInfoList);
        this.mockMvc.perform(get("/showPage/byCoordinates/"+coordinates))
                .andDo(print());
//                .andExpect(jsonPath("$[0].temperature").value("+1"))
//                .andExpect(jsonPath("$[1].temperature").value("+21"))
//                .andExpect(jsonPath("$[2].temperature").value("+3"))
//                .andExpect(jsonPath("$[3].temperature").value("+5"))
//                .andExpect(jsonPath("$[4].temperature").value("+16"));
    }
    @Test
    void get1WeatherInfo() throws Exception {

        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        weatherInfoList.add(new WeatherInfo("+1", "23%", "8m/s"));

        String coordinates = "lat=48.706355&lon=44.489114";
        when(weatherInfoService.showAllInfoByCoordinates(coordinates)).thenReturn(weatherInfoList);
        this.mockMvc.perform(get("/showPage/byCoordinates/"+coordinates))
                .andDo(print())
                .andExpect(jsonPath("$[0].temperature").value("+1"));
    }
    @Test
    void get0WeatherInfo() throws Exception {

        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        String coordinates = "lat=48.706355&lon=44.489114";
        when(weatherInfoService.showAllInfoByCoordinates(coordinates)).thenReturn(weatherInfoList);
        this.mockMvc.perform(get("/showPage/byCoordinates/"+coordinates))
                .andDo(print()).andExpect(status().isOk());
    }

}
