package com.actdigital.votacao.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.actdigital.votacao.utils.Validacoes;

public class Assembleia {

	private UUID id;
	private String titulo;
	private String descricao;
	private Status status;
	private List<Pauta> pautas;

	public Assembleia(String titulo, String descricao) throws Exception {
		this.id = UUID.randomUUID();
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = Status.ATIVO;
		this.pautas = new ArrayList<Pauta>();

		validar();
	}

	public UUID getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Status getStatus() {
		return status;
	}

	public List<Pauta> getPautas() {
		return pautas;
	}

	public void adicionarPauta(Pauta novaPauta) {
		this.pautas.add(novaPauta);
	}

	private void validar() throws Exception {
		Validacoes.ValidateIfNotEmptyOrNull(this.titulo, "Titulo não pode ser nulo ou vazio");
	}

	public void encerra() {
		this.status = Status.INATIVO;

	}

}
