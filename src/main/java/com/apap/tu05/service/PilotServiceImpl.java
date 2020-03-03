package com.apap.tu05.service;

import com.apap.tu05.model.PilotModel;
import com.apap.tu05.repository.PilotDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {

    private final PilotDb pilotDb;

    @Autowired
    public PilotServiceImpl(PilotDb pilotDb) {
        this.pilotDb = pilotDb;
    }

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
        return pilotDb.findByLicenseNumber(licenseNumber);
    }

    @Override
    public void addPilot(PilotModel pilot) {
        pilotDb.save(pilot);
    }

    @Override
    public PilotModel getPilotDetailByID(Long id) {
        return pilotDb.findById(id).get();
    }

    @Override
    public void delete(PilotModel pilot) {
        pilotDb.delete(pilot);
    }
}
