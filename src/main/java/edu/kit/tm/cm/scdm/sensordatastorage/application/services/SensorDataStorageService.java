package edu.kit.tm.cm.scdm.sensordatastorage.application.services;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.DynamicVehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.DynamicVehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
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
     * @param model Model of car
     * @param tag Licenseplate of Vehicle
     * @param seats Number of seats
     * @param tankSize Size of tank in liters
     * @param carType Type of Car
     * @param endpointIdentifier Identifier for DiagnosticEndpoint
     * @return Car created and saved
     */
    @Transactional
    public VehicleData createCar(String vin, String model, String tag, int seats, int tankSize, String carType,
                         String endpointIdentifier) {
        final VehicleData car = new VehicleData(vin, model, tag, seats, tankSize, carType, endpointIdentifier);
        return this.vehicleDataRepository.save(car);
    }

    /**
     * Creates an instance of SensorData an saves it to Database
     *
     * @param vin Vehicle Identification Number
     * @param position Position of Car
     * @param enginePressure Engine Pressure of Car
     * @param tirePressure Tire pressure of CAr
     * @param tankLevel Level of tank
     * @param timestamp Time of
     * @return saved SensorData object
     */
    @Transactional
    public DynamicVehicleData pushSensorData(String vin, String position, float enginePressure, float tirePressure,
                                             float tankLevel,
                                             String timestamp) {
        final DynamicVehicleData data = new DynamicVehicleData(position, enginePressure, tirePressure, tankLevel, timestamp);
        VehicleData car = vehicleDataRepository.findById(vin).get();
        data.setCar(car);
        return this.dynamicVehicleDataRepository.save(data);
    }

    /**
     * Returns a list of all known Cars limited by a total count
     * @param count limit to List of cars
     * @return limited List of Cars
     */
    @Transactional(readOnly = true)
    public List<VehicleData> getVehicles(int count) {
        return this.vehicleDataRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    /**
     * Finds a car by it's vin
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return Car instance if exists, else empty Optional object
     */
    @Transactional(readOnly = true)
    public Optional<VehicleData> getVehicle(String vin) {
        return this.vehicleDataRepository.findById(vin);
    }

    /**
     * Returns all Cars in it's DTO
     * @return List of CarDTO
     */
    @Transactional(readOnly = true)
    public List<VehicleDataDto> getAllVehicleDtos() {
        return this.vehicleDataRepository.findAllDtoBy();
    }

    /**
     * Finds a car by it's vin
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return CarDTO instance if exists, else empty Optional object
     */
    @Transactional(readOnly = true)
    public VehicleDataDto getVehicleDto(String vin) {
        return this.vehicleDataRepository.getDtoByVin(vin);
    }

    /**
     * Return the latest SensorData for a given car
     * @param vin Vehicle Identification Number to identify a specific vehicle
     * @return latest SensorData for Car
     */
    @Transactional(readOnly = true)
    public DynamicVehicleDataDto getLatestSensorData(String vin) {
        VehicleData car = vehicleDataRepository.findById(vin).get();
        DynamicVehicleData cur = car.getDynamicVehicleDataList().get(0);
          return   dynamicVehicleDataRepository.getDtoById(cur.getId());
    }
}
