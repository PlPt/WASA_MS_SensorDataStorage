package edu.kit.tm.cm.scdm.sensordatastorage.application.controllers.api;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/vehicles")
@Api(tags = "vehicles", description = "Retrieve data about vehicles")
public interface SensorDataStorageApi {

    /**
     *  Returns all known Vehicles
     * @return List of Vehicles as StaticVehicleDataResponse
     */
    @GetMapping("/")
    @ApiOperation(value = "Gets all vehicles")
    List<StaticVehicleDataResponse> getVehicles();


    /**
     * Returns static Car Information for a Car identified by it's vin
     * @param vin Vehicle Identification Number
     * @return CarResponse with static data
     */
    @GetMapping("{vin}/info")
    @ApiOperation(value = "Gets info for a given vehicle")
    StaticVehicleDataResponse getVehicleInfo(@PathVariable String vin);

    /**
     * Returns  current SensorData for a Car identified by it's vin
     * @param vin Vehicle Identification Number
     * @return SensorDataResponse with latest SensorData
     */
    @GetMapping("{vin}/sensorData")
    @ApiOperation(value = "Gets SensorData for a given vehicle")
    DynamicVehicleDataResponse getSensorDataByVin(@PathVariable String vin);
}