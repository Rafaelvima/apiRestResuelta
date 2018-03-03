/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Miguel Angel Diaz
 */
public class Asignatura {
    
    private Long id;
    private String nombre;
    private String ciclo;
    private String curso;
    
    public Asignatura(){
    }
    
    public void setId(Long id){
        this.id=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setCiclo(String ciclo){
        this.ciclo=ciclo;
    }
    public void setCurso(String curso){
        this.curso=curso;
    }
    
    public Long getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCiclo(){
        return ciclo;
    }
    public String getCurso(){
        return curso;
    }
}
