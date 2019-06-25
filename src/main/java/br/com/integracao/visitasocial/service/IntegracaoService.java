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
	
	private String serviceDb;
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	private String hostSabiusDb;
	
	private String serviceSabiusDb;
	
	private String portSabiusDb;
	
	private String userSabiusDb;
	
	private String passwordSabiusDb;
	
	public IntegracaoService(String uri, String user, String password, String hostDb, String serviceDb, String portDb, String userDb, String passwordDb, String hostSabiusDb, String serviceSabiusDb, String portSabiusDb, String userSabiusDb, String passwordSabiusDb) 
	{
		this.uri = uri;
		this.user = user;
		this.password = password;
		this.hostDb = hostDb;
		this.serviceDb = serviceDb;
		this.portDb = portDb;
		this.userDb = userDb;
		this.passwordDb = passwordDb;
		this.hostSabiusDb = hostSabiusDb;
		this.serviceSabiusDb = serviceSabiusDb;
		this.portSabiusDb = portSabiusDb;
		this.userSabiusDb = userSabiusDb;
		this.passwordSabiusDb = passwordSabiusDb;
	}
	
	public List<VisitaSocialResponse> processar() throws Exception
	{
		List<VisitaSocialResponse> listVisitaSocialResponse = new ArrayList<>();
		List<PacienteMV> listPacienteMV = new PacienteDaoMVImpl(getHostDb(), getServiceDb(), getPortDb(), getUserDb(), getPasswordDb()).obterDados();
		
		if (listPacienteMV != null
				&& listPacienteMV.size() > 0)
		{
			for (PacienteMV pacienteMV : listPacienteMV)
			{
				VisitaSocialResponse response = new VisitaSocialDaoImpl(getUri(), getUser(), getPassword(), getHostSabiusDb(), getServiceSabiusDb(), getPortSabiusDb(), getUserSabiusDb(), getPasswordSabiusDb()).inserir(pacienteMV);
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

	public String getHostSabiusDb() {
		return hostSabiusDb;
	}

	public void setHostSabiusDb(String hostSabiusDb) {
		this.hostSabiusDb = hostSabiusDb;
	}
	
	public String getServiceSabiusDb() {
		return serviceSabiusDb;
	}

	public void setServiceSabiusDb(String serviceSabiusDb) {
		this.serviceSabiusDb = serviceSabiusDb;
	}

	public String getPortSabiusDb() {
		return portSabiusDb;
	}

	public void setPortSabiusDb(String portSabiusDb) {
		this.portSabiusDb = portSabiusDb;
	}

	public String getUserSabiusDb() {
		return userSabiusDb;
	}

	public void setUserSabiusDb(String userSabiusDb) {
		this.userSabiusDb = userSabiusDb;
	}

	public String getPasswordSabiusDb() {
		return passwordSabiusDb;
	}

	public void setPasswordSabiusDb(String passwordSabiusDb) {
		this.passwordSabiusDb = passwordSabiusDb;
	}
}