package br.com.integracao.visitasocial.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.integracao.visitasocial.model.VisitaSocialRetorno;

@XmlRootElement
public class VisitaSocialResponse implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Boolean sucesso;

	private VisitaSocialRetorno retorno;
	
	private String mensagem;

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public VisitaSocialRetorno getRetorno() {
		return retorno;
	}

	public void setRetorno(VisitaSocialRetorno retorno) {
		this.retorno = retorno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}