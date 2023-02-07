package com.actdigital.votacao.services;

import java.util.UUID;

import com.actdigital.votacao.data.IPautaRepository;
import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;

public class PautaServiceImpl implements IPautaService {

	private IPautaRepository _repository;

	@Override
	public void abreSessaoVotacao(String pautaId) throws Exception {
		Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

		pauta.abreSessao();
		_repository.atualiza(pauta);
	}

	@Override
	public void registraVoto(Voto voto, String pautaId, String cpfAssociado) throws Exception {
		Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

		if (!pauta.sessaoEstaAberta())
			throw new Exception("sessao para esta pauta não está aberta");

		pauta.registrarVoto(voto, null);

	}

	@Override
	public Pauta carregaResultadoVotacao(String pautaId) throws Exception {
		return _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

	}

}
