package br.com.integracao.visitasocial.execute;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.service.IntegracaoService;
import br.com.integracao.visitasocial.utils.FileUtils;

public class ProcessarIntegracao
{
	static Logger logger = Logger.getLogger(ProcessarIntegracao.class);
	
	private static String uri;
	
	private static String user;
	
	private static String password;
	
	private static String hostDb;
	
	private static String portDb;
	
	private static String userDb;
	
	private static String passwordDb;
	
	public static void main(String args[]) throws Exception
	{
		EraseFile();

		logger.info("Integração iniciada");
		
		initVariables();
		
		if (validation())
		{
			process();
		}
		
		logger.info("Integração finalizada");
	}

	private static void EraseFile()
	{
		FileUtils.apagarConteudo();
	}
	
	private static void initVariables() 
	{
		uri = System.getProperty("uri");
		user = System.getProperty("user");
		password = System.getProperty("password");
		hostDb = System.getProperty("hostdb");
		portDb = System.getProperty("portdb");
		userDb = System.getProperty("userdb");
		passwordDb = System.getProperty("passworddb");
	}
	
	private static Boolean validation() 
	{
		if (uri == null 
				|| uri.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Duri");
			return false;
		}
		
		if (user == null 
				|| user.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Duser");
			return false;
		}
		
		if (password == null 
				|| password.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dpassword");
			return false;
		}
		
		if (hostDb == null 
				|| hostDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dhostdb");
			return false;
		}
		
		if (portDb == null 
				|| portDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dportdb");
			return false;
		}
		
		if (userDb == null 
				|| userDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Duserdb");
			return false;
		}
		
		if (passwordDb == null 
				|| passwordDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dpassworddb");
			return false;
		}
		
		return true;
	}
	
	private static void process() throws Exception 
	{
		new IntegracaoService(uri, user, password, hostDb, portDb, userDb, passwordDb).processar();
	}
}