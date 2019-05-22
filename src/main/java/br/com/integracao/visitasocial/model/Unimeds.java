package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Unimeds implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer codUnimed;
	
	public Unimeds() {
		
	}
	
	public Unimeds(Integer codUnimed) {
		this.codUnimed = codUnimed;
	}

	public Integer getCodUnimed() {
		return codUnimed;
	}

	public void setCodUnimed(Integer codUnimed) {
		this.codUnimed = codUnimed;
	}
}