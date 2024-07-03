package nl.novi.les17jwt.controller;

import nl.novi.les17jwt.dto.ProfileDto;
import nl.novi.les17jwt.model.Profile;
import nl.novi.les17jwt.repository.ProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    // No ProfileService used in demo code!

    private final ProfileRepository repos;

    public ProfileController(ProfileRepository repos) {
        this.repos = repos;
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setUsername(profileDto.username);
        profile.setFirstname(profileDto.firstname);
        profile.setLastname(profileDto.lastname);
        profile.setAddress(profileDto.address);
        profile.setBankAccount(profileDto.bankaccount);

        this.repos.save(profile);

        return ResponseEntity.created(null).body(profile);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        Profile profile = this.repos.findById(username).get();  // happy flow
        ProfileDto profileDto = new ProfileDto();
        profileDto.username = profile.getUsername();
        profileDto.firstname = profile.getFirstname();
        profileDto.lastname  = profile.getLastname();
        profileDto.address = profile.getAddress();
        profileDto.bankaccount = profile.getBankAccount();

        return ResponseEntity.ok(profileDto);
    }
}
