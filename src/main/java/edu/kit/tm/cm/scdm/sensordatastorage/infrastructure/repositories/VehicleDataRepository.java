package edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories;


import edu.kit.tm.cm.scdm.sensordatastorage.application.dtos.service.VehicleDataDto;
import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.VehicleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleData, String> {
    List<VehicleDataDto> findAllDtoBy();

    VehicleDataDto getDtoByVin(String vin);
}
