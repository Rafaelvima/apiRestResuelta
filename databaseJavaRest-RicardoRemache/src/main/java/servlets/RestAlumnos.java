/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.AlumnosServicios;
import utils.Constantes;
import model.Alumno;
import model.GenericResponse;
import org.apache.http.HttpStatus;
import utils.ConstantesError;
import static utils.UrlsPaths.ALUMNOS;

/**
 *
 * @author daw
 */
@WebServlet(name = "RestAlumnos", urlPatterns = {ALUMNOS})
public class RestAlumnos extends HttpServlet {

    private AlumnosServicios servicios;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alumno alumno = (Alumno) req.getAttribute(Constantes.ALUMNO);
        String deleteForce = req.getParameter(Constantes.DELETE_FORCE);

        int rowsAffected = -1;
        if (alumno != null && alumno.getId() > 0) {
            if (deleteForce != null && !deleteForce.isEmpty() && Boolean.valueOf(deleteForce)) {
                try {
                    rowsAffected = (servicios.deleteAlumnoForce((int) alumno.getId())) ? 1 : 0;

                } catch (SQLException ex) {
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryAlumnoDeletedFailedAgain));
                    Logger.getLogger(RestAlumnos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                rowsAffected = servicios.deleteAlumnoJDBC(alumno.getId());

                if (rowsAffected == ConstantesError.CodeErrorClaveForanea) {
                    resp.setStatus(HttpStatus.SC_CONFLICT);
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_CONFLICT, Constantes.messageQueryAlumnoDeletedFail));

                } else if (rowsAffected == 0) {
                    resp.setStatus(HttpStatus.SC_BAD_REQUEST);
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryAlumnoDeleteMissing));

                }

            }
        }
        if (rowsAffected > 0) {
            req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_ACCEPTED, Constantes.messageQueryAlumnoDeleted));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Alumno alumno = (Alumno) req.getAttribute(Constantes.ALUMNO);
        if (alumno != null) {
            alumno = servicios.insertAlumnoJDBC(alumno);
            if (alumno == null) {
                resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute(Constantes.JSON,
                        new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryAlumnoInsertedFail));
            } else {
                resp.setStatus(HttpStatus.SC_CREATED);
                req.setAttribute(Constantes.JSON, alumno);
            }

        }

    }

    @Override
    public void init() throws ServletException {
        servicios = new AlumnosServicios();
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

        request.setAttribute(Constantes.JSON, servicios.getAllAlumnos());

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
        Alumno alumno = (Alumno) request.getAttribute(Constantes.ALUMNO);
        if (alumno != null) {
            alumno = servicios.updateAlumnoJDBC(alumno);
            if (alumno != null) {
                request.setAttribute(Constantes.JSON, alumno);
            } else {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                request.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryAlumnoUpdatedFail));
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
