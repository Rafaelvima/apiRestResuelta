//AJAX para cargar
$(document).ready(function () {
    $("#cargar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "cargar";
        $.post("notas", $("#formulario").serialize(),
                function (data, status, xhr) {
                    if (data != "401") {
                        if(xhr.status != "204"){
                            var datos = JSON.parse(data);
                            $("#nota").val(datos.nota);
                        }else{
                            $("#nota").val("");
                            $("#info").html("No hay notas");
                            $("#info").fadeIn(100, function(){
                                setTimeout(function(){$("#info").fadeOut(100)}, 2000);
                            });
                        }
                    } else {
                        mostrarError();
                    }
                });
    });
});

//AJAX para guardar
$(document).ready(function () {
    $("#guardar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "guardar";
        $.post("notas", $("#formulario").serialize(),
                function (data, status) {
                    if (data == "401") {
                        mostrarError();
                    }else{
                        $("#info").html("Nota guardada");
                        $("#info").fadeIn(100, function(){
                            setTimeout(function(){$("#info").fadeOut(100)}, 2000);
                        });
                    }
                });
    });
});

//AJAX para borrar
$(document).ready(function () {
    $("#borrar").click(function (event) {
        event.preventDefault();
        document.getElementById("accion").value = "borrar";
        $.post("notas", $("#formulario").serialize(),
                function (data, status) {
                    if (data == "401") {
                        mostrarError();
                    } else {
                        $("#nota").val("");
                        $("#info").html("Nota borrada");
                        $("#info").fadeIn(100, function(){
                            setTimeout(function(){$("#info").fadeOut(100)}, 2000);
                        });
                    }
                });
    });
});

function mostrarError() {
    $("#contenido").remove();
    $("#error").html("Se ha superado la cuota establecida");
}