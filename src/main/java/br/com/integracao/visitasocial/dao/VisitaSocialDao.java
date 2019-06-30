package br.com.integracao.visitasocial.dao;

import br.com.integracao.visitasocial.model.Paciente;
import br.com.integracao.visitasocial.response.VisitaSocialResponse;

public interface VisitaSocialDao
{	
	VisitaSocialResponse inserir(Paciente pacienteMV);
}