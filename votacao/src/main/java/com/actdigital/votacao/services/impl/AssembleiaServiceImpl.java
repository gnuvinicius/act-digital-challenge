package com.actdigital.votacao.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actdigital.votacao.data.IAssembleiaRepository;
import com.actdigital.votacao.domain.Assembleia;
import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.services.IAssembleiaService;

@Service
public class AssembleiaServiceImpl implements IAssembleiaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssembleiaServiceImpl.class);

	private static String ASSEMBLEIA_NOT_FOUND = "assembleia não encontrada pelo Id %s";

	@Autowired
	private IAssembleiaRepository _repository;

	@Override
	public List<Assembleia> carregaListaAssembleias() {
		return _repository.carregaListAssembleiasAtivas();
	}

	@Override
	public void cadastraNovaAssembleia(String titulo, String descricao) throws Exception {
		try {
			var entity = new Assembleia(titulo, descricao);
			_repository.save(entity);
		} catch (Exception e) {

		}
	}

	@Override
	public void cadastraNovaPauta(String assembleiaId, String titulo, String descricao) throws Exception {

		Assembleia assembleia = _repository.buscaAssembleiaPorId(UUID.fromString(assembleiaId))
				.orElseThrow(() -> {
					LOGGER.error(String.format(ASSEMBLEIA_NOT_FOUND, assembleiaId));
					return new Exception(String.format(ASSEMBLEIA_NOT_FOUND, assembleiaId));
				});

		assembleia.adicionarPauta(new Pauta(titulo, descricao));

		_repository.save(assembleia);
	}

	@Override
	public void encerraAssembleia(String assembleiaId) throws Exception {
		Assembleia assembleia = _repository.buscaAssembleiaPorId(UUID.fromString(assembleiaId))
				.orElseThrow(() -> new Exception());

		assembleia.encerra();
		_repository.save(assembleia);
	}
}
