package com.actdigital.votacao.services.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actdigital.votacao.data.IPautaRepository;
import com.actdigital.votacao.domain.Pauta;
import com.actdigital.votacao.domain.Voto;
import com.actdigital.votacao.services.IPautaService;
import com.actdigital.votacao.utils.RabbitMQProducer;

@Service
public class PautaServiceImpl implements IPautaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PautaServiceImpl.class);

	@Autowired
	private IPautaRepository _repository;

	@Autowired
	private RabbitMQProducer _producer;

	@Override
	public void abreSessaoVotacao(String pautaId) throws Exception {
		Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

		pauta.abreSessao();
		_repository.save(pauta);
	}

	@Override
	public void recebeVotoParaProcessamento(Voto voto) {
		_producer.sendVotoMessage(voto);
		LOGGER.info(String.format("Voto message sent to RabbitMQ, %s", voto.toString()));
	}

	@Override
	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	public void registraVoto(Voto voto) throws Exception {
		try {
			Pauta pauta = _repository.BuscaPautaPorId(UUID.fromString(voto.getPautaId()))
					.orElseThrow(() -> new Exception("pauta não encontrada"));

			if (!pauta.sessaoEstaAberta())
				throw new Exception("sessao para esta pauta não está aberta");

			pauta.registrarVoto(voto, null);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public Pauta carregaResultadoVotacao(String pautaId) throws Exception {
		return _repository.BuscaPautaPorId(UUID.fromString(pautaId))
				.orElseThrow(() -> new Exception("pauta não encontrada"));

	}

}
