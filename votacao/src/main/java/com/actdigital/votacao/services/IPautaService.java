package com.actdigital.votacao.services;

import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;

public interface IPautaService {

	void alteraStatusDaSessaoDeVotacao(String pautaId, Integer segundos) throws Exception;

	void recebeVotoParaProcessamento(Voto voto) throws Exception;
	
	//void registraVoto(Voto voto) throws Exception;

	Pauta carregaResultadoVotacao(String pautaId) throws Exception;

}
