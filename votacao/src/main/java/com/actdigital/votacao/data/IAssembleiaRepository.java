package com.actdigital.votacao.data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.actdigital.votacao.domain.Assembleia;

@Repository
public interface IAssembleiaRepository {

	List<Assembleia> carregaListAssembleiasAtivas();
	Optional<Assembleia> buscaAssembleiaPorId(UUID assembleiaId);
	void cadastra(Assembleia assembleia);
	void atualiza(Assembleia assembleia);

}
