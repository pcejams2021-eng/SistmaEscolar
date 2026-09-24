package br.comgjlsw.SistmaEscolar.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @GetMapping
    public String listarAlunos() {
        return "API de alunos funcionando!";
    }
}