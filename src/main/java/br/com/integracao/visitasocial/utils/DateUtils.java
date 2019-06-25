package br.com.integracao.visitasocial.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.constants.BaseConstants;
import br.com.integracao.visitasocial.exceptions.AppException;

public class DateUtils
{
	static Logger logger = Logger.getLogger(DateUtils.class);
	
	public static int calculaIdade(java.util.Date dataNasc) 
	{
	    Calendar dataNascimento = Calendar.getInstance();  
	    dataNascimento.setTime(dataNasc);
	    Calendar hoje = Calendar.getInstance();

	    int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR); 

	    if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) 
	    {
	      idade--;
	    } 
	    else 
	    { 
	        if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
	        		&& hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) 
	        {
	            idade--; 
	        }
	    }
	    return idade;
	}
	
	public static Date stringToDate(String dateStr) 
	{
		try
		{
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} 
		catch (ParseException ex)
		{
			logger.error(BaseConstants.PARSER_ERROR);
			throw new AppException(BaseConstants.PARSER_ERROR);
		}
	}
	
	public static String removerHoraByDateStr(String dateStr)
	{
		try
		{
			SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
		
			return formato2.format(formato1.parse(dateStr));
		}
		catch (ParseException ex)
		{
			logger.error(BaseConstants.PARSER_ERROR);
			throw new AppException(BaseConstants.PARSER_ERROR);
		}
	}
	
	public static String removerHoraByDate(Date date)
	{
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(date);
	}
}