package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service;

public interface DynamicVehicleDataDto {
    CoordinateDto getPosition();

    double getOilPressure();

    double getTirePressure();

    double getTankLevel();

    String getTimestamp();
}
