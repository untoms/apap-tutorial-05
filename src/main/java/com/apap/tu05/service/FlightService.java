package com.apap.tu05.service;

import com.apap.tu05.model.FlightModel;

public interface FlightService {
    void addFlight(FlightModel flight);
    void deleteFlight(FlightModel flightModel);
    FlightModel getById(Long id);
}
