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
import model.Nota;
import servicios.NotasServicios;

/**
 *
 * @author Miguel Angel Diaz
 */
@WebServlet(name = "Notas", urlPatterns = {"/notas"})
public class Notas extends HttpServlet {

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

        NotasServicios ns = new NotasServicios();
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                String idAlu = request.getParameter("idAlumno");
                String idAsig = request.getParameter("idAsignatura");
                String nota = request.getParameter("nota");

                Nota n = new Nota();
                n.setIdAlumno(Long.parseLong(idAlu));
                n.setIdAsignatura(Long.parseLong(idAsig));

                switch (accion) {
                    case "guardar":
                        n.setNota(Integer.parseInt(nota));
                        if(ns.guardarNota(n) == 200){
                            response.getWriter().write("Nota guardada");
                        }

                        break;

                    case "borrar":
                        if(ns.delNota(n) == 200){
                            response.getWriter().write("Nota borrada");
                        }
                        break;

                    case "cargar":
                        String numNota = ns.getNota(n);
                        if(!numNota.equals("")){
                            response.getWriter().write(numNota);
                        }else{
                            response.setStatus(204);
                        }

                        break;
                }
            } else {
                request.setAttribute("lista", ns.getListas());
                request.getRequestDispatcher("/pintarListaNotas.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            if (ex.getMessage().contains("401")) {
                response.getWriter().write("401");
                request.setAttribute("error", "Se ha superado la cuota establecida");

            } else {
                request.setAttribute("error", "Ha ocurrido un error");
            }

            if (accion == null) {
                request.getRequestDispatcher("/pintarListaNotas.jsp").forward(request, response);
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
