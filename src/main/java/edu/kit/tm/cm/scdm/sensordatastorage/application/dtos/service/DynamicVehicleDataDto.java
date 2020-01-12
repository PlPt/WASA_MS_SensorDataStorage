package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service;

public interface DynamicVehicleDataDto {
    CoordinateDto getPosition();

    float getOilPressure();

    float getTirePressure();

    float getTankLevel();

    String getTimestamp();
}
