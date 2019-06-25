package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Prestador implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer codPrestador;
	
	public Prestador() {
		
	}
	
	public Prestador(Integer codPrestador) {
		this.codPrestador = codPrestador;
	}

	public Integer getCodPrestador() {
		return codPrestador;
	}

	public void setCodPrestador(Integer codPrestador) {
		this.codPrestador = codPrestador;
	}

	@Override
	public String toString() {
		return "Prestador [codPrestador=" + codPrestador + "]";
	}
}