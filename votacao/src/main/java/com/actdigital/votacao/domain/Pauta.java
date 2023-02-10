package com.actdigital.votacao.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Pauta {

	@Id
	private UUID id;
	private String titulo;
	private String descricao;
	private Status status;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Associado> associadosParticipante;
	private long totalVotoSim;
	private long totalVotoNao;
	private LocalDateTime criadaEm;
	
	public Pauta() {
	}

	public Pauta(String titulo, String descricao) {
		this.id = UUID.randomUUID();
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = Status.INATIVO;
		this.totalVotoSim = 0;
		this.totalVotoNao = 0;
		this.criadaEm = LocalDateTime.now();
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

	public List<Associado> getAssociadosParticipante() {
		return associadosParticipante;
	}

	public long getTotalVotoSim() {
		return totalVotoSim;
	}

	public long getTotalVotoNao() {
		return totalVotoNao;
	}

	public LocalDateTime getCriadaEm() {
		return criadaEm;
	}

	public void registrarVoto(Voto voto, String cpfAssociado) throws Exception {
		if (associadosParticipante.stream().anyMatch(a -> a.getCpf().equals(cpfAssociado)))
			throw new Exception("associado já votou nesta pauta");

		if (voto.getVoto().equals("SIM")) {
			totalVotoSim += 1;
		} else {
			totalVotoNao += 1;
		}

		associadosParticipante.add(new Associado(cpfAssociado));
	}

	public boolean sessaoEstaAberta() {
		if (status.equals(Status.ATIVO))
			return true;
		return false;
	}

	public void abreSessao() {
		status = Status.ATIVO;
	}
	
	public void fechaSessao() {
		status = Status.INATIVO;
	}
}
