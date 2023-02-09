package com.actdigital.votacao.domain;

public class Voto {

	private String pautaId;
	private String cpfAssociado;
	private String voto;

	public Voto() {
		// TODO Auto-generated constructor stub
	}
	
	public Voto(String pautaId, String cpfAssociado, String voto) {
		super();
		this.pautaId = pautaId;
		this.cpfAssociado = cpfAssociado;
		this.voto = voto;
	}

	public String getPautaId() {
		return pautaId;
	}

	public String getCpfAssociado() {
		return cpfAssociado;
	}

	public String getVoto() {
		return voto;
	}

}
