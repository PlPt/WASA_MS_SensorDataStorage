package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers.api;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/vehicles")
@Api(tags = "vehicles", description = "Retrieve data about vehicles")
public interface SensorDataStorageApi {

    /**
     * Returns all known Vehicles
     *
     * @return List of Vehicles as StaticVehicleDataResponse
     */
    @GetMapping("/")
    @ApiOperation(value = "Gets all vehicles")
    List<StaticVehicleDataResponse> getVehicles();


    /**
     * Returns StaticVehicleData for a Vehicle identified by its vin
     *
     * @param vin Vehicle Identification Number
     * @return StaticVehicleDataResponse
     */
    @GetMapping("{vin}/static")
    @ApiOperation(value = "Gets StaticVehicleData for a given vehicle")
    StaticVehicleDataResponse getVehicleInfo(@PathVariable String vin);

    /**
     * Returns latest DynamicVehicleData for a Vehicle identified by its vin
     *
     * @param vin Vehicle Identification Number
     * @return Latest DynamicVehicleData as DynamicVehicleDataResponse
     */
    @GetMapping("{vin}/dynamic")
    @ApiOperation(value = "Gets latest DynamicVehicleData for a given vehicle")
    DynamicVehicleDataResponse getSensorDataByVin(@PathVariable String vin);
}