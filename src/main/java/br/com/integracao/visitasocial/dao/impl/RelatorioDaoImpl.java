package br.com.integracao.visitasocial.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.dao.RelatorioDao;
import br.com.integracao.visitasocial.factory.ConnectionFactory;
import br.com.integracao.visitasocial.model.GeradorProcessamento;
import br.com.integracao.visitasocial.model.Paciente;
import br.com.integracao.visitasocial.utils.DateUtils;

public class RelatorioDaoImpl extends PacienteDaoImpl implements RelatorioDao
{
	static Logger logger = Logger.getLogger(RelatorioDaoImpl.class);
	
	private String hostDb;
	
	private String serviceDb;
	
	private String portDb;
	
	private String userDb;
	
	private String passwordDb;
	
	public RelatorioDaoImpl(String hostDb, String serviceDb, String portDb, String userDb, String passwordDb)
	{
		this.hostDb = hostDb;
		this.serviceDb = serviceDb;
		this.portDb = portDb;
		this.userDb = userDb;
		this.passwordDb = passwordDb;
	}
	
	@Override
	public List<Paciente> processarPacientes() throws SQLException
	{
		ConnectionFactory instanceConn = new ConnectionFactory(getHostDb(), getServiceDb(), getPortDb(), getUserDb(), getPasswordDb());
		List<Paciente> listPaciente = new ArrayList<>();
		
		try (Connection conn = instanceConn.createConnection())
		{
			listPaciente.addAll(autorizacoesSolicitacoesCedidas(conn));
			listPaciente.addAll(correcaoGeracaoDiasUteis(conn));
		} 
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
		
		return listPaciente;
	}
	
	private List<Paciente> autorizacoesSolicitacoesCedidas(Connection conn) throws SQLException 
	{
		List<Paciente> listPaciente = new ArrayList<>();
		
		try (PreparedStatement pst = conn.prepareStatement(buildSqlSelectFromAutorizacoesSolicitacoesCedidas());
			 ResultSet rs = pst.executeQuery();)
		{
			if (rs.next())
			{
				listPaciente.add(resultSetToObjectFromAutorizacoesSolicitacoesCedidas(rs));
			}
			return listPaciente;
		}
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
	}
	
	public Paciente resultSetToObjectFromAutorizacoesSolicitacoesCedidas(ResultSet rs) throws SQLException
	{
		int i = 1;
		Paciente paciente = new Paciente();
		
		paciente.setNrCarteira(rs.getString(i++));
		paciente.setNmPaciente(rs.getString(i++));
		paciente.setDtNascimento(rs.getDate(i++));
		paciente.setDtAtendimento(rs.getString(i++));
		paciente.setCdUf(rs.getString(i++));
		paciente.setCdPrestador(new Integer(rs.getString(i++)));
		
		return paciente;
	}
	
