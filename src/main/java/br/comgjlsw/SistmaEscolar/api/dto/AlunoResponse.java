package br.comgjlsw.SistmaEscolar.api.dto;

public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private String matricula;

    public AlunoResponse() {
    }

    public AlunoResponse(Long id, String nome, String email, String matricula) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }
}