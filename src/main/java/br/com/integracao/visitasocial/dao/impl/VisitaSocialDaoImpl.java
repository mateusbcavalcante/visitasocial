package br.com.integracao.visitasocial.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import br.com.integracao.visitasocial.constants.BaseConstants;
import br.com.integracao.visitasocial.dao.VisitaSocialDao;
import br.com.integracao.visitasocial.exceptions.AppException;
import br.com.integracao.visitasocial.factory.ConnectionFactory;
import br.com.integracao.visitasocial.interceptor.BasicAuthInterceptor;
import br.com.integracao.visitasocial.model.CarteiraBeneficiario;
import br.com.integracao.visitasocial.model.IntegracaoVisitaSocial;
import br.com.integracao.visitasocial.model.Paciente;
import br.com.integracao.visitasocial.model.Prestador;
import br.com.integracao.visitasocial.model.TipoSaida;
import br.com.integracao.visitasocial.model.UfCrm;
import br.com.integracao.visitasocial.model.UnimedProducao;
import br.com.integracao.visitasocial.model.Unimeds;
import br.com.integracao.visitasocial.model.Usuario;
import br.com.integracao.visitasocial.model.VisitaSocial;
import br.com.integracao.visitasocial.model.VisitaSocialRetorno;
import br.com.integracao.visitasocial.request.VisitaSocialRequest;
import br.com.integracao.visitasocial.response.VisitaSocialResponse;
import br.com.integracao.visitasocial.utils.DateUtils;
import br.com.integracao.visitasocial.utils.IntegracaoUtils;

public class VisitaSocialDaoImpl implements VisitaSocialDao
{
	static Logger logger = Logger.getLogger(VisitaSocialDaoImpl.class);
	
	private String uri;
	
	private String user;
	
	private String password;
	
	private String hostSabiusDb;
	
	private String serviceSabiusDb;
	
	private String portSabiusDb;
	
	private String userSabiusDb;
	
	private String passwordSabiusDb;
	
	public VisitaSocialDaoImpl(String uri, String user, String password, String hostSabiusDb, String serviceSabiusDb, String portSabiusDb, String userSabiusDb, String passwordSabiusDb)
	{
		this.uri = uri;
		this.user = user;
		this.password = password;
		this.hostSabiusDb = hostSabiusDb;
		this.serviceSabiusDb = serviceSabiusDb;
		this.portSabiusDb = portSabiusDb;
		this.userSabiusDb = userSabiusDb;
		this.passwordSabiusDb = passwordSabiusDb;
	}
	
	@Override
	public VisitaSocialResponse inserir(Paciente pacienteMV)
	{
		VisitaSocialResponse visitaSocialResponse = new VisitaSocialResponse();
		RestTemplate restTemplate = new RestTemplate();
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new BasicAuthInterceptor(getUser(), getPassword()));
        restTemplate.setInterceptors(interceptors);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
        VisitaSocialRequest visitaSocialRequest = instanciarObjetoParaInserir(pacienteMV);
        
        if (!IntegracaoUtils.isPacienteIntercambio(visitaSocialRequest.getIntegracaoVisitaSocial().getVisitaSocial().getUnimedCarteira()))
        {
        	HttpEntity<VisitaSocialRequest> entity = new HttpEntity<VisitaSocialRequest>(visitaSocialRequest, headers);
        	visitaSocialResponse = restTemplate.postForObject(getUri(), entity, VisitaSocialResponse.class);
        }
        else
        {
        	visitaSocialResponse.setSucesso(false);
        	visitaSocialResponse.setMensagem(BaseConstants.PACIENTE_INTERCAMBIO_ERROR);
        	visitaSocialResponse.setRetorno(buildVisitaSocialRetorno(visitaSocialRequest));
        }
        
