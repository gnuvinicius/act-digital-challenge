package com.actdigital.votacao.services;

import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;

public interface IPautaService {

	void abreSessaoVotacao(String pautaId) throws Exception;
	void registraVoto(Voto voto, String pautaId, String cpfAssociado) throws Exception;
	Pauta carregaResultadoVotacao(String pautaId) throws Exception;
	
	
}
