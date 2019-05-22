package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegracaoVisitaSocial implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;
	
	private VisitaSocial visitaSocial;

	public IntegracaoVisitaSocial() {
		
	}
	
	public IntegracaoVisitaSocial(VisitaSocial visitaSocial) {
		this.visitaSocial = visitaSocial;
	}
	
	public VisitaSocial getVisitaSocial() {
		return visitaSocial;
	}

	public void setVisitaSocial(VisitaSocial visitaSocial) {
		this.visitaSocial = visitaSocial;
	}
}