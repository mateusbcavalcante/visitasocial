package br.com.integracao.visitasocial.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.dao.PacienteDaoMV;
import br.com.integracao.visitasocial.exceptions.AppException;
import br.com.integracao.visitasocial.factory.ConnectionFactory;
import br.com.integracao.visitasocial.model.PacienteMV;

public class PacienteDaoMVImpl implements PacienteDaoMV
{
	static Logger logger = Logger.getLogger(PacienteDaoMVImpl.class);
	
	private String hostDb;
	
	private String serviceDb;
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	public PacienteDaoMVImpl(String hostDb, String serviceDb, String portDb, String userDb, String passwordDb)
	{
		this.hostDb = hostDb;
		this.serviceDb = serviceDb;
		this.portDb = portDb;
		this.userDb = userDb;
		this.passwordDb = passwordDb;
	}

	@Override
	public List<PacienteMV> obterDados() throws SQLException
	{
		ConnectionFactory instanceConn = new ConnectionFactory(getHostDb(), getServiceDb(), getPortDb(), getUserDb(), getPasswordDb());
		List<PacienteMV> listVisitaSocial = new ArrayList<>();
		
		try (Connection conn = instanceConn.createConnection();
			 PreparedStatement pst = conn.prepareStatement(buildSqlSelect());
			 ResultSet rs = pst.executeQuery())
		{
			while (rs.next())
			{
				listVisitaSocial.add(resultSetToObject(rs));
			}
			return listVisitaSocial;
		}
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new AppException(ex.getMessage());
		}
	}
	
	private PacienteMV resultSetToObject(ResultSet rs) throws SQLException
	{
		int i = 1;
		PacienteMV pacienteMV = new PacienteMV();
		
		pacienteMV.setDtCadastro(rs.getString(i++));
		pacienteMV.setNrCarteira(rs.getString(i++));
		pacienteMV.setNmPaciente(rs.getString(i++));
		pacienteMV.setDtNascimento(rs.getDate(i++));
		pacienteMV.setNrFone(rs.getString(i++));
		pacienteMV.setCdConPlan(rs.getString(i++));
		pacienteMV.setCdPrestador(new Integer(rs.getString(i++)));
		pacienteMV.setCdLeito(new Integer(rs.getString(i++)));
		pacienteMV.setDtAtendimento(rs.getString(i++));
		pacienteMV.setDsCodigoConselho(new Integer(rs.getString(i++)));
		pacienteMV.setCdUf(rs.getString(i++));
		pacienteMV.setCdMotAlt(new Integer(rs.getString(i++)));
		pacienteMV.setDtAlta(rs.getString(i++));
		
		return pacienteMV;
	}

	private String buildSqlSelect()
	{
		String sql = " SELECT PACIENTE.DT_CADASTRO, "
				   + "        ATENDIME.NR_CARTEIRA, "
				   + "        PACIENTE.NM_PACIENTE, "
				   + "        PACIENTE.DT_NASCIMENTO, " 
				   + "        PACIENTE.NR_FONE, "
				   + "        ATENDIME.CD_CON_PLA, "
				   + "        PRESTADOR.CD_PRESTADOR, " 
				   + "        LEITO.CD_LEITO, "
				   + "        ATENDIME.DT_ATENDIMENTO, " 
				   + "        PRESTADOR.CD_PRESTADOR, " 
				   + "        PRESTADOR.DS_CODIGO_CONSELHO, " 
				   + "        CONSELHO.CD_UF, "
				   + "        ATENDIME.CD_MOT_ALT, "
				   + "        ATENDIME.DT_ALTA "
				   + " FROM DBAMV.UNID_INT, "
				   + "      DBAMV.LEITO, "
				   + "      DBAMV.ATENDIME, "
				   + "      DBAMV.PACIENTE,  "
				   + "      DBAMV.PRE_MED P, "
				   + "      DBAMV.PRESTADOR, "
				   + "      DBAMV.CONSELHO " 
				   + " WHERE LEITO.CD_UNID_INT = UNID_INT.CD_UNID_INT "
				   + "   AND LEITO.CD_LEITO = ATENDIME.CD_LEITO "
				   + "   AND ATENDIME.CD_PACIENTE = PACIENTE.CD_PACIENTE "
				   + "   AND ATENDIME.TP_ATENDIMENTO = 'I' "
				   + "   AND ATENDIME.CD_MULTI_EMPRESA = 1 "
				   + "   AND P.CD_ATENDIMENTO = ATENDIME.CD_ATENDIMENTO "
				   + "   AND ATENDIME.CD_PRESTADOR = PRESTADOR.CD_PRESTADOR "
				   + "   AND PRESTADOR.CD_CONSELHO = CONSELHO.CD_CONSELHO "
				   + "   AND ATENDIME.DT_ALTA_MEDICA IS NOT NULL "
				   + "   AND p.DT_PRE_MED BETWEEN SYSDATE-1 AND SYSDATE ";
		
		return sql;
	}
	
	public String getHostDb() {
		return hostDb;
	}

	public void setHostDb(String hostDb) {
		this.hostDb = hostDb;
	}
	
	public String getServiceDb() {
		return serviceDb;
	}

	public void setServiceDb(String serviceDb) {
		this.serviceDb = serviceDb;
	}

	public String getPortDb() {
		return portDb;
	}

	public void setPortDb(String portDb) {
		this.portDb = portDb;
	}

	public String getUserDb() {
		return userDb;
	}

	public void setUserDb(String userDb) {
		this.userDb = userDb;
	}

	public String getPasswordDb() {
		return passwordDb;
	}

	public void setPasswordDb(String passwordDb) {
		this.passwordDb = passwordDb;
	}
}