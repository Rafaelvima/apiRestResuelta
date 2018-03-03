//AJAX para insertar
$(document).ready(function () {
    $("#insertar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "insertar";
        $.post("asignaturas", $("#formulario").serialize(),
                function (data, status) {
                    if (data != "401") {
                        var datos = JSON.parse(data);
                        $("#tabla").append("<tr id='fila" + datos.id + "'></tr>");
                        $("#fila" + datos.id).append("<td><input type='button' value='Cargar " + datos.id + "' style='width:100px' " +
                                "onclick=\"cargarAsignatura('" + datos.id + "','" + datos.nombre + "','" + datos.ciclo + "','" + datos.curso + "')\"></td>");
                        $("#fila" + datos.id).append("<td>" + datos.nombre + "</td>");
                        $("#fila" + datos.id).append("<td>" + datos.ciclo + "</td>");
                        $("#fila" + datos.id).append("<td>" + datos.curso + "</td>");
                        
                    } else {
                        mostrarError();
                    }
                });
    });
});

//AJAX para modificar
$(document).ready(function () {
    $("#actualizar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "actualizar";
        $.post("asignaturas", $("#formulario").serialize(),
                function (data, status) {
                    if (data != "401") {
                        var datos = JSON.parse(data);
                        $("#fila" + datos.id).empty();
                        $("#fila" + datos.id).append("<td><input type='button' value='Cargar " + datos.id + "' style='width:100px' " +
                                "onclick=\"cargarAsignatura('" + datos.id + "','" + datos.nombre + "','" + datos.ciclo + "'," + datos.curso + ")\"></td>");
                        $("#fila" + datos.id).append("<td>" + datos.nombre + "</td>");
                        $("#fila" + datos.id).append("<td>" + datos.ciclo + "</td>");
                        $("#fila" + datos.id).append("<td>" + datos.curso + "</td>");
                        
                    } else {
                        mostrarError();
                    }
                });
    });
});

//AJAX para borrar
$(document).ready(function () {
    $("#borrar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "borrar";
        $.post("asignaturas", $("#formulario").serialize(),
                function (data, status) {
                    if (data != "503") {
                        var datos = JSON.parse(data);
                        $("#fila" + datos.id).remove();
                    } else {
                        borrarIntegridad();
                    }
                });
    });
});

function mostrarError() {
    $("#tabla").remove();
    $("#error").html("Se ha superado la cuota establecida");
}

function borrarIntegridad() {
    var borrarnotas = confirm("Esta asignatura tiene notas.\n¿Quieres continuar?");
    if (borrarnotas == true) {
        document.getElementById("accion").value = "borrar2";
        $.post("asignaturas", $("#formulario").serialize(),
                function (data, status) {
                    var datos = JSON.parse(data);
                    $("#fila" + datos.id).remove();
                }
        );
    }
}