	private String buildSqlSelectFromAutorizacoesSolicitacoesCedidas()
	{
//		private String dtCadastro;
//		private String nrCarteira;
//		private String nmPaciente;
//		private Date dtNascimento;
//		private String nrFone;
//		private String cdConPlan;
//		private Integer cdPrestador;
//		private Integer cdLeito;
//		private String dtAtendimento;
//		private String dtAlta;
//		private Integer dsCodigoConselho;
//		private String cdUf;
//		private Integer cdMotAlt;
		
		String sql = " select cb.unimed_carteira || ' ' || cb.cod_carteira || '-' || cb.dv_carteira as carteira, " + 
				"        b.nome_beneficiario, " +
				"        b.DATA_NASCIMENTO, " + 
				"        g.data_internacao, " + 
				"        t.cod_requisitante as crm, " +
				"        t.cod_prestador " + 
				"   from transacoes                t, " + 
				"        carteira_beneficiario     cb, " + 
				"        beneficiario              b, " + 
				"        servicos_transacoes       st, " + 
				"        serv_medicos_hospitalares smh, " + 
				"        guias_hosp                g, " + 
				"        guias_peq_cirurgias       gpc, " + 
				"        prestador                 p1, " + 
				"        prestador                 p2, " + 
				"        contratos_beneficiario    cbc, " + 
				"        lotacoes                   l, " + 
				"        familias                   f " + 
				"  where t.unimed_cartao_prest = cb.unimed_carteira " + 
				"    and t.cod_carteira = cb.cod_carteira " + 
				"    and t.dv_carteira = cb.dv_carteira " + 
				"    and cb.cod_beneficiario = b.cod_beneficiario " + 
				"    and nvl(t.num_transacao,-1) > -1 " + 
				"    and t.data_transacao >= sysdate - 1 " + 
				"    and t.cod_unimed_prest = 63 " + 
				"    and t.tipo_nota in (10, 5) " + 
				"    and t.tipo_transacao IN (1, 5) " + 
				"    and cb.cod_contrato in (4654, 20) " + 
				"    and t.num_transacao = st.num_transacao " + 
				"    and st.result_transacao = 1 " + 
				"    and st.qtde_serv_med_hosp > 0 " + 
				"    and st.cod_serv_med_hosp = smh.cod_servico " + 
				"    and st.dv_serv_med_hosp = smh.dv_servico " + 
				"    and t.num_nota = g.num_nota(+) " + 
				"    AND G.TIPO_PRORROGACAO <> 2 " + 
				"    and t.num_nota = gpc.num_nota(+) " + 
				"    and p1.cod_prestador = t.cod_requisitante " + 
				"    and p2.cod_prestador = t.cod_prestador " + 
				"    and cb.cod_beneficiario = cbc.cod_beneficiario " + 
				"    and cb.cod_unimed = cbc.cod_unimed " + 
				"    and cb.cod_contrato = cbc.cod_contrato " + 
				"    and cb.cod_familia = cbc.cod_familia " + 
				"    and cb.cod_empresa = cbc.cod_empresa " + 
				"    and f.cod_unimed = cb.cod_unimed " + 
				"            and f.cod_empresa = cb.cod_empresa " + 
				"            and f.cod_familia = cb.cod_familia " + 
				"            and nvl(f.data_excl, sysdate) >= sysdate " + 
				"            and l.cod_unimed = f.cod_unimed " + 
				"            and l.cod_empresa = f.Cod_Empresa " + 
				"            and l.cod_lotacao = f.cod_lotacao " + 
				"    group by b.nome_beneficiario, " + 
				"          cb.unimed_carteira, " + 
				"          cb.cod_carteira, " + 
				"          cb.dv_carteira, " + 
				"          cb.cod_contrato, " + 
				"          t.num_nota, " + 
				"          t.cod_requisitante, " + 
				"          p1.nome_prestador, " + 
				"          b.data_nascimento, " + 
				"          cbc.cod_dependencia, " + 
				"          l.desc_lotacao, " + 
				"          t.cod_prestador, " + 
				"          p2.nome_prestador, " + 
				"          t.obs_justificativa, " + 
				"          cb.cod_unimed, " + 
				"          cb.cod_empresa, " + 
				"          cb.cod_familia, " + 
				"          g.data_internacao, " + 
				"          gpc.data_atendimento " + 
				"ORDER BY 1 ";
		
		return sql;
	}
	
