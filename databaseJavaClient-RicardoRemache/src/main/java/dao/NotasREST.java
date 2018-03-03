/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.GenericData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nota;
import model.GenericResponse;
import utils.Api;
import utils.Constantes;

/**
 *
 * @author daw
 */
public class NotasREST {

    private static NotasREST instancia;

    public static NotasREST getInstance() {
        if (instancia == null) {
            instancia = new NotasREST();
        }
        return instancia;
    }

    public Nota getNota(Nota nota) {
        Nota notas = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_NOTAS);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd").create();
            String json = gson.toJson(nota);
            url.set(Constantes.NOTA, json);
            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildGetRequest(url).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();
            ObjectMapper mapper = new ObjectMapper();
            notas = mapper.readValue(response.getContent(), Nota.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notas;
    }

    public Nota updateNota(Nota nota) {
        Nota notaResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_NOTAS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put(Constantes.NOTA, mapper.writeValueAsString(nota));

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPostRequest(url, new UrlEncodedContent(data))
                    .setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            notaResponse = mapper.readValue(response.getContent(), Nota.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notaResponse;
    }

    public Nota addNota(Nota nota) {
        Nota notaResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_NOTAS);
            ObjectMapper mapper = new ObjectMapper();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd").create();
            String json = gson.toJson(nota);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPutRequest(url.set(Constantes.NOTA, json),
                    new EmptyContent()).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            notaResponse = mapper.readValue(response.getContent(), Nota.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notaResponse;
    }

    public GenericResponse deleteNota(Nota nota) {
        GenericResponse notaResponse = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_NOTAS);

            String json = gson.toJson(nota);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildDeleteRequest(url.set(Constantes.NOTA, json))
                    .setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            ObjectMapper mapper = new ObjectMapper();
            notaResponse = mapper.readValue(response.getContent(), GenericResponse.class);

        } catch (HttpResponseException ex) {
            notaResponse = gson.fromJson(ex.getContent(), GenericResponse.class);
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notaResponse;
    }
//leer response
//            Scanner s = new Scanner(response.getContent()).useDelimiter("\\A");
//            String result = s.hasNext() ? s.next() : "";
}//fin clase
