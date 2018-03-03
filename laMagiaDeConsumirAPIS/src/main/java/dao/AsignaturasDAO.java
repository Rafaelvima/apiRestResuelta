/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import config.Configuration;
import java.io.IOException;
import java.util.List;
import model.Asignatura;

/**
 *
 * @author Miguel Angel Diaz
 */
public class AsignaturasDAO {

    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8080/ejercicioBaseDatos/rest/restasignaturas");
    
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Asignatura> getAllAsignaturas() throws IOException {
        List<Asignatura> lista;
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        HttpResponse datos = requestGoogle.execute();
        
        lista = objectMapper.readValue(datos.parseAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Asignatura.class));

        return lista;
    }

    public String addAsignatura(Asignatura a) throws JsonProcessingException, IOException {
        
        url.set("asignatura",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString();
    }

    public String updateAsignatura(Asignatura a) throws JsonProcessingException, IOException {
        
        url.set("asignatura",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString();
    }

    public String delAsignatura(Asignatura a) throws JsonProcessingException, IOException {
        
        url.set("asignatura",objectMapper.writeValueAsString(a));
        url.set("accion", "borrar");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString();
    }
    
    public String delAsignatura2(Asignatura a) throws JsonProcessingException, IOException {
        
        url.set("asignatura",objectMapper.writeValueAsString(a));
        url.set("accion", "borrar2");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString(); 
    }
}
