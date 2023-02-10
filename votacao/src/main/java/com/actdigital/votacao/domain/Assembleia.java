package com.actdigital.votacao.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.actdigital.votacao.utils.Validacoes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Assembleia {

	@Id
	private UUID id;
	private String titulo;
	private String descricao;
	private Status status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "assembleia_id")
	private List<Pauta> pautas;

	public Assembleia() {
	}

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
