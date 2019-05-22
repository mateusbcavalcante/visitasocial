package br.com.integracao.visitasocial.dao;

import java.util.List;

import br.com.integracao.visitasocial.model.PacienteMV;

public interface PacienteDaoMV
{	
	List<PacienteMV> obterDados() throws Exception;
}