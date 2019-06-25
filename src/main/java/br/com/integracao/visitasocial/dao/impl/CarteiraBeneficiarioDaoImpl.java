package br.com.integracao.visitasocial.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.dao.CarteiraBeneficiarioDao;
import br.com.integracao.visitasocial.model.CarteiraBeneficiario;

public class CarteiraBeneficiarioDaoImpl implements CarteiraBeneficiarioDao
{
	static Logger logger = Logger.getLogger(CarteiraBeneficiarioDaoImpl.class);
	
	@Override
	public CarteiraBeneficiario pesquisar(Connection conn, Long codCarteira) throws SQLException
	{
		CarteiraBeneficiario carteiraBeneficiario = null;
		
		try (PreparedStatement pst = conn.prepareStatement(buildSqlSelect(codCarteira));
			 ResultSet rs = pst.executeQuery();)
		{
			if (rs.next())
			{
				carteiraBeneficiario = new CarteiraBeneficiario();
				carteiraBeneficiario.setCodContrato(new Integer(rs.getString(1)));
				carteiraBeneficiario.setCodDependencia(rs.getString(2));
			}
			
			return carteiraBeneficiario;
		}
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
	}
	
	private String buildSqlSelect(Long codCarteira)
	{
		String sql = " SELECT cart.cod_contrato, cont.cod_dependencia "
				   + " FROM sabius.CARTEIRA_BENEFICIARIO cart "
				   + " INNER JOIN sabius.CONTRATOS_BENEFICIARIO cont on cart.cod_beneficiario = cont.cod_beneficiario "
				   + "                                               and cart.cod_unimed = cont.cod_unimed "
				   + "                                               and cart.cod_empresa = cont.cod_empresa "
				   + "                                               and cart.cod_familia = cont.cod_familia "
				   + "                                               and cart.cod_contrato = cont.cod_contrato " 
				   + " WHERE cart.cod_carteira = " + codCarteira;
		
		return sql;
	}
}