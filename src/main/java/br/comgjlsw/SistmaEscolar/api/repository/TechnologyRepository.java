package br.comgjlsw.SistmaEscolar.api.repository;

import br.comgjlsw.SistmaEscolar.api.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}