package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoSaida implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer codTipoSaida;
	
	private String descTipoSaida;
	
	public TipoSaida() {
		
	}
	
	public TipoSaida(Integer codTipoSaida) {
		this.codTipoSaida = codTipoSaida;
	}

	public Integer getCodTipoSaida() {
		return codTipoSaida;
	}

	public void setCodTipoSaida(Integer codTipoSaida) {
		this.codTipoSaida = codTipoSaida;
	}

	public String getDescTipoSaida() {
		return descTipoSaida;
	}

	public void setDescTipoSaida(String descTipoSaida) {
		this.descTipoSaida = descTipoSaida;
	}
}