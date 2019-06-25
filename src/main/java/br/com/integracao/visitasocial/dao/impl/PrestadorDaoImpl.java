package br.com.integracao.visitasocial.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.dao.PrestadorDao;

public class PrestadorDaoImpl implements PrestadorDao
{
	static Logger logger = Logger.getLogger(PrestadorDaoImpl.class);
	
	@Override
	public Integer pesquisarCodigoPrestadorPorCrm(Connection conn, Integer crm) throws SQLException
	{
		Integer codigoPrestador = 0;
		
		try (PreparedStatement pst = conn.prepareStatement(buildSqlSelect(crm));
			 ResultSet rs = pst.executeQuery();)
		{
			if (rs.next())
			{
				codigoPrestador = new Integer(rs.getString(1));
			}
			return codigoPrestador;
		}
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
	}
	
	private String buildSqlSelect(Integer crm)
	{
		String sql = " SELECT p.COD_PRESTADOR "
				   + " FROM sabius.COMPL_PRESTADOR_PF pf "
				   + " INNER JOIN sabius.PRESTADOR p ON pf.cod_prestador = p.cod_prestador "
				   + " WHERE p.UNIMED_PRESTADOR = 63 "
				   + "   AND pf.CRM = " + crm;
		
		return sql;
	}
}