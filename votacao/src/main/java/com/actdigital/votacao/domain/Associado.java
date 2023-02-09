package com.actdigital.votacao.domain;

import java.util.UUID;

import br.com.caelum.stella.validation.CPFValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Associado {
	
	@Id
	private UUID Id;
	private String cpf;
	private String nome;

	public Associado(String cpf, String nome) {
		Id = UUID.randomUUID();
		this.cpf = cpf;
		this.nome = nome;
	}

	public UUID getId() {
		return Id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public boolean isCPF() {
		var validator = new CPFValidator();
		try {
			validator.assertValid(cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
