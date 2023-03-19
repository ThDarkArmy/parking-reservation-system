package tda.darkarmy.parkme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.parkme.model.ParkingArea;
import tda.darkarmy.parkme.model.ParkingSlot;
import tda.darkarmy.parkme.model.User;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByParkingArea(ParkingArea parkingArea);
}
