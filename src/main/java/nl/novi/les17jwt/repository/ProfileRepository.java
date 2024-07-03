package nl.novi.les17jwt.repository;

import nl.novi.les17jwt.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
