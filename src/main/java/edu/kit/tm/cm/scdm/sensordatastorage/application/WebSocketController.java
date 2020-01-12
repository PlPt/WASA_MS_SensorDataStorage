package edu.kit.tm.cm.scdm.sensordatastorage.application;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.services.SensorDataStorageService;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        VehicleData data =  service.createVehicle(vehicle.getVin(),vehicle.getModel(),vehicle.getTag(),vehicle.getSeats(),
                vehicle.getTankSize(),vehicle.getVehicleType(),vehicle.getEndpointIdentifier());
    }


    @MessageMapping("/addDynamicVehicleData/{vin}")
    @SendTo("/response/addDynamicVehicleData")
    public void addDynamicVehicleData(@PathVariable String vin, DynamicVehicleDataResponse dynamicVehicleData) {
        service.pushSensorData(vin,new Coordinate(dynamicVehicleData.getPosition().getLatitude(),
                        dynamicVehicleData.getPosition().getLongitude()),dynamicVehicleData.getEnginePressure(),
                dynamicVehicleData.getTirePressure(),dynamicVehicleData.getTankLevel(),
                dynamicVehicleData.getTimestamp());
    }

    @MessageMapping("/test")
    @SendTo("/response/test")
    public String testWebSocket(String test) {
       return String.format("SensorDataStorage received '%s' as TestMessage via WebSocket",test);
    }

}