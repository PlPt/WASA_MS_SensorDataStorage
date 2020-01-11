package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers.vehicletest;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Api(tags = "test-vehicle", description = "Test API for creating Vehicle Test data")
public interface VehicleTestApi {


    @GetMapping("addVehicle")
    @ApiOperation(value = "Adds a new Vehicle to DataStorage")
    void createVehicle(StaticVehicleDataResponse vehicle);

    @GetMapping("addDynamicData/{vin}")
    @ApiOperation(value = "Adds a new Vehicle to DataStorage")
    void addDynamicVehicleData(@PathVariable String vin,DynamicVehicleDataResponse vehicle);


}
