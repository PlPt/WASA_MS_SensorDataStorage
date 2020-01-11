package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.DynamicVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.DynamicVehicleDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DynamicVehicleDataMapper {
    DynamicVehicleDataResponse toRespone(DynamicVehicleDataResponse dto);
}
