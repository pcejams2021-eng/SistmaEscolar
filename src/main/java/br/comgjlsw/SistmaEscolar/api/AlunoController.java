package br.comgjlsw.SistmaEscolar.api.controller;

import br.comgjlsw.SistmaEscolar.api.dto.AlunoRequest;
import br.comgjlsw.SistmaEscolar.api.dto.AlunoResponse;
import br.comgjlsw.SistmaEscolar.api.model.Aluno;
import br.comgjlsw.SistmaEscolar.api.repository.AlunoRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public List<AlunoResponse> listarAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> new AlunoResponse(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        aluno.getMatricula()
                ))
                .toList();
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criarAluno(
            @Valid @RequestBody AlunoRequest request) {

        Aluno aluno = new Aluno(
                request.getNome(),
                request.getEmail(),
                request.getMatricula()
        );

        Aluno salvo = alunoRepository.save(aluno);

        AlunoResponse response = new AlunoResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getMatricula()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarAluno(
            @PathVariable Long id) {

        return alunoRepository.findById(id)
                .map(aluno -> new AlunoResponse(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        aluno.getMatricula()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}