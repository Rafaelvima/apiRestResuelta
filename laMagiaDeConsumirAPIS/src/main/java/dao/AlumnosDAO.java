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
import model.Alumno;
import java.util.List;

/**
 *
 * @author Miguel Angel Diaz
 */
public class AlumnosDAO {

    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8080/ejercicioBaseDatos/rest/restalumnos");
    
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List getAllAlumnos() throws IOException {
        
        List<Alumno> lista;
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        /*Type type = new TypeToken<List<Alumno>>() {}.getType();
        List<Alumno> json = (List) requestGoogle.execute().parseAs(type);*/
        HttpResponse datos = requestGoogle.execute();
        
        lista = objectMapper.readValue(datos.parseAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));

        return lista;
    }

    public String insertAlumno(Alumno a) throws JsonProcessingException, IOException {
        
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        /*HttpResponse datos = requestGoogle.execute();
        String prueba = datos.parseAsString();
        
        a = objectMapper.readValue(prueba, Alumno.class);*/
        //a = (Alumno) datos.parseAsString();
        
        return requestGoogle.execute().parseAsString();
    }

    public String updateUser(Alumno a) throws JsonProcessingException, IOException {
        
        url.set("alumno",objectMapper.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        //a = objectMapper.readValue(requestGoogle.execute().parseAsString(), Alumno.class);
        
        return requestGoogle.execute().parseAsString();
    }

    public String delUser(Alumno a) throws JsonProcessingException, IOException {
        
        url.set("alumno",objectMapper.writeValueAsString(a));
        url.set("accion", "borrar");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString();
    }
    
    public String delUser2(Alumno a) throws JsonProcessingException, IOException{
        
        url.set("alumno",objectMapper.writeValueAsString(a));
        url.set("accion", "borrar2");
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        
        return requestGoogle.execute().parseAsString();   
    }

}
