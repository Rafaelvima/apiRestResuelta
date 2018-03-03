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
import com.google.gson.reflect.TypeToken;
import config.Configuration;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import model.Nota;

public class NotasDAO {
    
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8080/ejercicioBaseDatos/rest/restnotas");
    
    ObjectMapper objectMapper = new ObjectMapper();

    public int guardarNota(Nota n) throws JsonProcessingException, IOException {
        url.set("nota",objectMapper.writeValueAsString(n));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), n));
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        HttpResponse response = requestGoogle.execute();
        return response.getStatusCode();
    }

    public int delNota(Nota n) throws JsonProcessingException, IOException {
        url.set("nota",objectMapper.writeValueAsString(n));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        HttpResponse response = requestGoogle.execute();
        return response.getStatusCode();
    }

    public String getNota(Nota n) throws JsonProcessingException, IOException {
        url.set("nota",objectMapper.writeValueAsString(n));
        url.set("accion","cargarNota");
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        return requestGoogle.execute().parseAsString();
    }
    
    public List<List> getListas() throws IOException{
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", Configuration.getInstance().getApiKey());
        Type type = new TypeToken<List<List>>() {}.getType();
        List<List> lista = (List) requestGoogle.execute().parseAs(type);
        return lista;
    }
}
