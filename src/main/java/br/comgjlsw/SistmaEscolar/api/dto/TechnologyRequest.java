package br.comgjlsw.SistmaEscolar.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    public TechnologyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}