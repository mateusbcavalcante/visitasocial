package br.com.integracao.visitasocial.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.integracao.visitasocial.model.IntegracaoVisitaSocial;

@XmlRootElement
public class VisitaSocialRequest implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private IntegracaoVisitaSocial integracaoVisitaSocial;
	
	public VisitaSocialRequest() {
		
	}
	
	public VisitaSocialRequest(IntegracaoVisitaSocial integracaoVisitaSocial) {
		this.integracaoVisitaSocial = integracaoVisitaSocial;
	}
	
	public IntegracaoVisitaSocial getIntegracaoVisitaSocial() {
		return integracaoVisitaSocial;
	}

	public void setIntegracaoVisitaSocial(IntegracaoVisitaSocial integracaoVisitaSocial) {
		this.integracaoVisitaSocial = integracaoVisitaSocial;
	}
	
	
}