package com.actdigital.votacao.data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.actdigital.votacao.domain.Assembleia;

import jakarta.transaction.Transactional;

@Transactional
public interface IAssembleiaRepository extends JpaRepository<Assembleia, String> {

	@Query("SELECT a FROM Assembleia a")
	List<Assembleia> carregaListAssembleiasAtivas();
	
	@Query("SELECT a FROM Assembleia a")
	Optional<Assembleia> buscaAssembleiaPorId(UUID assembleiaId);
	
	@Query("SELECT a FROM Assembleia a")
	void cadastra(Assembleia assembleia);
	
	@Query("SELECT a FROM Assembleia a")
	void atualiza(Assembleia assembleia);

}
