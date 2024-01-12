package com.example.ArchiWeather.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.Models.WeatherInfo;
import com.example.ArchiWeather.services.RequestServices;
import com.example.ArchiWeather.services.WeatherInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestPageController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestServices requestServices;

    @Test
    void saveValidRequest1() throws Exception{

        Request request = new Request("lat=48.706355&lon=44.489114");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void saveValidRequest2() throws Exception{

        Request request = new Request("lat=0.706355&lon=1.489114");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void saveValidRequest3() throws Exception{

        Request request = new Request("lat=-8.706355&lon=4.489114");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void saveNoValidRequest1() throws Exception{

        Request request = new Request("lat=998.706355&lon=4.489114");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    void saveNoValidRequest2() throws Exception{

        Request request = new Request("lat=28.706355&lon=4.4s4");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    void saveNoValidRequest3() throws Exception{

        Request request = new Request("lat=998,706355&lon=4.489114");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/requestPage")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }












    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