	private List<Paciente> correcaoGeracaoDiasUteis(Connection conn) throws SQLException 
	{
		List<Paciente> listPaciente = new ArrayList<>();
		
		try (PreparedStatement pst = conn.prepareStatement(buildSqlSelectGeradorProcessamento());
			 ResultSet rs = pst.executeQuery();)
		{
			if (rs.next())
			{
				List<GeradorProcessamento> listGeradorProcessamento = new ArrayList<>();
				listGeradorProcessamento.add(resultSetToObjectFromGeradorProcessamento(rs));
				
				if (listGeradorProcessamento != null
						&& listGeradorProcessamento.size() > 0)
				{
					for (GeradorProcessamento element : listGeradorProcessamento)
					{
						if (element.getStatus() != null
								&& !element.getStatus().equalsIgnoreCase(""))
						{
							String dataUtilAtualStr = DateUtils.retornaDataUtil(1);
							String proximaDataUtilStr = DateUtils.retornaDataUtil(2);
							
							if (element.getStatus().equalsIgnoreCase("A"))
							{
								if (element.getDataAgendamento() != null)
								{
									String dataAgendamentoStr = DateUtils.convertToLocalDateViaInstant(element.getDataAgendamento());
									
									if (!dataAgendamentoStr.equalsIgnoreCase(dataUtilAtualStr))
									{
										
									}
								}
							}
							
							if (element.getStatus().equalsIgnoreCase("I"))
							{
								
							}
						}
					}
				}
//				listPaciente.add(resultSetToObjectFromGeradorProcessamento(rs));
			}
			return listPaciente;
		}
		catch (SQLException ex)
		{
			logger.error(ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
	}
	
	private String buildSqlSelectGeradorProcessamento()
	{
		String sql = " SELECT DISTINCT GP.COD_RELATORIO, " + 
				"                GP.COD_PROCESSAMENTO, " + 
				"                GP.STATUS, " + 
				"                TO_DATE(TO_CHAR(GP.DATA_AGENDAMENTO, 'DD/MM/YYYY'), " + 
				"                        'DD/MM/YYYY') DATA_AGENDAMENTO, " + 
				"                TO_CHAR(GP.DATA_AGENDAMENTO, 'HH24') HORA, " + 
				"                TO_CHAR(GP.DATA_AGENDAMENTO, 'MI') MINUTO, " + 
				"                TO_CHAR(GP.DATA_AGENDAMENTO, 'SS') SEGUNDO " + 
				"  FROM GERADOR_PROCESSAMENTO GP " + 
				" WHERE GP.COD_RELATORIO in (3002, 2999, 2998, 3000, 3001, 3006) " + 
				"   AND TO_DATE(TO_CHAR(GP.DATA_AGENDAMENTO, 'DD/MM/YYYY'), 'DD/MM/YYYY') = " + 
				"       TO_DATE(TO_CHAR(SYSDATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') + 1 " + 
				"   AND GP.STATUS = 'A' " + 
				"   AND GP.EMAIL <> 'alexandre.amorim@unimedfortaleza.com.br' ";
		
		return sql;
	}
	
	public GeradorProcessamento resultSetToObjectFromGeradorProcessamento(ResultSet rs) throws SQLException
	{
		int i = 1;
		GeradorProcessamento geradorProcessamento = new GeradorProcessamento();
		
		geradorProcessamento.setCdRelatorio(new Integer(rs.getString(i++)));
		geradorProcessamento.setCdProcessamento(new Integer(rs.getString(i++)));
		geradorProcessamento.setDataAgendamento(rs.getDate(i++));
		geradorProcessamento.setHora(rs.getString(i++));
		geradorProcessamento.setMinuto(rs.getString(i++));
		geradorProcessamento.setSegundo(rs.getString(i++));
		
		return geradorProcessamento;
	}
	
	public String getHostDb() {
		return hostDb;
	}

	public void setHostDb(String hostDb) {
		this.hostDb = hostDb;
	}

	public String getServiceDb() {
		return serviceDb;
	}

	public void setServiceDb(String serviceDb) {
		this.serviceDb = serviceDb;
	}

	public String getPortDb() {
		return portDb;
	}

	public void setPortDb(String portDb) {
		this.portDb = portDb;
	}

	public String getUserDb() {
		return userDb;
	}

	public void setUserDb(String userDb) {
		this.userDb = userDb;
	}

	public String getPasswordDb() {
		return passwordDb;
	}

	public void setPasswordDb(String passwordDb) {
		this.passwordDb = passwordDb;
	}
}