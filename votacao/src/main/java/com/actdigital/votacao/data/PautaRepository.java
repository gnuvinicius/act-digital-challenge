package com.actdigital.votacao.data;

import java.util.Optional;
import java.util.UUID;

import com.actdigital.votacao.domain.Pauta;

public class PautaRepository implements IPautaRepository {

	@Override
	public Optional<Pauta> BuscaPautaPorId(UUID fromString) {
		return Optional.empty();
	}

	@Override
	public void atualiza(Pauta pauta) {
		
	}

	@Override
	public Optional<Pauta> carregaResultadoPauta(String pautaId) {
		return Optional.empty();
	}

}
