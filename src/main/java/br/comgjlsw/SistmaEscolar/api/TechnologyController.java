package br.comgjlsw.SistmaEscolar.api;

import br.comgjlsw.SistmaEscolar.api.dto.TechnologyRequest;
import br.comgjlsw.SistmaEscolar.api.dto.TechnologyResponse;
import br.comgjlsw.SistmaEscolar.api.model.Technology;
import br.comgjlsw.SistmaEscolar.api.repository.TechnologyRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyRepository technologyRepository;

    public TechnologyController(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @PostMapping
    public ResponseEntity<TechnologyResponse> criarTechnology(
            @Valid @RequestBody TechnologyRequest request) {

        Technology technology = new Technology(request.getName());

        Technology salva = technologyRepository.save(technology);

        TechnologyResponse response = new TechnologyResponse(
                salva.getId(),
                salva.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> listarTechnologies() {

        return ResponseEntity.ok(
                technologyRepository.findAll()
                        .stream()
                        .map(technology -> new TechnologyResponse(
                                technology.getId(),
                                technology.getName()
                        ))
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyResponse> buscarTechnology(
            @PathVariable Long id) {

        return technologyRepository.findById(id)
                .map(technology -> new TechnologyResponse(
                        technology.getId(),
                        technology.getName()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}