package nl.novi.les17jwt.repository;

import nl.novi.les17jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
