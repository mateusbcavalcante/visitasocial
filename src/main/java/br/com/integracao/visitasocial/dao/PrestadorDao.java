package br.com.integracao.visitasocial.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface PrestadorDao
{	
	Integer pesquisarCodigoPrestadorPorCrm(Connection conn, Integer crm) throws SQLException;
}