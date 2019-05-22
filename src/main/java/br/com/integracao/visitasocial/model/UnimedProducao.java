package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UnimedProducao implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Unimeds unimeds;

	public UnimedProducao() {
		
	}
	
	public UnimedProducao(Unimeds unimeds) {
		this.unimeds = unimeds;
	}

	public Unimeds getUnimeds() {
		return unimeds;
	}

	public void setUnimeds(Unimeds unimeds) {
		this.unimeds = unimeds;
	}
}