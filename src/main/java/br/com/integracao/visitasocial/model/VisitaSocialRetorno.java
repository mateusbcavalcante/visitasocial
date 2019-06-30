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
	
	private TipoSaida motivoAlta;

	private Integer unimedCarteira;

	private Long codCarteira;

	private String dvCarteira;

	private String paciente;

	private Integer idade;

	private String telefone;

	private String codPlano;

	private Prestador prestador;

	private Integer leito;

	private String dataInternacao;
	
	private String dataAlta;

	private Integer crm;

	private String adesaoMedPrev;

	private String perfilMedPrev;

	private UfCrm ufCrm;

	private Integer codContrato;

	private String codDependencia;
	
	public VisitaSocialRetorno()
	{
		
	}

	public VisitaSocialRetorno(Integer idVisitaSocial, String dataCadastro, Usuario usuario,
								UnimedProducao unimedProducao, TipoSaida motivoAlta, Integer unimedCarteira, Long codCarteira, String dvCarteira,
								String paciente, Integer idade, String telefone, String codPlano, Prestador prestador, Integer leito,
								String dataInternacao, String dataAlta, Integer crm, String adesaoMedPrev, String perfilMedPrev, UfCrm ufCrm,
								Integer codContrato, String codDependencia) 
	{
		super();
		this.idVisitaSocial = idVisitaSocial;
		this.dataCadastro = dataCadastro;
		this.usuario = usuario;
		this.unimedProducao = unimedProducao;
		this.motivoAlta = motivoAlta;
		this.unimedCarteira = unimedCarteira;
		this.codCarteira = codCarteira;
		this.dvCarteira = dvCarteira;
		this.paciente = paciente;
		this.idade = idade;
		this.telefone = telefone;
		this.codPlano = codPlano;
		this.prestador = prestador;
		this.leito = leito;
		this.dataInternacao = dataInternacao;
		this.dataAlta = dataAlta;
		this.crm = crm;
		this.adesaoMedPrev = adesaoMedPrev;
		this.perfilMedPrev = perfilMedPrev;
		this.ufCrm = ufCrm;
		this.codContrato = codContrato;
		this.codDependencia = codDependencia;
	}

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

	public TipoSaida getMotivoAlta() {
		return motivoAlta;
	}

	public void setMotivoAlta(TipoSaida motivoAlta) {
		this.motivoAlta = motivoAlta;
	}

	public Integer getUnimedCarteira() {
		return unimedCarteira;
	}

	public void setUnimedCarteira(Integer unimedCarteira) {
		this.unimedCarteira = unimedCarteira;
	}

	public Long getCodCarteira() {
		return codCarteira;
	}

	public void setCodCarteira(Long codCarteira) {
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
	
	public String getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(String dataAlta) {
		this.dataAlta = dataAlta;
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

	@Override
	public String toString() {
		return "VisitaSocialRetorno [idVisitaSocial=" + idVisitaSocial + ", dataCadastro=" + dataCadastro + ", usuario="
				+ usuario + ", unimedProducao=" + unimedProducao + ", motivoAlta=" + motivoAlta + ", unimedCarteira="
				+ unimedCarteira + ", codCarteira=" + codCarteira + ", dvCarteira=" + dvCarteira + ", paciente="
				+ paciente + ", idade=" + idade + ", telefone=" + telefone + ", codPlano=" + codPlano + ", prestador="
				+ prestador + ", leito=" + leito + ", dataInternacao=" + dataInternacao + ", dataAlta=" + dataAlta
				+ ", crm=" + crm + ", adesaoMedPrev=" + adesaoMedPrev + ", perfilMedPrev=" + perfilMedPrev + ", ufCrm="
				+ ufCrm + ", codContrato=" + codContrato + ", codDependencia=" + codDependencia + "]";
	}
}