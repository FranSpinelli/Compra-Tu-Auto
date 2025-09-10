package ar.edu.unq.compra_tu_auto.repository.sqlRepository;

import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarSqlRepository extends JpaRepository<CarEntity, Integer> {

    Optional<CarEntity> findByIdAndCarDealershipId(Integer carId, Integer dealershipId);
}
