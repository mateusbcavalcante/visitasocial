package br.com.integracao.visitasocial.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import br.com.integracao.visitasocial.dao.VisitaSocialDao;
import br.com.integracao.visitasocial.interceptor.BasicAuthInterceptor;
import br.com.integracao.visitasocial.model.IntegracaoVisitaSocial;
import br.com.integracao.visitasocial.model.PacienteMV;
import br.com.integracao.visitasocial.model.Prestador;
import br.com.integracao.visitasocial.model.UnimedProducao;
import br.com.integracao.visitasocial.model.Unimeds;
import br.com.integracao.visitasocial.model.Usuario;
import br.com.integracao.visitasocial.model.VisitaSocial;
import br.com.integracao.visitasocial.request.VisitaSocialRequest;
import br.com.integracao.visitasocial.response.VisitaSocialResponse;

public class VisitaSocialDaoImpl implements VisitaSocialDao
{
	private String uri;
	
	private String user;
	
	private String password;
	
	public VisitaSocialDaoImpl(String uri, String user, String password)
	{
		this.uri = uri;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public VisitaSocialResponse inserir(PacienteMV pacienteMV)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new BasicAuthInterceptor(getUser(), getPassword()));
        restTemplate.setInterceptors(interceptors);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
        HttpEntity<VisitaSocialRequest> entity = new HttpEntity<VisitaSocialRequest>(instanciarObjetoParaInserir(pacienteMV), headers);
        
        return restTemplate.postForObject(getUri(), entity, VisitaSocialResponse.class);
	}

	private VisitaSocialRequest instanciarObjetoParaInserir(PacienteMV pacienteMV) 
	{
		VisitaSocial visitaSocial = new VisitaSocial();
		visitaSocial.setDataCadastro(new Date());
		visitaSocial.setUsuario(new Usuario("PRISCILAMARINHO"));
		visitaSocial.setUnimedProducao(new UnimedProducao(new Unimeds(63)));
		visitaSocial.setUnimedCarteira(63);
		visitaSocial.setCodCarteira(200447974);
		visitaSocial.setDvCarteira("0");
		visitaSocial.setPaciente("SEBASTIAO FREIRE");
		visitaSocial.setIdade(20);
		visitaSocial.setTelefone("0000000");
		visitaSocial.setCodPlano("N19");
		visitaSocial.setPrestador(new Prestador(11005098));
		visitaSocial.setLeito(701);
		visitaSocial.setDataInternacao(new Date());
		visitaSocial.setCrm(5023);
		visitaSocial.setAdesaoMedPrev("N");
		visitaSocial.setPerfilMedPrev("N");
		visitaSocial.setUfCrm("CE");
		visitaSocial.setCodContrato(9400);
		visitaSocial.setCodDependencia("D");
		
		return new VisitaSocialRequest(new IntegracaoVisitaSocial(visitaSocial));
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
}