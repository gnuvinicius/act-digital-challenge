package com.actdigital.votacao.data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.actdigital.votacao.domain.Assembleia;

public class AssembleiaRepository implements IAssembleiaRepository {

	@Override
	public List<Assembleia> carregaListAssembleiasAtivas() {
		return null;
	}

	@Override
	public Optional<Assembleia> buscaAssembleiaPorId(UUID assembleiaId) {
		return Optional.empty();
	}

	@Override
	public void cadastra(Assembleia assembleia) {

	}

	@Override
	public void atualiza(Assembleia assembleia) {

	}
}
