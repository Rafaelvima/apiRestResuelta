/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AlumnosREST;
import dao.AsignaturasREST;
import dao.NotasREST;
import java.io.IOException;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nota;
import servicios.NotasServicios;
import utils.Constantes;
import utils.UrlsPaths;

/**
 *
 * @author daw
 */
@WebServlet(name = "NotasServlet", urlPatterns = {UrlsPaths.NOTAS})
public class NotasServlet extends HttpServlet {

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
        NotasServicios serviciosNotas = new NotasServicios();

        Nota nota = null;
        Nota claves = null;

        String action = request.getParameter(Constantes.actionJSP);
        Map<String, String[]> parametros = null;
        NotasREST instanceRest = NotasREST.getInstance();
        if (action != null && !action.isEmpty()) {

            parametros = request.getParameterMap();
            claves = serviciosNotas.tratarParametros(parametros);

            switch (action) {
                case Constantes.VIEW://buscamos la nota 

                    nota = instanceRest.getNota(claves);

                    if (nota != null) {
                        request.setAttribute(Constantes.notaResult, nota);
                        claves = null;
                    } else {
                        request.setAttribute(Constantes.resultadoQuery, Constantes.messageQueryNotaMissing);
                        claves.setNota(-1);//no tenemos nota la base de datos
                    }

                    break;

                case Constantes.UPDATE:
                    //actualizar nota
                    //1* consultar en notas
                    //si -> update
                    //no -> insert

                    if (instanceRest.getNota(claves) != null) {
                        nota = instanceRest.updateNota(claves);
                    } else {
                        nota = instanceRest.addNota(claves);
                    }
                    request.setAttribute(Constantes.notaMessage, (nota != null) ? Constantes.messageQueryNotaUpdated : Constantes.messageQueryNotaUpdatedFail);

                    break;

            }

        }

        if (claves != null) {
            request.setAttribute(Constantes.notaResult, claves);
        }
        request.setAttribute(Constantes.asignaturasList, AsignaturasREST.getInstance().getAsignaturas());//envia la lista al jsp
        request.setAttribute(Constantes.alumnosList, AlumnosREST.getInstance().getAlumnos());

        request.getRequestDispatcher("/" + Constantes.notasJSP).forward(request, response);

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
