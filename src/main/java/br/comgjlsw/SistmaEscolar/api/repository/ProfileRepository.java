package br.comgjlsw.SistmaEscolar.api.repository;

import br.comgjlsw.SistmaEscolar.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}