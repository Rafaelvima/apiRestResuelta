<%-- 
    Document   : pintarListaAlumnos
    Created on : Oct 28, 2017, 8:02:42 PM
    Author     : Miguel Angel Diaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <script src="assets/scripts/javascriptAlumnos.js"></script>

        <title>Alumnos</title>
        <script>
            function cargarAlumno(id, nombre, fecha, mayor) {
                document.getElementById("idalumno").value = id;
                document.getElementById("nombre").value = nombre;
                document.getElementById("fecha").value = fecha;
                document.getElementById("mayor").checked = mayor;
                document.getElementById("actualizar").disabled = false;
                document.getElementById("borrar").disabled = false;
                document.getElementById("insertar").disabled = true;
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item active"><a class="nav-link" style="cursor: default; pointer-events: none" href="#">Alumnos</a></li>
                <li class="nav-item"><a class="nav-link" href="asignaturas">Asignaturas</a></li>
                <li class="nav-item"><a class="nav-link" href="notas">Notas</a></li>
            </ul>
        </nav>
            <div class="container">
                <div class="col-xs-8 col-xs-offset-2">
                    <h1>ALUMNOS</h1>
                
                    <form id="formulario" name="formulario" method="post">
                        <input type="hidden" id="idalumno" name="idalumno" />
                        <input type="text" id="nombre" name="nombre" size="12"/>
                        <input type="text" id="fecha" name="fecha" size="12"/>
                        <input type="checkbox" id="mayor" name="mayor"/>
                        <input type="hidden" id="accion" name="accion" value="">
                        <br>
                        <br>
                        <button id="actualizar" disabled>Actualizar</button>
                        <button id="insertar">Insertar</button>
                        <button id="borrar" disabled>Borrar</button>
                    </form>
                    <br>
                    <c:if test="${error == null}">
                        <table id="tabla" class="table table-striped">
                            <tr style="font-weight: bold">
                                <td></td>
                                <td>NOMBRE</td>
                                <td>FECHA DE NACIMIENTO</td>
                                <td>MAYOR DE EDAD</td>
                            </tr>
                            <c:forEach items="${alumnos}" var="alumno">  
                                <tr id="fila${alumno.id}">
                                    <td>
                                        <input type="button" value="Cargar ${alumno.id}" style="width:100px"
                                               onclick="cargarAlumno('${alumno.id}', '${fn:escapeXml(fn:replace(alumno.nombre,"'", "\\'"))}'
                                                           , '<fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>'
                                                           , ${alumno.mayor_edad});"/>
                                    </td> 
                                    <td>${alumno.nombre}</td>
                                    <td><fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/></td>
                                    <td><input type="checkbox" <c:if test="${alumno.mayor_edad}" >checked</c:if> /></td>
                                    </tr>
                            </c:forEach>                   
                        </table>
                    </c:if>
                    <h1 id="error">${error}</h1>
                </div>
            </div>
    </body>
</html>
