package com.actdigital.votacao.data;

import java.util.Optional;
import java.util.UUID;

import com.actdigital.votacao.domain.Pauta;

public interface IPautaRepository {

	Optional<Pauta> BuscaPautaPorId(UUID fromString);

	void atualiza(Pauta pauta);

	Optional<Pauta> carregaResultadoPauta(String pautaId);

}
