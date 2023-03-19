package tda.darkarmy.parkme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.parkme.model.BookParkingSlot;
import tda.darkarmy.parkme.model.User;

import java.util.List;

public interface BookParkingSlotRepository extends JpaRepository<BookParkingSlot, Long> {
    List<BookParkingSlot> findByUser(User user);
}
