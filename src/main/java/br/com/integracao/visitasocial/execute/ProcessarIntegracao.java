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
	
	private static String serviceDb;
	
	private static String portDb;
	
	private static String userDb;
	
	private static String passwordDb;
	
	private static String hostSabiusDb;
	
	private static String serviceSabiusDb;
	
	private static String portSabiusDb;
	
	private static String userSabiusDb;
	
	private static String passwordSabiusDb;
	
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
//		System.setProperty("uri", "http://tf1jboss.unimedfortaleza.com.br:8081/sabius-servicos-web/rest-servicos/visitaSocial/incluir");
//		System.setProperty("user", "SALESFORCE");
//		System.setProperty("password", "2al3F0RC3");
//		System.setProperty("hostdb", "racmv.unimedfortaleza.com.br");
//		System.setProperty("servicedb", "mv");
//		System.setProperty("portdb", "1528");
//		System.setProperty("userdb", "reader");
//		System.setProperty("passworddb", "reader");
//		System.setProperty("hostsabiusdb", "racsab.unimedfortaleza.com.br");
//		System.setProperty("servicesabiusdb", "sab");
//		System.setProperty("portsabiusdb", "1522");
//		System.setProperty("usersabiusdb", "reader");
//		System.setProperty("passwordsabiusdb", "reader");
		
		uri = System.getProperty("uri");
		user = System.getProperty("user");
		password = System.getProperty("password");
		hostDb = System.getProperty("hostdb");
		serviceDb = System.getProperty("servicedb");
		portDb = System.getProperty("portdb");
		userDb = System.getProperty("userdb");
		passwordDb = System.getProperty("passworddb");
		hostSabiusDb = System.getProperty("hostsabiusdb");
		serviceSabiusDb = System.getProperty("servicesabiusdb");
		portSabiusDb = System.getProperty("portsabiusdb");
		userSabiusDb = System.getProperty("usersabiusdb");
		passwordSabiusDb = System.getProperty("passwordsabiusdb");
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
		
		if (serviceDb == null 
				|| serviceDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dservicedb");
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
		
		if (hostSabiusDb == null 
				|| hostSabiusDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dhostsabiusdb");
			return false;
		}
		
		if (serviceSabiusDb == null 
				|| serviceSabiusDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dservicesabiusdb");
			return false;
		}
		
		if (portSabiusDb == null 
				|| portSabiusDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dportsabiusdb");
			return false;
		}
		
		if (userSabiusDb == null 
				|| userSabiusDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dusersabiusdb");
			return false;
		}
		
		if (passwordSabiusDb == null 
				|| passwordSabiusDb.equalsIgnoreCase(""))
		{
			logger.info("É necessário informar o campo: -Dpasswordsabiusdb");
			return false;
		}
		
		return true;
	}
	
	private static void process() throws Exception 
	{
		new IntegracaoService(uri, user, password, hostDb, serviceDb, portDb, userDb, passwordDb, hostSabiusDb, serviceSabiusDb, portSabiusDb, userSabiusDb, passwordSabiusDb).processar();
	}
}