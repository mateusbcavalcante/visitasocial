package br.com.integracao.visitasocial.model;

import java.io.Serializable;

public class CarteiraBeneficiario implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer codContrato;

	private String codDependencia;

	public Integer getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(Integer codContrato) {
		this.codContrato = codContrato;
	}

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
}