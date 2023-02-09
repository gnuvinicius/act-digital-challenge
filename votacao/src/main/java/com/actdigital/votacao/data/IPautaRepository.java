package com.actdigital.votacao.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.actdigital.votacao.domain.Pauta;

import jakarta.transaction.Transactional;

@Transactional
public interface IPautaRepository extends JpaRepository<Pauta, String> {

	@Query("SELECT p FROM Pauta p WHERE p.id = :id")
	Optional<Pauta> BuscaPautaPorId(UUID id);

	@Query("SELECT a FROM Pauta a")
	Optional<Pauta> carregaResultadoPauta(String pautaId);

}
