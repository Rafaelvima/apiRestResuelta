<%-- 
    Document   : pintarListaNotas
    Created on : 10-nov-2017, 11:53:58
    Author     : Miguel Angel Diaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="assets/scripts/javascriptNotas.js"></script>

        <title>Notas</title>
        <script>
            function cargarAlumno(id, nombre) {
                document.getElementById("idAlumno").value = id;
                document.getElementById("nombreAlumno").value = nombre;
            }
            function cargarAsignatura(id, nombre) {
                document.getElementById("idAsignatura").value = id;
                document.getElementById("nombreAsignatura").value = nombre;
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="alumnos">Alumnos</a></li>
                <li class="nav-item"><a class="nav-link" href="asignaturas">Asignaturas</a></li>
                <li class="nav-item active"><a class="nav-link" style="cursor: default; pointer-events: none" href="#">Notas</a></li>
            </ul>
        </nav>
        <div class="container" style="text-align: center">
            <div class="col-xs-8 col-xs-offset-2">
                <h1 style="margin: 30px">Notas</h1>

                <c:if test="${error == null}">
                    <div id="contenido"> 
                        <span>Alumno: </span>
                        <select id="alumno" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                            <option disabled selected>Selecciona un alumno</option>
                            <option disabled>-------------------------</option>
                            <c:forEach items="${lista[0]}" var="alumno">
                                <option value="${alumno.id}" name="${alumno.nombre}">${alumno.nombre}</option>
                            </c:forEach>
                        </select>

                        <span>Asignatura: </span>
                        <select id="asignatura" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                            <option disabled selected>Selecciona una asignatura</option>
                            <option disabled>-------------------------</option>
                            <c:forEach items="${lista[1]}" var="asignatura">
                                <option value="${asignatura.id}">${asignatura.nombre}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <br>
                        <br>
                        <form id="formulario" method="post">
                            <table class="table-condensed" style="margin: auto">
                                <tr>
                                    <td>
                                        ALUMNO
                                        <br>
                                        <input type="hidden" id="idAlumno" name="idAlumno" size="1" value="${idAlu}">
                                        <input type="text" name="nombreAlumno" id="nombreAlumno" value="${nomAlu}">
                                    </td>
                                    <td>
                                        ASIGNATURA
                                        <br>
                                        <input type="hidden" id="idAsignatura" name="idAsignatura" size="1" value="${idAsig}">
                                        <input type="text" name="nombreAsignatura" id="nombreAsignatura" value="${nomAsig}">
                                    </td>
                                    <td>
                                        <br>
                                        <button id="cargar">Cargar</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <br>
                                        NOTA <input type="text" id="nota" name="nota" size="1">
                                    </td>
                                    <td>
                                        <br>
                                        <input type="hidden" id="accion" name="accion" value="">
                                        <button id="guardar">Guardar</button>
                                        <button id="borrar">Borrar</button>
                                    </td>
                                <tr>
                                    <td>
                                        <h3 id="info" style="display: none"></h3>
                                    </td>
                                </tr>
                                </tr>
                            </table>
                        </form>
                        <br>
                    </div> 
                </c:if>
                <h1 id="error">${error}</h1>
            </div>
        </div>
    </body>
</html>
