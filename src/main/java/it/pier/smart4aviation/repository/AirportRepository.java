package it.pier.smart4aviation.repository;

import it.pier.smart4aviation.model.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    Optional<AirportEntity> findByIATACodeIgnoreCase(String IATACode);

    boolean existsByIATACodeIgnoreCase(String IATACode);


}
