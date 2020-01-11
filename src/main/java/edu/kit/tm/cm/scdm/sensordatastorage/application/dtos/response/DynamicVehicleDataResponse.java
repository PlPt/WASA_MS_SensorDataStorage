package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("DynamicVehicleData")
public class DynamicVehicleDataResponse {

    @ApiModelProperty(position = 1, required = true)
    private String vin;

    @ApiModelProperty(position = 2, required = true)
    private String position;

    @ApiModelProperty(position = 3, required = true)
    private float enginePressure;

    @ApiModelProperty(position = 4, required = true)
    private float tirePressure;

    @ApiModelProperty(position = 5, required = true)
    private float tankLevel;

    @ApiModelProperty(position = 6, required = true)
    private String timestamp;

    public DynamicVehicleDataResponse() {

    }

    public DynamicVehicleDataResponse(String vin, String position, float enginePressure, float tirePressure, float tankLevel,
                                      String timestamp) {
        this.vin = vin;
        this.position = position;
        this.enginePressure = enginePressure;
        this.tirePressure = tirePressure;
        this.tankLevel = tankLevel;
        this.timestamp = timestamp;
    }
}

