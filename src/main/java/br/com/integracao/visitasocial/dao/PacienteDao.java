package br.com.integracao.visitasocial.dao;

import java.sql.ResultSet;

import br.com.integracao.visitasocial.model.Paciente;

public interface PacienteDao
{	
	Paciente resultSetToObject(ResultSet rs) throws Exception;
}