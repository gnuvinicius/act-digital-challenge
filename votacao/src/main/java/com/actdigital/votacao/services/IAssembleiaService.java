package com.actdigital.votacao.services;

import java.util.List;

import com.actdigital.votacao.domain.Assembleia;

public interface IAssembleiaService {
	
	public List<Assembleia> carregaListaAssembleias();
	
	public void cadastraNovaAssembleia(String titulo, String descricao) throws Exception;
	
	public void cadastraNovaPauta(String assembleiaId, String titulo, String descricao) throws Exception;
	
	public void encerraAssembleia(String assembleiaId) throws Exception;
	
}
