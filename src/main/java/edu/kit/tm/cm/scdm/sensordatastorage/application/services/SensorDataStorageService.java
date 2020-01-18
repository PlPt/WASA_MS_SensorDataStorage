package edu.kit.tm.cm.scdm.sensordatastorage.application.services;

import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.DynamicVehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.Coordinate;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.DynamicVehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleType;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.DynamicVehicleDataRepository;
import edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories.VehicleDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataStorageService {
    private final VehicleDataRepository vehicleDataRepository;
    private final DynamicVehicleDataRepository dynamicVehicleDataRepository;
    Logger logger = LoggerFactory.getLogger(SensorDataStorageService.class);

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
     * @param oilPressure Oil Pressure of Vehicle
     * @param tirePressure   Tire pressure of Vehicle
     * @param tankLevel      Level of tank
     * @param timestamp      Time of measurement
     * @return Saved DynamicVehicleData object
     */
    @Transactional
    public DynamicVehicleData addDynamicData(String vin, Coordinate position, Double oilPressure, Double tirePressure,
                                             Double tankLevel, String timestamp) {

        Optional<VehicleData> vehicle = vehicleDataRepository.findById(vin);
        if(vehicle.isPresent()) {
            final DynamicVehicleData data = new DynamicVehicleData(position, oilPressure, tirePressure, tankLevel,
                    timestamp, vehicle.get());
            return this.dynamicVehicleDataRepository.save(data);
        }
        else{
            logger.error(String.format("Vehicle with vin '%s' was not registered as vehicle before. " +
                    "Received data could not be stored",vin));
            return  null;
        }
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
