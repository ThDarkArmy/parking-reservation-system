package tda.darkarmy.parkme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.parkme.model.ParkingArea;
import tda.darkarmy.parkme.model.User;

import java.util.List;

public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Long> {
    List<ParkingArea> findByUser(User user);
}
