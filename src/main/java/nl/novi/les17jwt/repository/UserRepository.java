package nl.novi.les17jwt.repository;

import nl.novi.les17jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
