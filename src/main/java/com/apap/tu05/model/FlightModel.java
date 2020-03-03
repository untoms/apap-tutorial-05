package com.apap.tu05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "flight")
public class FlightModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull
    @Size(max = 50)
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull
    @Column(name = "time", nullable = false)
    @DateTimeFormat(pattern= "yyyy-mm-dd")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_licenseNumber", referencedColumnName = "license_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private PilotModel pilot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PilotModel getPilot() {
        return pilot;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "FlightModel{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", time=" + time +
                '}';
    }
}
