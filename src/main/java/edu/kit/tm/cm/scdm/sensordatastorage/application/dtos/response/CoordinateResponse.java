package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.CoordinateDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Coordinate")
public class CoordinateResponse {


    @ApiModelProperty(position = 1, required = true)
    private double latitude;

    @ApiModelProperty(position = 2, required = true)
    private float longitude;




}
