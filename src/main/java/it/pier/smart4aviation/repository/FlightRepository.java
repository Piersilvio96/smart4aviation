package it.pier.smart4aviation.repository;

import it.pier.smart4aviation.model.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long>, JpaSpecificationExecutor<FlightEntity> {
}
