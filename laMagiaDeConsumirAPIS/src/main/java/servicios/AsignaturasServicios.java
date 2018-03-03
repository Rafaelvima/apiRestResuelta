/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AsignaturasDAO;
import java.io.IOException;
import model.Asignatura;
import java.util.List;

/**
 *
 * @author Miguel Angel Diaz
 */
public class AsignaturasServicios {

    public List<Asignatura> getAllAsignaturas() throws IOException {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.getAllAsignaturas();
    }

    public String addAsignatura(Asignatura a) throws IOException {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.addAsignatura(a);
    }

    public String updateAsignatura(Asignatura a) throws IOException {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.updateAsignatura(a);
    }

    public String delAsignatura(Asignatura a) throws IOException {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.delAsignatura(a);
    }
    
    public String delAsignatura2(Asignatura a) throws IOException{
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.delAsignatura2(a);
    }
}
