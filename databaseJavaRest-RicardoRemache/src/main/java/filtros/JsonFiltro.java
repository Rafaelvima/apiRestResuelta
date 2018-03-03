/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import model.Alumno;
import model.Asignatura;
import model.GenericResponse;
import model.Nota;
import org.apache.http.HttpStatus;
import servicios.AlumnosServicios;
import utils.Constantes;
import static utils.UrlsPaths.JSON_FILTRO;

/**
 *
 * @author daw
 */
@WebFilter(filterName = "JsonFiltro", urlPatterns = {JSON_FILTRO})
public class JsonFiltro implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public JsonFiltro() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("JsonFiltro:DoBeforeProcessing");
        }
        ObjectMapper mapper = new ObjectMapper();

        String alumno = request.getParameter(Constantes.ALUMNO);
        String asignatura = request.getParameter(Constantes.ASIGNATURA);
        String nota = request.getParameter(Constantes.NOTA);

        AlumnosServicios servicios = new AlumnosServicios();
        int codeResponse = HttpStatus.SC_ACCEPTED;
        
        String method = ((HttpServletRequest) request).getMethod();

        if (alumno != null && !alumno.isEmpty()) {
            Alumno a = mapper.readValue(alumno, new TypeReference<Alumno>() {
            });

            if (method.equalsIgnoreCase(Constantes.PUT)) {

                if (servicios.comprobarCamposAlumno(a, false)) {
                    request.setAttribute(Constantes.ALUMNO, a);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            } else if (method.equalsIgnoreCase(Constantes.POST)) {
                if (servicios.comprobarCamposAlumno(a, true)) {
                    request.setAttribute(Constantes.ALUMNO, a);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            } else if (method.equalsIgnoreCase(Constantes.DELETE)) {
                if (a != null && a.getId() > 0) {
                    request.setAttribute(Constantes.ALUMNO, a);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            }

        } else if (asignatura != null && !asignatura.isEmpty()) {

            Asignatura asig = mapper.readValue(asignatura, new TypeReference<Asignatura>() {
            });

            if (method.equalsIgnoreCase(Constantes.PUT)) {

                if (servicios.comprobarCamposAsignatura(asig, false)) {
                    request.setAttribute(Constantes.ASIGNATURA, asig);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            } else if (method.equalsIgnoreCase(Constantes.POST)) {
                if (servicios.comprobarCamposAsignatura(asig, true)) {
                    request.setAttribute(Constantes.ASIGNATURA, asig);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            } else if (method.equalsIgnoreCase(Constantes.DELETE)) {
                if (asig != null && asig.getId() > 0) {
                    request.setAttribute(Constantes.ASIGNATURA, asig);
                } else {
                    codeResponse = HttpStatus.SC_BAD_REQUEST;
                }
            }

        } else if (nota != null && !nota.isEmpty()) {
            Nota n = mapper.readValue(nota, new TypeReference<Nota>() {
            });
            if (servicios.comprobarCamposNota(n, false)) {

                if (method.equalsIgnoreCase(Constantes.POST) || method.equalsIgnoreCase(Constantes.PUT)) {
                    if (servicios.comprobarCamposNota(n, true)) {
                        request.setAttribute(Constantes.NOTA, n);
                    } else {
                        codeResponse = HttpStatus.SC_BAD_REQUEST;
                    }
                } else {

                    request.setAttribute(Constantes.NOTA, n);

                }

            } else {
                codeResponse = HttpStatus.SC_BAD_REQUEST;
            }
        } else {
            codeResponse = HttpStatus.SC_BAD_REQUEST;
        }

        if (codeResponse != HttpStatus.SC_ACCEPTED) {
            request.setAttribute(Constantes.JSON, new GenericResponse(codeResponse, Constantes.faltanCampos));
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("JsonFiltro:DoAfterProcessing");
        }
        ObjectMapper mapper = new ObjectMapper();
        Object json = request.getAttribute(Constantes.JSON);
        if (json != null) {
            mapper.writeValue(response.getOutputStream(), json);
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("JsonFiltro:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("JsonFiltro:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("JsonFiltro()");
        }
        StringBuffer sb = new StringBuffer("JsonFiltro(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
