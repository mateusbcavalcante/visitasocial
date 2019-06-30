package br.com.integracao.visitasocial.dao;

import java.util.List;

import br.com.integracao.visitasocial.model.Paciente;

public interface PacienteDaoMV
{	
	List<Paciente> obterDados() throws Exception;
}