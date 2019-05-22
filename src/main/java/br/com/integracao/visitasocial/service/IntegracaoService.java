package br.com.integracao.visitasocial.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.dao.impl.PacienteDaoMVImpl;
import br.com.integracao.visitasocial.dao.impl.VisitaSocialDaoImpl;
import br.com.integracao.visitasocial.model.PacienteMV;
import br.com.integracao.visitasocial.response.VisitaSocialResponse;

public class IntegracaoService
{
	static Logger logger = Logger.getLogger(IntegracaoService.class);
	
	private String uri;
	
	private String user;
	
	private String password;
	
	private String hostDb;
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	public IntegracaoService(String uri, String user, String password, String hostDb, String portDb, String userDb, String passwordDb) 
	{
		this.uri = uri;
		this.user = user;
		this.password = password;
		this.hostDb = hostDb;
		this.portDb = portDb;
		this.userDb = userDb;
		this.passwordDb = passwordDb;
	}
	
	public List<VisitaSocialResponse> processar() throws Exception
	{
		List<VisitaSocialResponse> listVisitaSocialResponse = new ArrayList<>();
		List<PacienteMV> listPacienteMV = new PacienteDaoMVImpl(getHostDb(), getPortDb(), getUserDb(), getPasswordDb()).obterDados();
		
		if (listPacienteMV != null
				&& listPacienteMV.size() > 0)
		{
			for (PacienteMV pacienteMV : listPacienteMV)
			{
				VisitaSocialResponse response = new VisitaSocialDaoImpl(getUri(), getUser(), getPassword()).inserir(pacienteMV);
				addLoggerInfo(response);
				listVisitaSocialResponse.add(response);
			}
		}
		return listVisitaSocialResponse;
	}

	private void addLoggerInfo(VisitaSocialResponse response) 
	{
		if (response.getSucesso()
				&& response.getRetorno() != null)
		{
			logger.info("Visita Social inserida com sucesso. ID: " + response.getRetorno().getIdVisitaSocial());
		}
		else
		{
			logger.info(response.getMensagem());
		}
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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