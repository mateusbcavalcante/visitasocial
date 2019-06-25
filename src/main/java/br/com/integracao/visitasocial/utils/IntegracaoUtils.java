package br.com.integracao.visitasocial.utils;

public class IntegracaoUtils
{
	public static final Integer UNIMED_PRODUCAO = 63;
	
	public static boolean isPacienteIntercambio(Integer unimedCarteira)
	{
		return unimedCarteira == null
				|| unimedCarteira.intValue() <= 0
				|| unimedCarteira.intValue() != UNIMED_PRODUCAO;
	}
}