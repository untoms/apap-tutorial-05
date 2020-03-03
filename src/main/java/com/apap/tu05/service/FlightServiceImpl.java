package com.apap.tu05.service;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.repository.FlightDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightDb flightDb;

    @Autowired
    public FlightServiceImpl(FlightDb flightDb) {
        this.flightDb = flightDb;
    }

    @Override
    public void addFlight(FlightModel flight) {
        flightDb.save(flight);
    }

    @Override
    public void deleteFlight(FlightModel flightModel) {
        flightDb.delete(flightModel);
    }

    @Override
    public FlightModel getById(Long id) {
        return flightDb.findById(id).get();
    }
}
