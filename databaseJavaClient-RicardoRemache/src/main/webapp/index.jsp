<%-- 
    Document   : index
    Created on : 05-dic-2017, 8:50:47
    Author     : daw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cons" class="utils.UrlsPaths" scope="session"/>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.css">
        <style>
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
            <c:set var="baseURL" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />

            <a href="<c:out value="${baseURL}${cons.getAlumnos()}"/>">alumnos</a>
            <a href="<c:out value="${baseURL}${cons.getAsignaturas()}"/>">asignaturas</a>
            <a href="<c:out value="${baseURL}${cons.getNotas()}"/>">notas</a>
            

        </div>
    </body>
</html>