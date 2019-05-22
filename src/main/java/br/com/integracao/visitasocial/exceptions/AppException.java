package br.com.integracao.visitasocial.exceptions;

public class AppException extends RuntimeException
{
	private static final long serialVersionUID = 1448386098336515968L;

	public AppException(String s) 
	{
		super(s);
	}
	
	public AppException(String s, Throwable throwable) 
	{
		super(s, throwable);
	}
}