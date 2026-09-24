package br.comgjlsw.SistmaEscolar.api;

import br.comgjlsw.SistmaEscolar.api.dto.ProfileRequest;
import br.comgjlsw.SistmaEscolar.api.dto.ProfileResponse;
import br.comgjlsw.SistmaEscolar.api.model.Profile;
import br.comgjlsw.SistmaEscolar.api.repository.ProfileRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<ProfileResponse> listarProfiles() {

        return profileRepository.findAll()
                .stream()
                .map(profile -> new ProfileResponse(
                        profile.getId(),
                        profile.getName(),
                        profile.getEmail(),
                        profile.getBio()
                ))
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> criarProfile(
            @Valid @RequestBody ProfileRequest request) {

        Profile profile = new Profile(
                request.getName(),
                request.getEmail(),
                request.getBio()
        );

        Profile salvo = profileRepository.save(profile);

        ProfileResponse response = new ProfileResponse(
                salvo.getId(),
                salvo.getName(),
                salvo.getEmail(),
                salvo.getBio()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> buscarProfile(
            @PathVariable Long id) {

        return profileRepository.findById(id)
                .map(profile -> new ProfileResponse(
                        profile.getId(),
                        profile.getName(),
                        profile.getEmail(),
                        profile.getBio()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}