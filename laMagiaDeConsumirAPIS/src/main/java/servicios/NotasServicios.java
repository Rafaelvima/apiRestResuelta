/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import java.io.IOException;
import java.util.List;
import model.Nota;

/**
 *
 * @author Miguel Angel Diaz
 */
public class NotasServicios {
    public int guardarNota(Nota n) throws IOException{
        NotasDAO dao = new NotasDAO();
        return dao.guardarNota(n);
    }
    
    public String getNota(Nota n) throws IOException{
        NotasDAO dao = new NotasDAO();
        return dao.getNota(n);
    }
    
    public int delNota(Nota n) throws IOException{
        NotasDAO dao = new NotasDAO();
        return dao.delNota(n);
    }
    
    public List<List> getListas() throws IOException{
        NotasDAO dao = new NotasDAO();
        return dao.getListas();
    }
}
