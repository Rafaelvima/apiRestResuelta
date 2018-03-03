/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import java.io.IOException;
import java.util.List;
import model.Alumno;

/**
 *
 * @author Miguel Angel Diaz
 */
public class AlumnosServicios {

    public List getAllAlumnos() throws IOException {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.getAllAlumnos();
    }

    public String addAlumno(Alumno alumnoNuevo) throws IOException {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.insertAlumno(alumnoNuevo);
    }

    public String updateAlumno(Alumno alumnoNuevo) throws IOException {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.updateUser(alumnoNuevo);
    }
    
    public String delAlumno(Alumno alumnoNuevo) throws IOException{
        AlumnosDAO dao = new AlumnosDAO();
        return dao.delUser(alumnoNuevo);
    }
    
    public String delAlumno2(Alumno alumnoNuevo) throws IOException{
        AlumnosDAO dao = new AlumnosDAO();
        return dao.delUser2(alumnoNuevo);
    }
}
