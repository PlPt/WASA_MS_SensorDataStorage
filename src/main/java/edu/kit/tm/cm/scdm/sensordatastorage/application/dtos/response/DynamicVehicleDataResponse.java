package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("DynamicVehicleData")
public class DynamicVehicleDataResponse {


    @ApiModelProperty(position = 1, required = true)
    private CoordinateResponse position;

    @ApiModelProperty(position = 2, required = true)
    private float oilPressure;

    @ApiModelProperty(position = 3, required = true)
    private float tirePressure;

    @ApiModelProperty(position = 4, required = true)
    private float tankLevel;

    @ApiModelProperty(position = 5, required = true)
    private String timestamp;
}

