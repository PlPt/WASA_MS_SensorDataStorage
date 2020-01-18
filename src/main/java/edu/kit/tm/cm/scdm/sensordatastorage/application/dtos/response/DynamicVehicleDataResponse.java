package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Optional;

@Data
@ApiModel("DynamicVehicleData")
public class DynamicVehicleDataResponse {


    @ApiModelProperty(position = 1)
    private CoordinateResponse position;

    @ApiModelProperty(position = 2)
    private Double oilPressure;

    @ApiModelProperty(position = 3)
    private Double tirePressure;

    @ApiModelProperty(position = 4)
    private Double tankLevel;

    @ApiModelProperty(position = 5, required = true)
    private String timestamp;
}

