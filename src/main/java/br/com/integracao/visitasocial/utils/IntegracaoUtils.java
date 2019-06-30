package br.com.integracao.visitasocial.utils;

import java.util.HashMap;
import java.util.Map;

public class IntegracaoUtils
{
	public static final Integer UNIMED_PRODUCAO = 63;
	
	public static final Map<Integer, Integer> mapTipoSaida;
	
	static 
	{
        mapTipoSaida = new HashMap<>();
        mapTipoSaida.put(1, 12);
        mapTipoSaida.put(2, 11);
        mapTipoSaida.put(4, 14);
        mapTipoSaida.put(7, 81);
        mapTipoSaida.put(47, 18);
        mapTipoSaida.put(50, 87);
        mapTipoSaida.put(51, 88);
        mapTipoSaida.put(52, 89);
        mapTipoSaida.put(53, 90);
        mapTipoSaida.put(64, 82);
        mapTipoSaida.put(65, 83);
        mapTipoSaida.put(66, 84);
        
    }
	
	public static Integer getMapTipoSaida(Integer cdTipoSaida) 
	{
		return mapTipoSaida.get(cdTipoSaida);
	}
	
	public static boolean isPacienteIntercambio(Integer unimedCarteira)
	{
		return unimedCarteira == null
				|| unimedCarteira.intValue() <= 0
				|| unimedCarteira.intValue() != UNIMED_PRODUCAO;
	}
}