package tda.darkarmy.parkme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.parkme.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
