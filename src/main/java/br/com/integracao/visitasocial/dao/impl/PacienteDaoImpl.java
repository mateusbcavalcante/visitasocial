package br.com.integracao.visitasocial.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.integracao.visitasocial.dao.PacienteDao;
import br.com.integracao.visitasocial.model.Paciente;

public class PacienteDaoImpl implements PacienteDao
{
	@Override
	public Paciente resultSetToObject(ResultSet rs) throws SQLException
	{
		int i = 1;
		Paciente paciente = new Paciente();
		
		paciente.setDtCadastro(rs.getString(i++));
		paciente.setNrCarteira(rs.getString(i++));
		paciente.setNmPaciente(rs.getString(i++));
		paciente.setDtNascimento(rs.getDate(i++));
		paciente.setNrFone(rs.getString(i++));
		paciente.setCdConPlan(rs.getString(i++));
		paciente.setCdPrestador(new Integer(rs.getString(i++)));
		paciente.setCdLeito(new Integer(rs.getString(i++)));
		paciente.setDtAtendimento(rs.getString(i++));
		paciente.setDsCodigoConselho(new Integer(rs.getString(i++)));
		paciente.setCdUf(rs.getString(i++));
		paciente.setCdMotAlt(new Integer(rs.getString(i++)));
		paciente.setDtAlta(rs.getString(i++));
		
		return paciente;
	}
}