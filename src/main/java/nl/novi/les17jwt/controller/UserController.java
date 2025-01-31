package nl.novi.les17jwt.controller;

import nl.novi.les17jwt.dto.UserDto;
import nl.novi.les17jwt.model.Role;
import nl.novi.les17jwt.model.User;
import nl.novi.les17jwt.repository.RoleRepository;
import nl.novi.les17jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    // No UserService used in demo code!

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }
    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        Set<Role> userRoles = newUser.getRoles();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }

        userRepos.save(newUser);

        return "Done";
    }
}
