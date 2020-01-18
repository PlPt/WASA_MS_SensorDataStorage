package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Coordinate")
public class CoordinateResponse {


    @ApiModelProperty(position = 1)
    private double latitude;

    @ApiModelProperty(position = 2)
    private double longitude;


}
