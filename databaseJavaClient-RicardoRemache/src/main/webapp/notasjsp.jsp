<%-- 
    Document   : notasjsp
    Created on : 10-nov-2017, 11:12:45
    Author     : daw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="cons" class="utils.UrlsPaths" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
        <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css">
        <style>
            form {
                border: 1px solid black;
                display: inline-block;
                text-align: left;    
            }

            body {
                text-align: center; 
            }
            .container {
                margin: 0 auto;
                text-align: center;
                width: 100%;
            }
            .container a {
                padding-left: 20px;
                font-size: 1.5em;
            }

        </style>

    </head>
    <body>
        <div class="container">
            <a href="<c:out value="${cons.getAlumnosRelative()}"/>">alumnos</a>
            <a href="<c:out value="${cons.getAsignaturasRelative()}"/>">asignaturas</a>
            <a href="<c:out value="${cons.getNotasRelative()}"/>">notas</a>
            
        </div>
        <form action="notas" >
            <select name="id_asignatura">
                <c:forEach var="asignatura" items="${asignaturasList}">
                    <option value="${asignatura.id}"
                            <c:if test="${not empty notaResult.id_asignatura}">

                                ${notaResult.id_asignatura == asignatura.id ? "selected": ""}

                            </c:if>>${fn:escapeXml(fn:replace(asignatura.nombre,"'","\\'"))}</option>
                </c:forEach>

            </select> 
            <select name="id_alumno">
                <c:forEach var="alumno" items="${alumnosList}">
                    <option value="${alumno.id}"
                            <c:if test="${not empty notaResult.id_alumno}">

                                ${notaResult.id_alumno == alumno.id ? "selected": ""}

                            </c:if>
                            >${fn:escapeXml(fn:replace(alumno.nombre,"'","\\'"))}</option>
                </c:forEach>

            </select> 
            <c:if test="${not empty notaResult.nota}">
                <c:if test="${notaResult.nota != -1}">
                    <input type="text" name="nota" value="${notaResult.nota}">    
                </c:if>                
                <c:if test="${notaResult.nota == -1}">
                    <input type="text" name="nota" placeholder="${resultado}" >    
                </c:if> 
            </c:if>
            <!-- para borrar-->
            <c:if test="${empty notaResult.nota}">
                <input type="text" name="nota" placeholder="${resultado}">    
            </c:if>

            <input type="submit" name="action" value="VIEW">
            <input type="submit" name="action" value="UPDATE" onclick="return comprobarInputNota()" >
        </form>
        <c:if test="${not empty notaMessage}">
            <p>
                <c:out value="${notaMessage}"/>
            </p>
        </c:if>

        <script>
            function comprobarInputNota() {
                var formulario = document.getElementsByTagName("input")[0].value;
                if (formulario.length == 0) {

                    alert("No has introducido una nota!");
                    return false;

                }
            }
        </script>
    </body>
</html>
