package com.actdigital.votacao.application;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actdigital.votacao.domain.Voto;
import com.actdigital.votacao.services.IAssembleiaService;
import com.actdigital.votacao.services.IPautaService;

@RestController
@RequestMapping(value = "/api/votacao")
public class VotacaoController {

	@Autowired
	private IAssembleiaService _assembleiaService;

	@Autowired
	private IPautaService _pautaService;

	@GetMapping
	public ResponseEntity<?> carregaListaAssembleias() {
		var result = _assembleiaService.carregaListaAssembleias();
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/nova-assembleia")
	public ResponseEntity<?> cadastraNovaAssembleia(@RequestBody AssembleiaRequestDto request) throws Exception {

		_assembleiaService.cadastraNovaAssembleia(request.titulo, request.descricao);
		URI uri = URI.create("");

		return ResponseEntity.created(uri).build();
	}

	@PostMapping(value = "/nova-pauta")
	public ResponseEntity<?> cadastraNovaPauta() throws Exception {
		_assembleiaService.cadastraNovaPauta(null, null, null);
		return ResponseEntity.ok(null);
	}

	@GetMapping(value = "/abre-sessao")
	public ResponseEntity<?> abreSessaoVotacao() throws Exception {
		_pautaService.abreSessaoVotacao(null);
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/registra-voto")
	public ResponseEntity<?> registraVoto(@RequestBody Voto voto) throws Exception {
		_pautaService.recebeVotoParaProcessamento(voto);
		return ResponseEntity.ok(null);
	}

}
