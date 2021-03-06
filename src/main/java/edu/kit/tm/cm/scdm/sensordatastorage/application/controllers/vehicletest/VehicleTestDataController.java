package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers.vehicletest;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.services.SensorDataStorageService;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class VehicleTestDataController implements VehicleTestApi {


    private final SensorDataStorageService service;

    @Autowired
    public VehicleTestDataController(SensorDataStorageService service) {
        this.service = service;
    }

    @Override
    public void createVehicle(StaticVehicleDataResponse vehicle) {
        VehicleData data = service.createVehicle(vehicle.getVin(), vehicle.getModel(), vehicle.getTag(),
                vehicle.getSeats(), vehicle.getTankCapacity(), vehicle.getType(),
                vehicle.getEndpointIdentifier());
    }

    @Override
    public void addDynamicVehicleData(String vin, DynamicVehicleDataResponse dynamicVehicleData) {
        Coordinate currentPosition = null;
        if(dynamicVehicleData.getPosition()!=null){
            currentPosition = new Coordinate(dynamicVehicleData.getPosition().getLatitude(),dynamicVehicleData.getPosition().getLongitude());
        }

        service.addDynamicData(vin, currentPosition, dynamicVehicleData.getOilPressure(),
                dynamicVehicleData.getTirePressure(), dynamicVehicleData.getTankLevel(),
                dynamicVehicleData.getTimestamp());

    }
}
