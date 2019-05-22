package br.com.integracao.visitasocial.dao.impl;

import java.math.BigInteger;
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
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	public PacienteDaoMVImpl(String hostDb, String portDb, String userDb, String passwordDb)
	{
		this.hostDb = hostDb;
		this.portDb = portDb;
		this.userDb = userDb;
		this.passwordDb = passwordDb;
	}

	@Override
	public List<PacienteMV> obterDados() throws SQLException
	{
		Connection conn = null;
		ConnectionFactory instanceConn = new ConnectionFactory(getHostDb(), getPortDb(), getUserDb(), getPasswordDb());
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PacienteMV> listVisitaSocial = new ArrayList<>();
		
		try
		{
			conn = instanceConn.createConnection();
			pst = conn.prepareStatement(buildSqlSelect());
			rs = pst.executeQuery();
			
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
		finally
		{
			instanceConn.closeConnection(conn, pst, rs);
		}
	}
	
	private PacienteMV resultSetToObject(ResultSet rs) throws SQLException
	{
		int i = 1;
		PacienteMV visitaSocial = new PacienteMV();
		
		visitaSocial.setCdUnidInt(new BigInteger(rs.getString(i++)));
		visitaSocial.setDsUnidInt(rs.getString(i++));
		visitaSocial.setCodLeito(new Integer(rs.getString(i++)));
		visitaSocial.setDsLeito(rs.getString(i++));
		visitaSocial.setCdAtendimento(new BigInteger(rs.getString(i++)));
		visitaSocial.setCodPrestador(new BigInteger(rs.getString(i++)));
		visitaSocial.setNmPaciente(rs.getString(i++));
		visitaSocial.setDtAltaMedica(rs.getDate(i++));
		visitaSocial.setHrAltaMedica(rs.getString(i++));
		visitaSocial.setDtAltaHospitalar(rs.getDate(i++));
		visitaSocial.setHrAltaHospitalar(rs.getString(i++));
		visitaSocial.setDtPreMed(rs.getDate(i++));
		
		return visitaSocial;
	}

	private String buildSqlSelect()
	{
		String sql = " SELECT UNID_INT.CD_UNID_INT, "
				   + "        UNID_INT.DS_UNID_INT, "
				   + "        LEITO.CD_LEITO, "
				   + "        LEITO.DS_LEITO, "
				   + "		  ATENDIME.CD_ATENDIMENTO, "
				   + "        ATENDIME.CD_PRESTADOR, "
				   + "		  PACIENTE.NM_PACIENTE, "
				   + "		  TRUNC(ATENDIME.DT_ALTA_MEDICA) DT_ALTA_MEDICA, "
				   + "		  TO_CHAR(ATENDIME.HR_ALTA_MEDICA, 'HH24:MI:SS') HR_ALTA_MEDICA, " 
				   + "		  TRUNC(ATENDIME.DT_ALTA) DT_ALTA_HOSPITALAR, "
				   + "		  TO_CHAR(ATENDIME.HR_ALTA, 'HH24:MI:SS') HR_ALTA_HOSPITALAR, " 
				   + "		  P.DT_PRE_MED "
				   + " FROM DBAMV.UNID_INT, "
				   + "      DBAMV.LEITO, "
				   + "      DBAMV.ATENDIME,"
				   + "      DBAMV.PACIENTE, "
				   + "      DBAMV.PRE_MED P " 
				   + " WHERE LEITO.CD_UNID_INT = UNID_INT.CD_UNID_INT " 
				   + "   AND LEITO.CD_LEITO = ATENDIME.CD_LEITO "
				   + "   AND ATENDIME.CD_PACIENTE = PACIENTE.CD_PACIENTE " 
				   + "   AND ATENDIME.TP_ATENDIMENTO = 'I' "
				   + "   AND ATENDIME.CD_MULTI_EMPRESA = 1 "
				   + "   AND P.CD_ATENDIMENTO = ATENDIME.CD_ATENDIMENTO " 
				   + "   AND ATENDIME.DT_ALTA_MEDICA IS NOT NULL "
				   + "   AND P.DT_PRE_MED BETWEEN SYSDATE-1 AND SYSDATE";
		
		return sql;
	}
	
	public String getHostDb() {
		return hostDb;
	}

	public void setHostDb(String hostDb) {
		this.hostDb = hostDb;
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