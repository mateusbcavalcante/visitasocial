package br.com.integracao.visitasocial.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paciente implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private String dtCadastro;

	private String nrCarteira;

	private String nmPaciente;

	private Date dtNascimento;

	private String nrFone;

	private String cdConPlan;

	private Integer cdPrestador;

	private Integer cdLeito;

	private String dtAtendimento;
	
	private String dtAlta;

	private Integer dsCodigoConselho;

	private String cdUf;
	
	private Integer cdMotAlt;

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNrCarteira() {
		return nrCarteira;
	}

	public void setNrCarteira(String nrCarteira) {
		this.nrCarteira = nrCarteira;
	}

	public String getNmPaciente() {
		return nmPaciente;
	}

	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNrFone() {
		return nrFone;
	}

	public void setNrFone(String nrFone) {
		this.nrFone = nrFone;
	}

	public String getCdConPlan() {
		return cdConPlan;
	}

	public void setCdConPlan(String cdConPlan) {
		this.cdConPlan = cdConPlan;
	}

	public Integer getCdPrestador() {
		return cdPrestador;
	}

	public void setCdPrestador(Integer cdPrestador) {
		this.cdPrestador = cdPrestador;
	}

	public Integer getCdLeito() {
		return cdLeito;
	}

	public void setCdLeito(Integer cdLeito) {
		this.cdLeito = cdLeito;
	}

	public String getDtAtendimento() {
		return dtAtendimento;
	}

	public void setDtAtendimento(String dtAtendimento) {
		this.dtAtendimento = dtAtendimento;
	}

	public String getDtAlta() {
		return dtAlta;
	}

	public void setDtAlta(String dtAlta) {
		this.dtAlta = dtAlta;
	}

	public Integer getDsCodigoConselho() {
		return dsCodigoConselho;
	}

	public void setDsCodigoConselho(Integer dsCodigoConselho) {
		this.dsCodigoConselho = dsCodigoConselho;
	}

	public String getCdUf() {
		return cdUf;
	}

	public void setCdUf(String cdUf) {
		this.cdUf = cdUf;
	}
	
	public Integer getCdMotAlt() {
		return cdMotAlt;
	}

	public void setCdMotAlt(Integer cdMotAlt) {
		this.cdMotAlt = cdMotAlt;
	}

	@Override
	public String toString() {
		return "PacienteMV [dtCadastro=" + dtCadastro + ", nrCarteira=" + nrCarteira + ", nmPaciente=" + nmPaciente
				+ ", dtNascimento=" + dtNascimento + ", nrFone=" + nrFone + ", cdConPlan=" + cdConPlan
				+ ", cdPrestador=" + cdPrestador + ", cdLeito=" + cdLeito + ", dtAtendimento=" + dtAtendimento
				+ ", dtAlta=" + dtAlta + ", dsCodigoConselho=" + dsCodigoConselho + ", cdUf=" + cdUf + ", cdMotAlt="
				+ cdMotAlt + "]";
	}
}