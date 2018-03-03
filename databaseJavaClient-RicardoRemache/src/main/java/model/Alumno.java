/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.sql.Date;

/**
 *
 * @author oscar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "nombre",
    "fecha_nacimiento",
    "mayor_edad"
})
public class Alumno {

    @JsonProperty("id")
    public long id;
    @JsonProperty("nombre")
    public String nombre;
    @JsonProperty("fecha_nacimiento")
    public Date fecha_nacimiento;
    @JsonProperty("mayor_edad")
    public Boolean mayor_edad;

    public Alumno() {
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("fecha_nacimiento")
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    @JsonProperty("fecha_nacimiento")
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @JsonProperty("mayor_edad")
    public Boolean getMayor_edad() {
        return mayor_edad;
    }

    @JsonProperty("mayor_edad")
    public void setMayor_edad(Boolean mayor_edad) {
        this.mayor_edad = mayor_edad;
    }

}
