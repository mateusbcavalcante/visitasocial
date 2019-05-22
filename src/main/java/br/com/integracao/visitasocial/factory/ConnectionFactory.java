package br.com.integracao.visitasocial.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.constants.BaseConstants;
import br.com.integracao.visitasocial.exceptions.AppException;

public class ConnectionFactory
{
	static Logger logger = Logger.getLogger(ConnectionFactory.class);
	
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private String hostDb;
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	public ConnectionFactory(String hostDb, String portDb, String userDb, String passwordDb) 
	{
		this.userDb = userDb;
		this.portDb = portDb;
		this.passwordDb = passwordDb;
		this.hostDb = hostDb;
	}
	
	public Connection createConnection()
	{
		try 
		{
			String tsNames = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = "
					       + "(PROTOCOL = TCP)(HOST = " + getHostDb() + ")(PORT = " + getPortDb() + ")) "
						   + "(LOAD_BALANCE = yes) "
						   + "(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = mv) "
						   + "(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";
			
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(tsNames, getUserDb(), getPasswordDb());
			
			return connection;
		} 
		catch (ClassNotFoundException e)
		{
			logger.error(BaseConstants.DRIVER_NOT_FOUND);
			throw new AppException(BaseConstants.DRIVER_NOT_FOUND);
	    } 
		catch (SQLException e)
		{
			logger.error(BaseConstants.CONNECT_ERROR);
			throw new AppException(BaseConstants.CONNECT_ERROR);
	    }
	}
	
	public void closeConnection(Connection conexao, PreparedStatement pstmt, ResultSet rs) 
	{
		try 
		{
			if (conexao != null) 
			{
				conexao.close();
			}
			
			if (pstmt != null) 
			{
				pstmt.close();
			}
			
			if (rs != null) 
			{
				rs.close();
			}
		} 
		catch (Exception e)
		{
			logger.error(BaseConstants.CLOSE_CONNECTION_ERROR);
			throw new AppException(BaseConstants.CLOSE_CONNECTION_ERROR);
		}
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