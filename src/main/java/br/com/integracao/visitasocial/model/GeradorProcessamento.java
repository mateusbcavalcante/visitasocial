package br.com.integracao.visitasocial.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeradorProcessamento implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer cdRelatorio;

	private Integer cdProcessamento;
	
	private String status;

	private Date dataAgendamento;

	private String hora;

	private String minuto;

	private String segundo;

	public Integer getCdRelatorio() {
		return cdRelatorio;
	}

	public void setCdRelatorio(Integer cdRelatorio) {
		this.cdRelatorio = cdRelatorio;
	}

	public Integer getCdProcessamento() {
		return cdProcessamento;
	}

	public void setCdProcessamento(Integer cdProcessamento) {
		this.cdProcessamento = cdProcessamento;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}

	public String getSegundo() {
		return segundo;
	}

	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
}