        return visitaSocialResponse;
	}

	private VisitaSocialRetorno buildVisitaSocialRetorno(VisitaSocialRequest visitaSocialRequest) 
	{
		VisitaSocial visitaSocial = visitaSocialRequest.getIntegracaoVisitaSocial().getVisitaSocial();
		
		return new VisitaSocialRetorno(visitaSocial.getIdVisitaSocial(), visitaSocial.getDataCadastro(), visitaSocial.getUsuario(),
				                       visitaSocial.getUnimedProducao(), visitaSocial.getMotivoAlta(), visitaSocial.getUnimedCarteira(), visitaSocial.getCodCarteira(), visitaSocial.getDvCarteira(),
				                       visitaSocial.getPaciente(), visitaSocial.getIdade(), visitaSocial.getTelefone(), visitaSocial.getCodPlano(), visitaSocial.getPrestador(), visitaSocial.getLeito(),
				                       visitaSocial.getDataInternacao(), visitaSocial.getDataAlta(), visitaSocial.getCrm(), visitaSocial.getAdesaoMedPrev(), visitaSocial.getPerfilMedPrev(), new UfCrm(visitaSocial.getUfCrm()),
				                       visitaSocial.getCodContrato(), visitaSocial.getCodDependencia());
	}

	private VisitaSocialRequest instanciarObjetoParaInserir(Paciente pacienteMV) 
	{
		Integer unimedCarteira = null;
		Long codCarteira = null;
		String dvCarteira = null;
		
		if (pacienteMV != null
				&& pacienteMV.getNrCarteira() != null
				&& !pacienteMV.getNrCarteira().equalsIgnoreCase(""))
		{
			unimedCarteira = new Integer(pacienteMV.getNrCarteira().substring(0, 3));
			codCarteira = new Long(pacienteMV.getNrCarteira().substring(3, pacienteMV.getNrCarteira().length()-1));
			dvCarteira = pacienteMV.getNrCarteira().substring(pacienteMV.getNrCarteira().length()-1);
		}
		
		ConnectionFactory instanceConn = new ConnectionFactory(getHostSabiusDb(), getServiceSabiusDb(), getPortSabiusDb(), getUserSabiusDb(), getPasswordSabiusDb());
		VisitaSocial visitaSocial = new VisitaSocial();
		VisitaSocialRequest visitaSocialRequest = new VisitaSocialRequest();
		
		try (Connection conn = instanceConn.createConnection())
		{
			CarteiraBeneficiario carteiraBeneficiario = new CarteiraBeneficiarioDaoImpl().pesquisar(conn, codCarteira);
			
			visitaSocial.setDataCadastro(DateUtils.removerHoraByDate(new Date()));
			visitaSocial.setUsuario(new Usuario("PRISCILAMARINHO"));
			visitaSocial.setUnimedProducao(new UnimedProducao(new Unimeds(IntegracaoUtils.UNIMED_PRODUCAO)));
			visitaSocial.setUnimedCarteira(unimedCarteira);
			visitaSocial.setCodCarteira(codCarteira);
			visitaSocial.setDvCarteira(dvCarteira);
			visitaSocial.setPaciente(pacienteMV.getNmPaciente());
			visitaSocial.setIdade(DateUtils.calculaIdade(pacienteMV.getDtNascimento()));
			visitaSocial.setTelefone(pacienteMV.getNrFone());
			visitaSocial.setCodPlano(pacienteMV.getCdConPlan());
			visitaSocial.setPrestador(new Prestador(11005116));
			visitaSocial.setLeito(pacienteMV.getCdLeito());
			visitaSocial.setDataInternacao(DateUtils.removerHoraByDateStr(pacienteMV.getDtAtendimento()));
			visitaSocial.setCrm(pacienteMV.getDsCodigoConselho());
			visitaSocial.setAdesaoMedPrev("N");
			visitaSocial.setPerfilMedPrev("N");
			visitaSocial.setUfCrm(pacienteMV.getCdUf());
			
			if (pacienteMV.getDtAlta() != null
					&& !pacienteMV.getDtAlta().equalsIgnoreCase("")) 
			{
				visitaSocial.setMotivoAlta(new TipoSaida(IntegracaoUtils.getMapTipoSaida(pacienteMV.getCdMotAlt())));
				visitaSocial.setDataAlta(DateUtils.removerHoraByDateStr(pacienteMV.getDtAlta()));
			}
			
			if (carteiraBeneficiario != null)
			{
				visitaSocial.setCodContrato(carteiraBeneficiario.getCodContrato());
				visitaSocial.setCodDependencia(carteiraBeneficiario.getCodDependencia());
			}
			
			visitaSocialRequest = new VisitaSocialRequest(new IntegracaoVisitaSocial(visitaSocial));
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new AppException(ex.getMessage());
		}
		
		logger.info(visitaSocial.toString());
		
		return visitaSocialRequest;
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