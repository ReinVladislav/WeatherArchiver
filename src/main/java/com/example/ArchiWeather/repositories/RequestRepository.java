package com.example.ArchiWeather.repositories;

import com.example.ArchiWeather.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findAllByCoordinates(String coordinates);
}
