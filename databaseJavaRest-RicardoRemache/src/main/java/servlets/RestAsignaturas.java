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
import model.Asignatura;
import model.GenericResponse;
import org.apache.http.HttpStatus;
import servicios.AsignaturasServicios;
import utils.Constantes;
import utils.ConstantesError;
import static utils.UrlsPaths.ASIGNATURAS;

/**
 *
 * @author daw
 */
@WebServlet(name = "RestAsignaturas", urlPatterns = {ASIGNATURAS})
public class RestAsignaturas extends HttpServlet {

    private AsignaturasServicios servicios;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Asignatura asignatura = (Asignatura) req.getAttribute(Constantes.ASIGNATURA);
        String deleteForce = req.getParameter(Constantes.DELETE_FORCE);

        int rowsAffected = -1;
        if (asignatura != null && asignatura.getId() > 0) {
            if (deleteForce != null && !deleteForce.isEmpty() && Boolean.valueOf(deleteForce)) {
                try {
                    rowsAffected = (servicios.deleteAsignaturaForce((int) asignatura.getId())) ? 1 : 0;

                } catch (SQLException ex) {
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryAsignaturaDeletedFailedAgain));
                    Logger.getLogger(RestAlumnos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                rowsAffected = servicios.deleteAsignaturadbUtils(asignatura.getId());

                if (rowsAffected == ConstantesError.CodeErrorClaveForanea) {
                    resp.setStatus(HttpStatus.SC_CONFLICT);
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_CONFLICT, Constantes.messageQueryAsignaturaDeletedFail));

                } else if (rowsAffected == 0) {
                    resp.setStatus(HttpStatus.SC_BAD_REQUEST);
                    req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_BAD_REQUEST, Constantes.messageQueryAsignaturaDeleteMissing));

                }

            }
        }
        if (rowsAffected > 0) {
            req.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_ACCEPTED, Constantes.messageQueryAsignaturaDeleted));
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Asignatura asignatura = (Asignatura) req.getAttribute(Constantes.ASIGNATURA);
        if (asignatura != null) {
            asignatura = servicios.insertAsignaturadbUtils(asignatura);
            if (asignatura == null) {
                resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute(Constantes.JSON,
                        new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryAsignaturaInsertFailed));
            } else {
                resp.setStatus(HttpStatus.SC_CREATED);
                req.setAttribute(Constantes.JSON, asignatura);
            }

        }

    }

    @Override
    public void init() throws ServletException {
        servicios = new AsignaturasServicios();
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

        request.setAttribute(Constantes.JSON, servicios.getAllAsignaturasdbUtils());

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
        Asignatura asignatura = (Asignatura) request.getAttribute(Constantes.ASIGNATURA);
        if (asignatura != null) {
            asignatura = servicios.updateAsignaturadbUtils(asignatura);
            if (asignatura != null) {
                request.setAttribute(Constantes.JSON, asignatura);
            } else {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                request.setAttribute(Constantes.JSON, new GenericResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constantes.messageQueryAsignaturaUpdateFailed));
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
