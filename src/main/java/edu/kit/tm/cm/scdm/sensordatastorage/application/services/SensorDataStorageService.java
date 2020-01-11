package edu.kit.tm.cm.scdm.sensordatastorage.application.services;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.DynamicVehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.DynamicVehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleType;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.VehicleDataRepository;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.DynamicVehicleDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorDataStorageService {
    private final VehicleDataRepository vehicleDataRepository;
    private final DynamicVehicleDataRepository dynamicVehicleDataRepository;

    /**
     * Initializes the DataStorace Service via DependencyInjection
     * @param vehicleDataRepository VehicleDataRepository for DataAccess
     * @param dynamicVehicleDataRepository DynamicVehicleDataRepository for DataAccess
     */
    public SensorDataStorageService(VehicleDataRepository vehicleDataRepository,
                                    DynamicVehicleDataRepository dynamicVehicleDataRepository) {
        this.vehicleDataRepository = vehicleDataRepository;
        this.dynamicVehicleDataRepository = dynamicVehicleDataRepository;
    }

    /**
     * Creates and saves a new Car in the database
     * @param vin Vehicle Identification Number
     * @param model Model of vehicle
     * @param tag Licenceplate of Vehicle
     * @param seats Number of seats
     * @param tankSize Size of tank in liters
     * @param carType Type of Vehicle
     * @param endpointIdentifier Identifier for DiagnosticEndpoint
     * @return VehicleData created and saved
     */
    @Transactional
    public VehicleData createVehicle(String vin, String model, String tag, int seats, int tankSize,
                                     VehicleType vehicleType, String endpointIdentifier) {
        final VehicleData car = new VehicleData(vin, model, tag, seats, tankSize, vehicleType, endpointIdentifier);
        return this.vehicleDataRepository.save(car);
    }

    /**
     * Creates an instance of DynamicVehicleData an saves it to Database
     *
     * @param vin Vehicle Identification Number
     * @param position Position of Vehicle
     * @param enginePressure Engine Pressure of Vehicle
     * @param tirePressure Tire pressure of Vehicle
     * @param tankLevel Level of tank
     * @param timestamp Time of measurement
     * @return saved DynamicVehicleData object
     */
    @Transactional
    public DynamicVehicleData pushSensorData(String vin, Coordinate position, float enginePressure, float tirePressure,
                                             float tankLevel, String timestamp) {

        VehicleData vehicle = vehicleDataRepository.findById(vin).get();
        final DynamicVehicleData data = new DynamicVehicleData(position, enginePressure, tirePressure, tankLevel,
                                                                timestamp,vehicle);

        return this.dynamicVehicleDataRepository.save(data);
    }

    /**
     * Returns a list of all known Vehicles limited by a total count
     * @param count limit to List of vehicles
     * @return limited List of Vehicles
     */
    @Transactional(readOnly = true)
    public List<VehicleData> getVehicles(int count) {
        return this.vehicleDataRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    /**
     * Finds a vehicle by it's vin
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return Vehicle instance if exists, else empty Optional object
     */
    @Transactional(readOnly = true)
    public Optional<VehicleData> getVehicle(String vin) {
        return this.vehicleDataRepository.findById(vin);
    }

    /**
     * Returns all Vehicles in it's DTO
     * @return List of VehicleDataDTO
     */
    @Transactional(readOnly = true)
    public List<VehicleDataDto> getAllVehicleDtos() {
        return this.vehicleDataRepository.findAllDtoBy();
    }

    /**
     * Finds a vehicle by it's vin
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return VehicleDataDTO instance if exists, else empty Optional object
     */
    @Transactional(readOnly = true)
    public VehicleDataDto getVehicleDto(String vin) {
        return this.vehicleDataRepository.getDtoByVin(vin);
    }

    /**
     * Return the latest SensorData for a given vehicle
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return latest SensorData for a Vehicle
     */
    @Transactional(readOnly = true)
    public DynamicVehicleDataDto getLatestSensorData(String vin) {
        VehicleData car = vehicleDataRepository.findById(vin).get();
        DynamicVehicleData cur = car.getDynamicVehicleDataList().get(0);
          return   dynamicVehicleDataRepository.getDtoById(cur.getId());
    }
}
