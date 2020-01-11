package edu.kit.tm.cm.scdm.sensordatastorage.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class DynamicVehicleData implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "position")
    private String position;

    @Column(name = "engine_pressure")
    private float enginePressure;

    @Column(name = "tire_pressure")
    private float tirePressure;

    @Column(name = "tank_level")
    private float tankLevel;

    @Column(name = "timestamp")
    //TODO add iso
    private String timestamp;

    @ManyToOne(optional = false)
    private VehicleData car;

    public DynamicVehicleData(String position, float enginePressure, float tirePressure, float tankLevel, String timestamp) {
        this.position = position;
        this.enginePressure = enginePressure;
        this.tirePressure = tirePressure;
        this.tankLevel = tankLevel;
        this.timestamp = timestamp;
    }

    public DynamicVehicleData() {

    }

    public String getVin() {
        return car.getVin();
    }
}
