package br.com.integracao.visitasocial.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor 
{
    private final String username;
    
    private final String password;

    public BasicAuthInterceptor(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException 
    {
        final String auth = username + ":" + password;
        final String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")));
        
        request.getHeaders().add("Authorization", authHeader);
        
        return execution.execute(request, body);
    }
}