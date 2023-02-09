package com.actdigital.votacao.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actdigital.votacao.data.IAssembleiaRepository;
import com.actdigital.votacao.domain.Assembleia;
import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.services.IAssembleiaService;

@Service
public class AssembleiaServiceImpl implements IAssembleiaService {

	@Autowired
	private IAssembleiaRepository _repository;

	@Override
	public List<Assembleia> carregaListaAssembleias() {
		return _repository.carregaListAssembleiasAtivas();
	}

	@Override
	public void cadastraNovaAssembleia(String titulo, String descricao) throws Exception {
		_repository.cadastra(new Assembleia(titulo, descricao));
	}

	@Override
	public void cadastraNovaPauta(String assembleiaId, String titulo, String descricao) throws Exception {

		Assembleia assembleia = _repository.buscaAssembleiaPorId(UUID.fromString(assembleiaId))
				.orElseThrow(() -> new Exception());

		var novaPauta = new Pauta(titulo, descricao);

		assembleia.adicionarPauta(novaPauta);

		_repository.atualiza(assembleia);
	}

	@Override
	public void encerraAssembleia(String assembleiaId) throws Exception {
		Assembleia assembleia = _repository.buscaAssembleiaPorId(UUID.fromString(assembleiaId))
				.orElseThrow(() -> new Exception());

		assembleia.encerra();
		_repository.atualiza(assembleia);
	}
}
