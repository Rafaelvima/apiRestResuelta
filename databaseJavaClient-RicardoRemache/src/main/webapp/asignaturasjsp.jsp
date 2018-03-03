<%-- 
    Document   : asignaturasjsp
    Created on : 07-nov-2017, 10:04:12
    Author     : daw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="cons" class="utils.UrlsPaths" scope="session"/>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css">
        <title>Asignaturas List</title>
        <style>
            body {text-align: center}
            table {
                margin-left: auto;
                margin-right: auto;
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
        <script>
            function cargarAsignatura(id, nombre, curso, ciclo) {
                document.getElementById("id").value = id;
                document.getElementById("nombre").value = nombre;
                document.getElementById("curso").value = curso;
                document.getElementById("ciclo").value = ciclo;

            }

        </script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    </head>
    <body>
        <div class="container">
            <a href="<c:out value="${cons.getAlumnosRelative()}"/>">alumnos</a>
            <a href="<c:out value="${cons.getAsignaturasRelative()}"/>">asignaturas</a>
            <a href="<c:out value="${cons.getNotasRelative()}"/>">notas</a>
            
        </div>
        <c:if test="${not empty asignaturaResult}">
            <form action="asignaturas">
                <h3><c:out value="${resultado}"/></h3>
                <input type="hidden" name="id" value="${asignaturaResult.id}" ><br>           
                <button name="action" name="action" value="delete_force" type="submit">Borrar Completamente</button>
                <button name="action" name="action" value="CANCEL" type="submit">Cancelar</button>

            </form>

        </c:if>

        <c:if test="${empty asignaturaResult}">
            <c:out value="${resultado}"/>


        </c:if>
        <br>

        <table class="table">
            <tr>
                <th></th>
                <th>Nombre</th>
                <th>Curso</th>                
                <th>Ciclo</th>
            </tr>
            <c:forEach var="asignatura" items="${asignaturasList}">
                <tr>
                    <td>
                        <c:set var="nombre" value="${asignatura.nombre}"/>

                        <button id="cargarAsignatura" onClick="
                cargarAsignatura(${asignatura.id},
                        '${fn:escapeXml(fn:replace(nombre,"'","\\'"))}'
                        , '${asignatura.curso}',
                        '${asignatura.ciclo}')">Cargar</button>
                    </td>
                    <td contenteditable="true">
                        <c:out value="${asignatura.nombre}"/>
                    </td>
                    <td contenteditable="true">
                        <c:out value="${asignatura.curso}"/>
                    </td>
                    <td contenteditable="true">
                        <c:out value="${asignatura.ciclo}"/>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <form action="asignaturas" >
            <input type="hidden" name="id" id="id" ><br>
            Nombre:
            <input type="text" name="nombre" id="nombre" ><br>
            Curso:
            <input type="text" name="curso" id="curso" placeholder=""><br>
            Ciclo: 
            <input type="text" name="ciclo" id="ciclo" placeholder=""><br>

            <br>
            <input type="submit" name="action" value="INSERT">
            <input type="submit" name="action" value="UPDATE">
            <input type="submit" name="action" value="DELETE">
        </form>  


        <p>Nº Asignaturas: 
            <c:out value="${fn:length(asignaturasList)}"/>
        </p>

        <script>


        </script>
    </body>
</html>
