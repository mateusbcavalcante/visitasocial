package br.com.integracao.visitasocial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VisitaSocialRetorno implements Serializable {

	private static final long serialVersionUID = -3389333209713569083L;

	private Integer idVisitaSocial;

	private String dataCadastro;

	private Usuario usuario;

	private UnimedProducao unimedProducao;

	private Integer unimedCarteira;

	private Integer codCarteira;

	private String dvCarteira;

	private String paciente;

	private Integer idade;

	private String telefone;

	private String codPlano;

	private Prestador prestador;

	private Integer leito;

	private String dataInternacao;

	private Integer crm;

	private String adesaoMedPrev;

	private String perfilMedPrev;

	private UfCrm ufCrm;

	private Integer codContrato;

	private String codDependencia;

	public Integer getIdVisitaSocial() {
		return idVisitaSocial;
	}

	public void setIdVisitaSocial(Integer idVisitaSocial) {
		this.idVisitaSocial = idVisitaSocial;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UnimedProducao getUnimedProducao() {
		return unimedProducao;
	}

	public void setUnimedProducao(UnimedProducao unimedProducao) {
		this.unimedProducao = unimedProducao;
	}

	public Integer getUnimedCarteira() {
		return unimedCarteira;
	}

	public void setUnimedCarteira(Integer unimedCarteira) {
		this.unimedCarteira = unimedCarteira;
	}

	public Integer getCodCarteira() {
		return codCarteira;
	}

	public void setCodCarteira(Integer codCarteira) {
		this.codCarteira = codCarteira;
	}

	public String getDvCarteira() {
		return dvCarteira;
	}

	public void setDvCarteira(String dvCarteira) {
		this.dvCarteira = dvCarteira;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodPlano() {
		return codPlano;
	}

	public void setCodPlano(String codPlano) {
		this.codPlano = codPlano;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Integer getLeito() {
		return leito;
	}

	public void setLeito(Integer leito) {
		this.leito = leito;
	}

	public String getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(String dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public Integer getCrm() {
		return crm;
	}

	public void setCrm(Integer crm) {
		this.crm = crm;
	}

	public String getAdesaoMedPrev() {
		return adesaoMedPrev;
	}

	public void setAdesaoMedPrev(String adesaoMedPrev) {
		this.adesaoMedPrev = adesaoMedPrev;
	}

	public String getPerfilMedPrev() {
		return perfilMedPrev;
	}

	public void setPerfilMedPrev(String perfilMedPrev) {
		this.perfilMedPrev = perfilMedPrev;
	}

	public UfCrm getUfCrm() {
		return ufCrm;
	}

	public void setUfCrm(UfCrm ufCrm) {
		this.ufCrm = ufCrm;
	}

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