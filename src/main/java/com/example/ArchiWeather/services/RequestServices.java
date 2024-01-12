package com.example.ArchiWeather.services;

import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestServices {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServices(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Transactional
    public void saveRequest(Request newRequest){
        if(requestRepository.findAllByCoordinates(newRequest.getCoordinates()).isEmpty()){
            requestRepository.save(newRequest);
        }
    }
    @Transactional
    public List<Request> showAllRequest(){
        return requestRepository.findAll();
    }
}
