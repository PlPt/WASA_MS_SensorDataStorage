package edu.kit.tm.cm.scdm.sensordatastorage.application.services;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.DynamicVehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.DynamicVehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleType;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.DynamicVehicleDataRepository;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.VehicleDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SensorDataStorageService {
    private final VehicleDataRepository vehicleDataRepository;
    private final DynamicVehicleDataRepository dynamicVehicleDataRepository;

    /**
     * Initializes the DataStorageService via DependencyInjection
     *
     * @param vehicleDataRepository        VehicleDataRepository for DataAccess
     * @param dynamicVehicleDataRepository DynamicVehicleDataRepository for DataAccess
     */
    public SensorDataStorageService(VehicleDataRepository vehicleDataRepository,
                                    DynamicVehicleDataRepository dynamicVehicleDataRepository) {
        this.vehicleDataRepository = vehicleDataRepository;
        this.dynamicVehicleDataRepository = dynamicVehicleDataRepository;
    }

    /**
     * Creates and saves a new Vehicle in the database
     *
     * @param vin                Vehicle Identification Number
     * @param model              Model of Vehicle
     * @param tag                Licence plate of Vehicle
     * @param seats              Number of seats
     * @param tankSize           Size of tank in liters
     * @param vehicleType        Type of Vehicle
     * @param endpointIdentifier Identifier for SSG
     * @return VehicleData created and saved
     */
    @Transactional
    public VehicleData createVehicle(String vin, String model, String tag, int seats, int tankSize,
                                     VehicleType vehicleType, String endpointIdentifier) {
        final VehicleData vehicle = new VehicleData(vin, model, tag, seats, tankSize, vehicleType, endpointIdentifier);
        return this.vehicleDataRepository.save(vehicle);
    }

    /**
     * Creates an instance of DynamicVehicleData an saves it to Database
     *
     * @param vin            Vehicle Identification Number
     * @param position       Position of Vehicle
     * @param enginePressure Engine Pressure of Vehicle
     * @param tirePressure   Tire pressure of Vehicle
     * @param tankLevel      Level of tank
     * @param timestamp      Time of measurement
     * @return Saved DynamicVehicleData object
     */
    @Transactional
    public DynamicVehicleData addDynamicData(String vin, Coordinate position, float enginePressure, float tirePressure,
                                             double tankLevel, String timestamp) {

        VehicleData vehicle = vehicleDataRepository.findById(vin).get();
        final DynamicVehicleData data = new DynamicVehicleData(position, enginePressure, tirePressure, tankLevel,
                timestamp, vehicle);
        return this.dynamicVehicleDataRepository.save(data);
    }

    /**
     * Returns all Vehicles
     *
     * @return List of VehicleDataDto
     */
    @Transactional(readOnly = true)
    public List<VehicleDataDto> getAllVehicleDtos() {
        return this.vehicleDataRepository.findAllDtoBy();
    }

    /**
     * Finds a Vehicle by its vin
     *
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return VehicleDataDTO instance if exists, else empty Optional object
     */
    @Transactional(readOnly = true)
    public VehicleDataDto getVehicleDto(String vin) {
        return this.vehicleDataRepository.getDtoByVin(vin);
    }

    /**
     * Return the latest DynamicVehicleData for a given Vehicle
     *
     * @param vin Vehicle Identification Number to identify a specific Vehicle
     * @return Latest DynamicVehicleData for a Vehicle
     */
    @Transactional(readOnly = true)
    public DynamicVehicleDataDto getLatestDynamicData(String vin) {
        VehicleData car = vehicleDataRepository.findById(vin).get();
        DynamicVehicleData cur = car.getDynamicVehicleDataList().get(0);
        return dynamicVehicleDataRepository.getDtoById(cur.getId());
    }
}
