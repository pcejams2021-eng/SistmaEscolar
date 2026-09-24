package br.comgjlsw.SistmaEscolar.api.repository;

import br.comgjlsw.SistmaEscolar.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}