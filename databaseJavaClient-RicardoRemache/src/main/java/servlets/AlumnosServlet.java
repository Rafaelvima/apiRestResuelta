/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AlumnosREST;
import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.GenericResponse;
import org.apache.http.HttpStatus;
import servicios.AlumnosServicios;

import utils.Constantes;

import utils.UrlsPaths;

/**
 *
 * @author daw
 */
@WebServlet(name = "AlumnosServlet", urlPatterns = {UrlsPaths.ALUMNOS})
public class AlumnosServlet extends HttpServlet {

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
        AlumnosServicios servicios = new AlumnosServicios();
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter(Constantes.actionJSP);
        Alumno alumno = null;
        String messageToUser = null;
        Map<String, String[]> parametros = request.getParameterMap();
        
        AlumnosREST instanceREST = AlumnosREST.getInstance();
        if (action != null && !action.isEmpty()) {

            switch (action) {
                case Constantes.UPDATE:

                    alumno = servicios.tratarParametros(parametros);
                    messageToUser = (instanceREST.updateAlumno(alumno) != null) ? Constantes.messageQueryAlumnoUpdated : Constantes.messageQueryAlumnoUpdatedFail;

                    break;
                case Constantes.INSERT:

                    alumno = servicios.tratarParametros(parametros);
                    messageToUser = (instanceREST.addAlumno(alumno) != null) ? Constantes.messageQueryAlumnoInserted : Constantes.messageQueryAlumnoInsertedFail;

                    break;
                case Constantes.DELETE:
                    alumno = servicios.tratarParametros(parametros);
                    String key = request.getParameter(Constantes.ID.toLowerCase());
                    GenericResponse responseDel = null;
                    
                    if (key != null && !key.isEmpty()) {
                        responseDel = instanceREST.deleteAlumno(alumno, false);
                    }
                    if (responseDel != null && responseDel.getCode() == HttpStatus.SC_CONFLICT) {

                        request.setAttribute(Constantes.alumnoResult, alumno);
                        messageToUser = Constantes.messageQueryAlumnoDeletedFail;

                    } else if (responseDel != null && responseDel.getCode() == HttpStatus.SC_ACCEPTED) {

                        messageToUser = Constantes.messageQueryAlumnoDeleted;
                    }
                    break;
                case Constantes.DELETE_FORCE:

                    alumno = servicios.tratarParametros(parametros);
                     {

                        GenericResponse borrado = instanceREST.deleteAlumno(alumno, true);

                        messageToUser = (borrado != null && borrado.getCode() == HttpStatus.SC_ACCEPTED) ? Constantes.messageQueryAlumnoDeleted : Constantes.messageQueryAlumnoDeletedFailedAgain;

                    }
                    //1º -> BORRAR NOTA
                    //2º -> BORRAR ALUMNO
                    break;

            }
        }
        if (messageToUser != null) {
            request.setAttribute(Constantes.resultadoQuery, messageToUser);
        }
        Alumno[] listaAlumnos = instanceREST.getAlumnos();
        request.setAttribute(Constantes.alumnosList, listaAlumnos);//envia la lista al jsp
        request.getRequestDispatcher("/" + Constantes.alumnosJSP).forward(request, response);
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
