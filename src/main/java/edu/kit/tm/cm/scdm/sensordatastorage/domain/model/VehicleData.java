package edu.kit.tm.cm.scdm.sensordatastorage.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class VehicleData implements Serializable {

    @Id
    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "tank_size", nullable = false)
    private int tankSize;

    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "endpoint_identifier")
    private String endpointIdentifier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", orphanRemoval = true)
    @OrderBy("timestamp desc")
    private List<DynamicVehicleData> dynamicVehicleDataList;


    public VehicleData(String vin, String model, String tag, int seats, int tankSize, VehicleType vehicleType,
               String endpointIdentifier) {
        this.vin = vin;
        this.model = model;
        this.tag = tag;
        this.seats = seats;
        this.tankSize = tankSize;
        this.vehicleType = vehicleType;
        this.endpointIdentifier = endpointIdentifier;
    }

    public VehicleData() {

    }
}
