package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers;

import edu.kit.tm.cm.scdm.sensordatastorage.application.controllers.api.SensorDataStorageApi;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.DynamicVehicleDataMapper;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.VehicleDataMapper;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.services.SensorDataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SensorDataStorageApiController implements SensorDataStorageApi {

    private final VehicleDataMapper vehicleMapper;
    private final DynamicVehicleDataMapper dynamicVehicleDataMapper;
    private final SensorDataStorageService service;

    @Autowired
    public SensorDataStorageApiController(SensorDataStorageService service, VehicleDataMapper vehicleDataMapper,
                                          DynamicVehicleDataMapper dynamicVehicleDataMapper) {
        this.service = service;
        this.vehicleMapper = vehicleDataMapper;
        this.dynamicVehicleDataMapper = dynamicVehicleDataMapper;
    }

    @Override
    public List<StaticVehicleDataResponse> getVehicles() {
        return vehicleMapper.toResponseList(service.getAllVehicleDtos());
    }

    @Override
    public StaticVehicleDataResponse getVehicleInfo(String vin) {
        return vehicleMapper.toResponse(service.getVehicleDto(vin));
    }

    @Override
    public DynamicVehicleDataResponse getSensorDataByVin(String vin) {
        return dynamicVehicleDataMapper.toResponse(service.getLatestDynamicData(vin));
    }
}
