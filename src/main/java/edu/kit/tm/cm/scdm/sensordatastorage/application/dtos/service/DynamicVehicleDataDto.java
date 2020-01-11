package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service;

public interface DynamicVehicleDataDto {
    String getPosition();

    float getEnginePressure();

    float getTirePressure();

    float getTankLevel();

    String getTimestamp();
}
