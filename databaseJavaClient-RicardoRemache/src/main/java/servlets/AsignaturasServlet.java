/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AsignaturasREST;
import java.io.IOException;
import java.util.Map;

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
import utils.UrlsPaths;

/**
 *
 * @author daw
 */
@WebServlet(name = "AsignaturasServlet", urlPatterns = {UrlsPaths.ASIGNATURAS})
public class AsignaturasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AsignaturasServicios servicios = new AsignaturasServicios();

        AsignaturasREST instanceREST = AsignaturasREST.getInstance();

        String action = request.getParameter(Constantes.actionJSP);
        String messageToUser = null;

        Map<String, String[]> parametros = request.getParameterMap();
        Asignatura asignatura = servicios.tratarParametros(parametros);
        if (action != null && !action.isEmpty()) {

            switch (action) {
                case Constantes.UPDATE:

                    Asignatura filas = instanceREST.updateAsignatura(asignatura);

                    messageToUser = (filas != null) ? Constantes.messageQueryAsignaturaUpdated : Constantes.messageQueryAsignaturaUpdateFailed;

                    break;
                case Constantes.INSERT:

                    messageToUser = (instanceREST.addAsignatura(asignatura) != null)
                            ? Constantes.messageQueryAsignaturaInserted : Constantes.messageQueryAsignaturaInsertFailed;

                    break;
                case Constantes.DELETE:
                    String key = request.getParameter(Constantes.ID.toLowerCase());
                    GenericResponse responseDel = null;

                    
                    if (key != null && !key.isEmpty()) {
                        responseDel = instanceREST.deleteAsignatura(asignatura, false);

                    }
                    if (responseDel != null && responseDel.getCode() == HttpStatus.SC_CONFLICT) {

                        request.setAttribute(Constantes.asignaturaResult, asignatura);
                        messageToUser = Constantes.messageQueryAsignaturaDeletedFail;

                    } else if (responseDel != null && responseDel.getCode() == HttpStatus.SC_ACCEPTED) {
                        messageToUser = Constantes.messageQueryAsignaturaDeleted;
                    }
                    break;
                case Constantes.DELETE_FORCE:

                    
                        GenericResponse borrado = instanceREST.deleteAsignatura(asignatura, true);
                        messageToUser = (borrado != null && borrado.getCode() == HttpStatus.SC_ACCEPTED) ? Constantes.messageQueryAsignaturaDeleted : Constantes.messageQueryAlumnoDeletedFailedAgain;

                    

                    //1º -> BORRAR NOTA 
                    //2º -> BORRAR ASIGNATURA
                    break;

            }
        }

        if (messageToUser != null) {
            request.setAttribute(Constantes.resultadoQuery, messageToUser);
        }

        request.setAttribute(Constantes.asignaturasList, instanceREST.getAsignaturas());//envia la lista al jsp
        request.getRequestDispatcher("/" + Constantes.asignaturasJSP).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
