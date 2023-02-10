package com.actdigital.votacao.services.impl;

import java.util.Timer;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actdigital.votacao.data.IPautaRepository;
import com.actdigital.votacao.domain.Associado;
import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;
import com.actdigital.votacao.services.IPautaService;
import com.actdigital.votacao.utils.AlteraStatusPauta;
import com.actdigital.votacao.utils.RabbitMQProducer;

@Service
public class PautaServiceImpl implements IPautaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PautaServiceImpl.class);

	@Autowired
	private IPautaRepository _repository;

	@Autowired
	private RabbitMQProducer _producer;

	@Override
	public void alteraStatusDaSessaoDeVotacao(String pautaId, Integer segundos) throws Exception {
		Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

		pauta.abreSessao();

		configuraTimeParaSessao(pauta, segundos);
		_repository.save(pauta);
	}

	@Override
	public void recebeVotoParaProcessamento(Voto voto) throws Exception {
		Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(voto.getPautaId()))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

		if (!pauta.sessaoEstaAberta())
			throw new Exception("sessao para esta pauta não está aberta");

		if (!Associado.isCPF(voto.getCpfAssociado()))
			throw new Exception("cpf invalido");

		_producer.sendVotoMessage(voto);
		LOGGER.info(String.format("Voto message sent to RabbitMQ, %s", voto.toString()));
	}

	@Override
	public Pauta carregaResultadoVotacao(String pautaId) throws Exception {
		return _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

	}

	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	private void registraVoto(Voto voto) throws Exception {
		try {
			Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(voto.getPautaId()))
					.orElseThrow(() -> new Exception("pauta não encontrada"));

			pauta.registrarVoto(voto, voto.getCpfAssociado());

			_repository.save(pauta);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	private void configuraTimeParaSessao(Pauta pauta, Integer segundos) {
		if (segundos == null)
			segundos = 60;

		var timer = new Timer();
		timer.schedule(new AlteraStatusPauta(pauta, _repository), segundos * 1000);
	}
}
