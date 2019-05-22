package br.com.integracao.visitasocial.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PacienteMV implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private BigInteger cdUnidInt;

	private String dsUnidInt;

	private Integer codLeito;

	private String dsLeito;

	private BigInteger cdAtendimento;

	private BigInteger codPrestador;

	private String nmPaciente;

	private Date dtAltaMedica;

	private String hrAltaMedica;

	private Date dtAltaHospitalar;

	private String hrAltaHospitalar;

	private Date dtPreMed;

	public BigInteger getCdUnidInt() {
		return cdUnidInt;
	}

	public void setCdUnidInt(BigInteger cdUnidInt) {
		this.cdUnidInt = cdUnidInt;
	}

	public String getDsUnidInt() {
		return dsUnidInt;
	}

	public void setDsUnidInt(String dsUnidInt) {
		this.dsUnidInt = dsUnidInt;
	}

	public String getDsLeito() {
		return dsLeito;
	}

	public void setDsLeito(String dsLeito) {
		this.dsLeito = dsLeito;
	}

	public BigInteger getCdAtendimento() {
		return cdAtendimento;
	}

	public void setCdAtendimento(BigInteger cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}

	public String getNmPaciente() {
		return nmPaciente;
	}

	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}

	public Date getDtAltaMedica() {
		return dtAltaMedica;
	}

	public void setDtAltaMedica(Date dtAltaMedica) {
		this.dtAltaMedica = dtAltaMedica;
	}

	public String getHrAltaMedica() {
		return hrAltaMedica;
	}

	public void setHrAltaMedica(String hrAltaMedica) {
		this.hrAltaMedica = hrAltaMedica;
	}

	public Date getDtAltaHospitalar() {
		return dtAltaHospitalar;
	}

	public void setDtAltaHospitalar(Date dtAltaHospitalar) {
		this.dtAltaHospitalar = dtAltaHospitalar;
	}

	public String getHrAltaHospitalar() {
		return hrAltaHospitalar;
	}

	public void setHrAltaHospitalar(String hrAltaHospitalar) {
		this.hrAltaHospitalar = hrAltaHospitalar;
	}

	public Date getDtPreMed() {
		return dtPreMed;
	}

	public void setDtPreMed(Date dtPreMed) {
		this.dtPreMed = dtPreMed;
	}

	public Integer getCodLeito() {
		return codLeito;
	}

	public void setCodLeito(Integer codLeito) {
		this.codLeito = codLeito;
	}

	public BigInteger getCodPrestador() {
		return codPrestador;
	}

	public void setCodPrestador(BigInteger codPrestador) {
		this.codPrestador = codPrestador;
	}

	@Override
	public String toString() {
		return "PacienteMV [cdUnidInt=" + cdUnidInt + ", dsUnidInt=" + dsUnidInt + ", codLeito=" + codLeito
				+ ", dsLeito=" + dsLeito + ", cdAtendimento=" + cdAtendimento + ", codPrestador=" + codPrestador
				+ ", nmPaciente=" + nmPaciente + ", dtAltaMedica=" + dtAltaMedica + ", hrAltaMedica=" + hrAltaMedica
				+ ", dtAltaHospitalar=" + dtAltaHospitalar + ", hrAltaHospitalar=" + hrAltaHospitalar + ", dtPreMed="
				+ dtPreMed + "]";
	}
}