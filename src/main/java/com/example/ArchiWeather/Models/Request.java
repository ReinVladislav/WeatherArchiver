package com.example.ArchiWeather.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @Column(name = "id_request")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequest;
    @Pattern(regexp = "lat=\\W?\\d{1,2}.\\d+&lon=\\W?\\d{1,2}.\\d+")
    @Column(name = "coordinates")
    private String coordinates;
    @Column(name="name")
    private String name;

    public Request() {
    }

    public Request(@Pattern(regexp = "lat=\\W?\\d{1,2}.\\d+&lon=\\W?\\d{1,2}.\\d+") String coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public Request(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return idRequest == request.idRequest && coordinates == request.coordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest, coordinates);
    }

    @Override
    public String toString() {
        return "Request{" +
                "idRequest=" + idRequest +
                ", coordinates=" + coordinates +
                '}';
    }
}
