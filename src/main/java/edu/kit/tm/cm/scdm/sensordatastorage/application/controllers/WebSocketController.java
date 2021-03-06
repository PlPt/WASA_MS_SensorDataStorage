package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.services.SensorDataStorageService;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SensorDataStorageService service;

    @Autowired
    public WebSocketController(SensorDataStorageService service) {
        this.service = service;
    }

    @MessageMapping("/registerVehicle")
    @SendTo("/response/registerVehicle")
    public void createVehicle(StaticVehicleDataResponse vehicle) {
        service.createVehicle(vehicle.getVin(), vehicle.getModel(), vehicle.getTag(), vehicle.getSeats(),
                vehicle.getTankCapacity(), vehicle.getType(), vehicle.getEndpointIdentifier());
    }


    @MessageMapping("/addDynamicVehicleData/{vin}")
    @SendTo("/response/addDynamicVehicleData")
    public void addDynamicVehicleData(@DestinationVariable String vin, DynamicVehicleDataResponse dynamicVehicleData) {
        Coordinate currentPosition = null;
        if(dynamicVehicleData.getPosition()!=null){
            currentPosition = new Coordinate(dynamicVehicleData.getPosition().getLatitude(),dynamicVehicleData.getPosition().getLongitude());
        }
        service.addDynamicData(vin, currentPosition, dynamicVehicleData.getOilPressure(),
                dynamicVehicleData.getTirePressure(), dynamicVehicleData.getTankLevel(),
                dynamicVehicleData.getTimestamp());
    }

}