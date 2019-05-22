package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private String codigoUsuario;

	public Usuario() {
		
	}
	
	public Usuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
}