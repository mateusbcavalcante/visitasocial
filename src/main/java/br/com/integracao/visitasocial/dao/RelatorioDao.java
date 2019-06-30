package br.com.integracao.visitasocial.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.integracao.visitasocial.model.Paciente;

public interface RelatorioDao
{	
	List<Paciente> processarPacientes() throws SQLException;
}