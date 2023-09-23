package it.pier.smart4aviation.repository;

import it.pier.smart4aviation.model.entity.BaggageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageRepository extends JpaRepository<BaggageEntity, Long> {
}
