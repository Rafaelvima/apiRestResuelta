//AJAX para insertar
$(document).ready(function () {
    $("#insertar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "insertar";
        $.post("alumnos", $("#formulario").serialize(),
                function (data, status) {
                    if (data != "401") {
                        var datos = JSON.parse(data);
                        var fecha = new Date(datos.fecha_nacimiento);
                        var cadenaFecha = fecha.getDate() + "-" + (fecha.getMonth() + 1) + "-" + fecha.getFullYear();
                        $("#tabla").append("<tr id='fila" + datos.id + "'></tr>");
                        $("#fila" + datos.id).append("<td><input type='button' value='Cargar " + datos.id + "' style='width:100px' " +
                                "onclick=\"cargarAlumno('" + datos.id + "','" + datos.nombre + "','" + cadenaFecha + "'," + datos.mayor_edad + ")\"></td>");
                        $("#fila" + datos.id).append("<td>" + datos.nombre + "</td>");
                        $("#fila" + datos.id).append("<td>" + cadenaFecha + "</td>");
                        if (datos.mayor_edad) {
                            $("#fila" + datos.id).append("<td><input type='checkbox' checked></td>");
                        } else {
                            $("#fila" + datos.id).append("<td><input type='checkbox'></td>");
                        }
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
        $.post("alumnos", $("#formulario").serialize(),
                function (data, status) {
                    if (data != "401") {
                        var datos = JSON.parse(data);
                        var fecha = new Date(datos.fecha_nacimiento);
                        var cadenaFecha = fecha.getDate() + "-" + (fecha.getMonth() + 1) + "-" + fecha.getFullYear();
                        $("#fila" + datos.id).empty();
                        $("#fila" + datos.id).append("<td><input type='button' value='Cargar " + datos.id + "' style='width:100px' " +
                                "onclick=\"cargarAlumno('" + datos.id + "','" + datos.nombre + "','" + cadenaFecha + "'," + datos.mayor_edad + ")\"></td>");
                        $("#fila" + datos.id).append("<td>" + datos.nombre + "</td>");
                        $("#fila" + datos.id).append("<td>" + cadenaFecha + "</td>");
                        if (datos.mayor_edad) {
                            $("#fila" + datos.id).append("<td><input type='checkbox' checked></td>");
                        } else {
                            $("#fila" + datos.id).append("<td><input type='checkbox'></td>");
                        }
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
        $.post("alumnos", $("#formulario").serialize(),
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
    var borrarnotas = confirm("Este alumno tiene notas.\n¿Quieres continuar?");
    if (borrarnotas == true) {
        document.getElementById("accion").value = "borrar2";
        $.post("alumnos", $("#formulario").serialize(),
                function (data, status) {
                    var datos = JSON.parse(data);
                    $("#fila" + datos.id).remove();
                }
        );
    }
}