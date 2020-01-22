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


    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate position;

    @Column(name = "oil_pressure")
    private Double oilPressure;

    @Column(name = "tire_pressure")
    private Double tirePressure;

    @Column(name = "tank_level")
    private Double tankLevel;

    @Column(name = "timestamp")
    private String timestamp;

    @ManyToOne(optional = false)
    private VehicleData vehicle;

    public DynamicVehicleData(Coordinate position, Double oilPressure, Double tirePressure, Double tankLevel,
                              String timestamp, VehicleData vehicle) {
        this.position = position;
        this.oilPressure = oilPressure;
        this.tirePressure = tirePressure;
        this.tankLevel = tankLevel;
        this.timestamp = timestamp;
        this.vehicle = vehicle;

    }

    public DynamicVehicleData() {

    }

    /**
     * Getter for VIN from vehicle reference
     * @return vin string from vehicle
     */
    public String getVin() {
        return vehicle.getVin();
    }
}
