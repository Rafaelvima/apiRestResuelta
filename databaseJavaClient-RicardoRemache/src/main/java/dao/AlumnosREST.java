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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.GenericResponse;
import utils.Api;
import utils.Constantes;

/**
 *
 * @author daw
 */
public class AlumnosREST {

    private static AlumnosREST instancia;

    public static AlumnosREST getInstance() {
        if (instancia == null) {
            instancia = new AlumnosREST();
        }
        return instancia;
    }

    public List<Alumno> getAlumnos() {
        List<Alumno> alumnos = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildGetRequest(url).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();
            ObjectMapper mapper = new ObjectMapper();
            alumnos = mapper.readValue(response.getContent(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));

        } catch (HttpResponseException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    public Alumno updateAlumno(Alumno alumno) {
        Alumno alumnoResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();
            GenericData data = new GenericData();
            data.put(Constantes.ALUMNO, mapper.writeValueAsString(alumno));

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPostRequest(url, new UrlEncodedContent(data))
                    .setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            alumnoResponse = mapper.readValue(response.getContent(), Alumno.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnoResponse;
    }

    public Alumno addAlumno(Alumno alumno) {
        Alumno alumnoResponse = null;
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);
            ObjectMapper mapper = new ObjectMapper();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd").create();
            String json = gson.toJson(alumno);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildPutRequest(url.set(Constantes.ALUMNO, json),
                    new EmptyContent()).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();

            alumnoResponse = mapper.readValue(response.getContent(), Alumno.class);

        } catch (HttpResponseException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnoResponse;
    }

    public GenericResponse deleteAlumno(Alumno alumno, boolean force) {
        GenericResponse alumnoResponse = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();
        try {
            GenericUrl url = new GenericUrl(Api.END_POINT_ALUMNOS);

            url.set(Constantes.DELETE_FORCE, force);

            String json = gson.toJson(alumno);

            RestApi instanceRest = RestApi.getInstance();

            HttpRequest requestGoogle = instanceRest.crearServicio().buildDeleteRequest(url.set(Constantes.ALUMNO, json)).setHeaders(instanceRest.getHeaderApikey());
            HttpResponse response = requestGoogle.execute();
            
            ObjectMapper mapper = new ObjectMapper();
            alumnoResponse = mapper.readValue(response.getContent(), GenericResponse.class);

        } catch (HttpResponseException ex) {
            alumnoResponse = gson.fromJson(ex.getContent(), GenericResponse.class);
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlumnosREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnoResponse;
    }
//leer response
//            Scanner s = new Scanner(response.getContent()).useDelimiter("\\A");
//            String result = s.hasNext() ? s.next() : "";
}//fin clase
