package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service;

import java.util.Optional;

public interface DynamicVehicleDataDto {
    CoordinateDto getPosition();

    Double getOilPressure();

    Double getTirePressure();

    Double getTankLevel();

    String getTimestamp();
}
