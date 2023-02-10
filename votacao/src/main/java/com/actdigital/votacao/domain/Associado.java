package com.actdigital.votacao.domain;

import java.util.UUID;

import br.com.caelum.stella.validation.CPFValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Associado {

	@Id
	private UUID id;
	private String cpf;

	public Associado() {
	}

	public Associado(String cpf) {
		id = UUID.randomUUID();
		this.cpf = cpf;
	}

	public UUID getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public static boolean isCPF(String cpf) {
		var validator = new CPFValidator();
		try {
			validator.assertValid(cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
