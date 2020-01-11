package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.response.StaticVehicleDataResponse;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleDataMapper {
    StaticVehicleDataResponse toResponse(VehicleDataDto dto);
    List<StaticVehicleDataResponse> toResponseList(List<VehicleDataDto> dtos);
}
