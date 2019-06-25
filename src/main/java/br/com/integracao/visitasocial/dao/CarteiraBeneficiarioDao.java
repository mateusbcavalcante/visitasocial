package br.com.integracao.visitasocial.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.integracao.visitasocial.model.CarteiraBeneficiario;

public interface CarteiraBeneficiarioDao
{	
	CarteiraBeneficiario pesquisar(Connection conn, Long codCarteira) throws SQLException;
}