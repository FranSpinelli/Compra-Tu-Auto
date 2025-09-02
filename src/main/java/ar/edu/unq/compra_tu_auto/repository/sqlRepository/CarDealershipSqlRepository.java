package ar.edu.unq.compra_tu_auto.repository.sqlRepository;

import ar.edu.unq.compra_tu_auto.repository.sqlRepository.entities.CarDealershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealershipSqlRepository extends JpaRepository<CarDealershipEntity, Integer> {
}
