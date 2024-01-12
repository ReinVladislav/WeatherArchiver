package com.example.ArchiWeather.Controllers;

import com.example.ArchiWeather.Models.Request;
import com.example.ArchiWeather.services.RequestServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requestPage")
@CrossOrigin
public class RequestPageController {
    private final RequestServices requestServices;

    @Autowired
    public RequestPageController(RequestServices requestServices) {
        this.requestServices = requestServices;
    }



    @PostMapping()
    public ResponseEntity<HttpStatus> saveRequest(@RequestBody @Valid Request request,
                                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("No valid!!!");
            return ResponseEntity.badRequest().build();
        }
        requestServices.saveRequest(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
