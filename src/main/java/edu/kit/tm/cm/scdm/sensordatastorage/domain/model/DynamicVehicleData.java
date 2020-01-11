package edu.kit.tm.cm.scdm.sensordatastorage.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class DynamicVehicleData implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @OneToOne(cascade=CascadeType.ALL)
    private Coordinate position;

    @Column(name = "engine_pressure")
    private float enginePressure;

    @Column(name = "tire_pressure")
    private float tirePressure;

    @Column(name = "tank_level")
    private float tankLevel;

    @Column(name = "timestamp")
    private String timestamp;

    @ManyToOne(optional = false)
    private VehicleData vehicle;

    public DynamicVehicleData(Coordinate position, float enginePressure, float tirePressure, float tankLevel,
                              String timestamp,VehicleData vehicle) {
        this.position = position;
        this.enginePressure = enginePressure;
        this.tirePressure = tirePressure;
        this.tankLevel = tankLevel;
        this.timestamp = timestamp;
        this.vehicle = vehicle;

    }

    public DynamicVehicleData() {

    }

    public String getVin() {
        return vehicle.getVin();
    }
}
