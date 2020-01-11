package edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service;

public interface VehicleDataDto {
    String getVin();

    String getModel();

    String getTag();

    int getSeats();

    int getTankSize();

    String getCarType();

    String getEndpointIdentifier();
}
