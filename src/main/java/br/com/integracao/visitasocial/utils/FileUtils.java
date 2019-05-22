package br.com.integracao.visitasocial.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import br.com.integracao.visitasocial.constants.BaseConstants;
import br.com.integracao.visitasocial.exceptions.AppException;

public class FileUtils
{
	static Logger logger = Logger.getLogger(FileUtils.class);
	
	public static final String PATH_LOG = "C:\\temp\\visitasocial\\logging.log";
	
	public static void apagarConteudo()
	{
		FileReader fileReader = null;
		FileWriter fileWriter = null;
		BufferedReader leitor = null; 
		String line = "";
		
		try 
		{
			fileReader = new FileReader(new File(PATH_LOG));
			fileWriter = new FileWriter(new File(PATH_LOG));
			leitor = new BufferedReader(fileReader);
			
            while ((line = leitor.readLine()) != null) 
            {  
                fileWriter.write(line + "\r\n");
            }  
		} 
		catch (IOException e) 
		{
			logger.error(BaseConstants.ERASE_FILE_ERROR);
			throw new AppException(BaseConstants.ERASE_FILE_ERROR);
	    } 
		finally  
		{  
	        try 
	        {
	            fileWriter.close();
	            fileReader.close();  
	        } 
	        catch (IOException e) 
	        {
	        	logger.error(BaseConstants.CLOSE_CONNECTION_FILE_ERROR);
				throw new AppException(BaseConstants.CLOSE_CONNECTION_FILE_ERROR);
	        }  
	    }  
	}
}