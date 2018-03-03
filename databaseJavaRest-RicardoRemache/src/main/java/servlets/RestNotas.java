/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GenericResponse;
import model.Nota;
import servicios.NotasServicios;
import utils.Constantes;
import org.apache.http.HttpStatus;
import static utils.UrlsPaths.NOTAS;

/**
 *
 * @author daw
 */
@WebServlet(name = "RestNotas", urlPatterns = {NOTAS})
public class RestNotas extends HttpServlet {

    private NotasServicios servicios;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Nota nota = (Nota) req.getAttribute(Constantes.NOTA);

        if (nota != null) {

            int rowsAffected = (servicios.deleteNota((int) nota.getId_alumno())) ? 1 : 0;
            if (rowsAffected > 0) {
                req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_OK, Constantes.messageQueryNotaDeleted));
            } else {
                req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryNotaMissing));
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Nota nota = (Nota) req.getAttribute(Constantes.NOTA);
        if (nota != null) {
            nota = servicios.insertNota(nota);
            if (nota == null) {
                resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute(Constantes.JSON,
                        new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryNotaUpdatedFail));
            } else {
                resp.setStatus(HttpStatus.SC_CREATED);
                req.setAttribute(Constantes.JSON,nota);
            }

        }
    }

    @Override
    public void init() throws ServletException {
        servicios = new NotasServicios();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Nota nota = (Nota) request.getAttribute(Constantes.NOTA);
        if (nota != null) {
            nota = servicios.getNota(nota);
            if (nota != null) {
                request.setAttribute(Constantes.JSON, nota);
                response.setStatus(HttpStatus.SC_OK);
            } else {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                request.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_NOT_FOUND, Constantes.messageQueryNotaMissing));
            }

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Nota nota = (Nota) request.getAttribute(Constantes.NOTA);
        if (nota != null) {

            if (servicios.getNota(nota) != null) {

                if (servicios.updateNota(nota)) {
                    request.setAttribute(Constantes.JSON, nota);
                } else {
                    response.setStatus(HttpStatus.SC_BAD_REQUEST);
                    request.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryNotaUpdatedFail));
                }

            } else {
                nota = servicios.insertNota(nota);
                if (nota == null) {
                    response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    request.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryNotaUpdatedFail));
                }else{
                    request.setAttribute(Constantes.JSON, nota);
                }
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
