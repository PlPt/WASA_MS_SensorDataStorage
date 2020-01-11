package edu.kit.tm.cm.scdm.sensordatastorage.infrastructure.repositories;

import edu.kit.tm.cm.scdm.sensordatastorage.domain.model.DynamicVehicleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicVehicleDataRepository extends JpaRepository<DynamicVehicleData, Integer> {
}
