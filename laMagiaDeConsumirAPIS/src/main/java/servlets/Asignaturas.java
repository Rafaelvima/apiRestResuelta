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
import model.Asignatura;
import servicios.AsignaturasServicios;

/**
 *
 * @author Miguel Angel Diaz
 */
@WebServlet(name = "Asignaturas", urlPatterns = {"/asignaturas"})
public class Asignaturas extends HttpServlet {

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

        AsignaturasServicios as = new AsignaturasServicios();
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                Asignatura a = new Asignatura();
                a.setNombre(request.getParameter("nombre"));
                a.setCiclo(request.getParameter("ciclo"));
                a.setCurso(request.getParameter("curso"));

                switch (accion) {
                    case "actualizar":
                        a.setId(Long.parseLong(request.getParameter("idasignatura")));
                        response.getWriter().write(as.updateAsignatura(a));

                        break;

                    case "insertar":
                        response.getWriter().write(as.addAsignatura(a));
                        
                        break;

                    case "borrar":
                        a.setId(Long.parseLong(request.getParameter("idasignatura")));
                        response.getWriter().write(as.delAsignatura(a));
                        
                        break;
                        
                    case "borrar2":
                        a.setId(Long.parseLong(request.getParameter("idasignatura")));
                        response.getWriter().write(as.delAsignatura2(a));
                        
                        break;
                }
            } else {
                request.setAttribute("asignaturas", as.getAllAsignaturas());
                request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                response.getWriter().write("401");
                request.setAttribute("error", "Se ha superado la cuota establecida");

            } else if (ex.getMessage().contains("503")) {
                response.getWriter().write("503");
                
            } else {
                request.setAttribute("error", "Ha ocurrido un error");
            }

            if (accion == null) {
                request.getRequestDispatcher("/pintarListaAsignaturas.jsp").forward(request, response);
            }
        }
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
