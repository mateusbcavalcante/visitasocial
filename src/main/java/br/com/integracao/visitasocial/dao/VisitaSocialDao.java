package br.com.integracao.visitasocial.dao;

import br.com.integracao.visitasocial.model.PacienteMV;
import br.com.integracao.visitasocial.response.VisitaSocialResponse;

public interface VisitaSocialDao
{	
	VisitaSocialResponse inserir(PacienteMV pacienteMV);
}