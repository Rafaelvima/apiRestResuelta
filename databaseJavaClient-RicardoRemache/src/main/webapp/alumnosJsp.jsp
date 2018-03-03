<%-- 
    Document   : resultadosJsp
    Created on : 02-nov-2017, 21:26:26
    Author     : Gato
--%>

<%@page import="utils.Constantes"%>

<%@page import="model.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="cons" class="utils.UrlsPaths" scope="session"/>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Alumnos List</title>
        <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css">
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
            function cargarAlumno(id, nombre, fecha_nacimiento, mayor_edad) {
                document.getElementById("id").value = id;
                document.getElementById("nombre").value = nombre;
                document.getElementById("fecha_nacimiento").value = fecha_nacimiento;

                if (mayor_edad === true) {
                    document.getElementById("mayor_edad_true").checked = true;
                } else {
                    document.getElementById("mayor_edad_false").checked = true;
                }
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
        <c:if test="${not empty alumnoResult}">
            <form action="alumnos">
                <h3><c:out value="${resultado}"/></h3>
                <input type="hidden" name="id" value="${alumnoResult.id}" ><br>           
                <button name="action" name="action" value="delete_force" type="submit">Borrar Completamente</button>
                <button name="action" name="action" value="CANCEL" type="submit">Cancelar</button>

            </form>

        </c:if>

        <c:if test="${empty alumnoResult}">
            <c:out value="${resultado}"/>

        </c:if>
        <br>
        <table class="table">
            <tr>
                <th></th>
                <th>Nombre</th>
                <th>Fecha</th>                
                <th></th>
            </tr>
            <c:forEach var="alumno" items="${alumnosList}">
                <tr>
                    <td>
                        <c:set var="nombre" value="${alumno.nombre}"/>

                        <button id="cargarAlumno" onClick="
                cargarAlumno(${alumno.id},
                        '${fn:escapeXml(fn:replace(nombre,"'","\\'"))}'
                        , '${alumno.fecha_nacimiento}',
                                ${alumno.mayor_edad})">Cargar</button>
                    </td>
                    <td contenteditable="true">
                        <c:out value="${alumno.nombre}"/>
                    </td>
                    <td contenteditable="true">
                        <c:out value="${alumno.fecha_nacimiento}"/>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <form action="alumnos" >
            <input type="hidden" name="id" id="id" ><br>
            Nombre:
            <input type="text" name="nombre" id="nombre" ><br>
            Fecha Nacimiento:
            <input type="date" name="fecha_nacimiento" id="fecha_nacimiento" placeholder="yyyy-mm-dd"><br>
            Mayor de edad: <br>
            Si: <input type="radio" name="mayor_edad" value="on" id="mayor_edad_true" required >
            No: <input type="radio" name="mayor_edad" value="off" id="mayor_edad_false" >
            <br>
            <input type="submit" name="action" value="INSERT">
            <input type="submit" name="action" value="UPDATE">
            <input type="submit" name="action" value="DELETE">
        </form>  


        <p>Nº Alumnos: 
            <c:out value="${fn:length(alumnosList)}"/>
        </p>

        <script>
            if (document.getElementById("fecha_nacimiento").type !== "date") { //if browser doesn't support input type="date", initialize date picker widget:
                $(function () {
                    // Find any date inputs and override their functionality
                    $('input[type="date"]').datepicker({dateFormat: 'yy-mm-dd'});
                });
            }

        </script>
    </body>
</html>
