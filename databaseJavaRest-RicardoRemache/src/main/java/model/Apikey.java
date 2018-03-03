/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Gato
 */
public class Apikey {
    public long id;
    public String apikey;
    public long num_peticiones;
    public Date fecha_ultima_peticion;

    public Apikey(long id, String apikey, long num_peticiones, Date fecha_ultima_peticion) {
        this.id = id;
        this.apikey = apikey;
        this.num_peticiones = num_peticiones;
        this.fecha_ultima_peticion = fecha_ultima_peticion;
    }

    public Apikey() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public long getNum_peticiones() {
        return num_peticiones;
    }

    public void setNum_peticiones(long num_peticiones) {
        this.num_peticiones = num_peticiones;
    }

    public Date getFecha_ultima_peticion() {
        return fecha_ultima_peticion;
    }

    public void setFecha_ultima_peticion(Date fecha_ultima_peticion) {
        this.fecha_ultima_peticion = fecha_ultima_peticion;
    }
    
    
    
    
}
