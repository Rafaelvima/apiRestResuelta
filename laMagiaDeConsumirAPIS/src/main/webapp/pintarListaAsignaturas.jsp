<%-- 
    Document   : pintarListaAsignaturas
    Created on : 07-nov-2017, 9:44:08
    Author     : Miguel Angel Diaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="assets/scripts/javascriptAsignaturas.js"></script>

        <title>Asignaturas</title>
        <script>
            function cargarAsignatura(id, nombre, ciclo, curso) {
                document.getElementById("idasignatura").value = id;
                document.getElementById("nombre").value = nombre;
                document.getElementById("ciclo").value = ciclo;
                document.getElementById("curso").value = curso;
                document.getElementById("actualizar").disabled = false;
                document.getElementById("borrar").disabled = false;
                document.getElementById("insertar").disabled = true;
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="alumnos">Alumnos</a></li>
                <li class="nav-item active"><a class="nav-link" style="cursor: default; pointer-events: none" href="#">Asignaturas</a></li>
                <li class="nav-item"><a class="nav-link" href="notas">Notas</a></li>
            </ul>
        </nav>
        <div class="container">
            <div class="col-xs-8 col-xs-offset-2">
                <h1>Asignaturas</h1>
                <form id="formulario" name="formulario" method="post">
                    <input type="hidden" id="idasignatura" name="idasignatura">
                    <input type="text" id="nombre" name="nombre">
                    <input type="text" id="ciclo" name="ciclo">
                    <input type="text" id="curso" name="curso">
                    <input type="hidden" id="accion" name="accion">
                    <br>
                    <br>
                    <button id="actualizar" disabled>Actualizar</button>
                    <button id="insertar" >Insertar</button>
                    <button id="borrar" disabled>Borrar</button>
                </form>
                <c:if test="${error == null}">
                    <table class="table table-striped" id="tabla">
                        <tr style="font-weight: bold">
                            <td></td>
                            <td>NOMBRE</td>
                            <td>CICLO</td>
                            <td>CURSO</td>
                        </tr>
                        <c:forEach items="${asignaturas}" var="asignatura">
                            <tr id="fila${asignatura.id}">
                                <td><input type="button" value="Cargar ${asignatura.id}" style="width:100px" onclick="cargarAsignatura('${asignatura.id}',
                                                '${fn:escapeXml(fn:replace(asignatura.nombre,"'", "\\'"))}', '${fn:escapeXml(fn:replace(asignatura.ciclo,"'", "\\'"))}',
                                                '${fn:escapeXml(fn:replace(asignatura.curso,"'", "\\'"))}')"></td>
                                <td>${asignatura.nombre}</td>
                                <td>${asignatura.ciclo}</td>
                                <td>${asignatura.curso}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <h1 id="error">${error}</h1>
            </div>
        </div>
    </body>
</html>
