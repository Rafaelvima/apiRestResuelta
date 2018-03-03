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
import model.Asignatura;
import model.GenericResponse;
import utils.Api;
import utils.Constantes;

/**
 *
 * @author daw
 */
public class AsignaturasREST {

    private static AsignaturasREST instancia;

    public static AsignaturasREST getInstance() {
        if (instancia == null) {
            instancia = new AsignaturasREST();
        }
        return instancia;
    }

    public Asignatura[] getAsignaturas() {
        Asignatura[] asignaturas = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ASIGNATURAS);
            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildGetRequest(url).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();
            ObjectMapper mapper = new ObjectMapper();
            asignaturas = mapper.readValue(response.getContent(), Asignatura[].class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturas;
    }

    public Asignatura updateAsignatura(Asignatura asignatura) {
        Asignatura asignaturaResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ASIGNATURAS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put(Constantes.ASIGNATURA, mapper.writeValueAsString(asignatura));

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPostRequest(url, new UrlEncodedContent(data))
                    .setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            asignaturaResponse = mapper.readValue(response.getContent(), Asignatura.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturaResponse;
    }

    public Asignatura addAsignatura(Asignatura asignatura) {
        Asignatura asignaturaResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ASIGNATURAS);
            ObjectMapper mapper = new ObjectMapper();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd").create();
            String json = gson.toJson(asignatura);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPutRequest(url.set(Constantes.ASIGNATURA, json),
                    new EmptyContent()).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            asignaturaResponse = mapper.readValue(response.getContent(), Asignatura.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturaResponse;
    }

    public GenericResponse deleteAsignatura(Asignatura asignatura, boolean force) {
        GenericResponse asignaturaResponse = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ASIGNATURAS);

            url.set(Constantes.DELETE_FORCE, force);

            String json = gson.toJson(asignatura);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildDeleteRequest(url.set(Constantes.ASIGNATURA, json))
                    .setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            ObjectMapper mapper = new ObjectMapper();
            asignaturaResponse = mapper.readValue(response.getContent(), GenericResponse.class);

        } catch (HttpResponseException ex) {
            asignaturaResponse = gson.fromJson(ex.getContent(), GenericResponse.class);
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsignaturasREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturaResponse;
    }
//leer response
//            Scanner s = new Scanner(response.getContent()).useDelimiter("\\A");
//            String result = s.hasNext() ? s.next() : "";
}//fin clase
