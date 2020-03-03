package com.apap.tu05.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "fly_hour", nullable = false)
    private int flyHour;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<FlightModel> pilotFlight = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlyHour() {
        return flyHour;
    }

    public void setFlyHour(int flyHour) {
        this.flyHour = flyHour;
    }

    public List<FlightModel> getPilotFlight() {
        return pilotFlight;
    }

    public void setPilotFlight(List<FlightModel> pilotFlight) {
        this.pilotFlight = pilotFlight;
    }

    @Override
    public String toString() {
        return "PilotModel{" +
                "id=" + id +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", flyHour=" + flyHour +
                ", pilotFlight=" + pilotFlight +
                '}';
    }
}
