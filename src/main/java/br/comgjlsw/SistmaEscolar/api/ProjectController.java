package br.comgjlsw.SistmaEscolar.api;

import br.comgjlsw.SistmaEscolar.api.dto.ProjectRequest;
import br.comgjlsw.SistmaEscolar.api.dto.ProjectResponse;
import br.comgjlsw.SistmaEscolar.api.model.Project;
import br.comgjlsw.SistmaEscolar.api.model.Profile;
import br.comgjlsw.SistmaEscolar.api.model.Technology;
import br.comgjlsw.SistmaEscolar.api.repository.ProjectRepository;
import br.comgjlsw.SistmaEscolar.api.repository.ProfileRepository;
import br.comgjlsw.SistmaEscolar.api.repository.TechnologyRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository repository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectController(
            ProjectRepository repository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.repository = repository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    @GetMapping
    public List<ProjectResponse> listar() {

        return repository.findAll()
                .stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getDescription()
                ))
                .toList();
    }

    @PostMapping
    public ProjectResponse criar(@RequestBody ProjectRequest request) {

        Project project = new Project(
                request.getName(),
                request.getDescription()
        );

        if (request.getProfileId() != null) {

            Profile profile = profileRepository.findById(request.getProfileId())
                    .orElseThrow();

            project.setProfile(profile);
        }

        if (request.getTechnologyIds() != null) {

            List<Technology> technologies =
                    technologyRepository.findAllById(request.getTechnologyIds());

            project.setTechnologies(technologies);
        }

        Project salvo = repository.save(project);

        return new ProjectResponse(
                salvo.getId(),
                salvo.getName(),
                salvo.getDescription()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> buscarPorId(
            @PathVariable Long id) {

        return repository.findById(id)
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getDescription()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}