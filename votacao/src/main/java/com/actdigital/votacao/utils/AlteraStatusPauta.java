package com.actdigital.votacao.utils;

import java.util.TimerTask;

import com.actdigital.votacao.data.IPautaRepository;
import com.actdigital.votacao.domain.Pauta;

public class AlteraStatusPauta extends TimerTask {

	private IPautaRepository _repository;
	
	private final Pauta pauta;
	
	public AlteraStatusPauta(Pauta pauta, IPautaRepository repository) {
		this.pauta = pauta;
		this._repository = repository;
	}
	
	@Override
	public void run() {
		pauta.fechaSessao();
		_repository.save(pauta);
	}

}
