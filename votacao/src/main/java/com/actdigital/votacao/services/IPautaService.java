package com.actdigital.votacao.services;

import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;

public interface IPautaService {

	void abreSessaoVotacao(String pautaId) throws Exception;

	void recebeVotoParaProcessamento(Voto voto);
	
	void registraVoto(Voto voto) throws Exception;

	Pauta carregaResultadoVotacao(String pautaId) throws Exception;

}
