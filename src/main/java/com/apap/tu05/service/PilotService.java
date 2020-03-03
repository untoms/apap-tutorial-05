package com.apap.tu05.service;

import com.apap.tu05.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    PilotModel getPilotDetailByID(Long id);
    void delete(PilotModel pilot);
    void addPilot(PilotModel pilot);
}
