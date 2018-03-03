/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author oscar
 */
public class Constantes {

    public static String alumnosList = "alumnosList";
    public static String asignaturasList = "asignaturasList";
    public static String notaResult = "notaResult";
    public static String notaMessage = "notaMessage";

    public static String alumnoResult = "alumnoResult";
    public static String asignaturaResult = "asignaturaResult";

    public static String alumnosJSP = "alumnosJsp.jsp";
    public static String indexJSP = "index.jsp";
    public static String asignaturasJSP = "asignaturasjsp.jsp";
    public static String notasJSP = "notasjsp.jsp";
    public static String registroJSP = "registroJsp.jsp";

    public static String actionJSP = "action";

    public static final String ID = "ID";
    public static final String INSERT = "INSERT";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String VIEW = "VIEW";
    public static final String DELETE_FORCE = "delete_force";
    public static final String REGISTRAR = "REGISTRAR";
    public static final String VALIDATE = "VALIDATE";
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    
    public static final String JSON = "json";
    public static final String ALUMNO = "alumno";
    public static final String ASIGNATURA = "asignatura";
    public static final String NOTA = "nota";
    public static final String POST = "POST";    
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    
    //aPIKEY
    public static final String ID_CLIENT = "idClient";
    public static final String APIKEY_PASS = "apiKeyPass";
    public static final String APIKEY = "apikey";
    

    //Columnas Users
    public static final String USERS = "USERS";    
    public static final String NOMBRE = "NOMBRE";
    public static final String PASSWORD = "PASSWORD";
    public static final String MAYOR_EDAD = "MAYOR_EDAD";
    public static final String ACTIVO = "ACTIVO";
    public static final String CODIGO_ACTIVACION = "CODIGO_ACTIVACION";
    public static final String FECHA_ACTIVACION = "FECHA_ACTIVACION";
    public static final String EMAIL = "EMAIL";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String CURSO = "CURSO";
    public static final String CICLO = "CICLO";
    public static final String ID_ALUMNO = "ID_ALUMNO";
    public static final String ID_ASIGNATURA = "ID_ASIGNATURA";

    public static String resultadoQuery = "resultado";
    public static String messageQueryAlumnoInserted = "El alumno fue insertado en la lista";
    public static String messageQueryAlumnoInsertedFail = "Alumno no agregado en la lista, vuelve a intentarlo";
    public static String faltanCampos = "Faltan campos en el objeto";
    public static String messageQueryAlumnoUpdated = "El alumno fue actualizado en la lista";
    public static String messageQueryAlumnoUpdatedFail = "Problemas actualizando el alumno, inténtalo otra vez";
    public static String messageQueryAlumnoDeleted = "El alumno fue eliminado correctamente de la lista";
    public static String messageQueryAlumnoDeletedFail = "Cuidado! el alumno tiene notas, Quieres borrarlo?";
    public static String messageQueryAlumnoDeleteMissing = "No Existe el alumno que tratas de borrar";
    public static String messageQueryAlumnoDeletedFailedAgain = "Seguimos sin poder borrar el alumno";

    public static String messageQueryAsignaturaInserted = "Asignatura fue insertado en la lista";
    public static String messageQueryAsignaturaInsertFailed = "Asignatura no agregada a la base de datos";
    public static String messageQueryAsignaturaUpdated = "Asignatura actualizada correctamente";
    public static String messageQueryAsignaturaUpdateFailed = "Error en la actualización de Asignatura";
    public static String messageQueryAsignaturaDeleted = "Asignatura eliminada correctamente";
    public static String messageQueryAsignaturaDeletedFail = "Cuidado! Tienes alumnos que estan cursando esta asignatura. Estás Seguro?";
    public static String messageQueryAsignaturaDeleteMissing = "No Existe la asignatura que tratas de borrar";
    public static String messageQueryAsignaturaDeletedFailedAgain = "Problemas graves, no pudimos borrar la Asignatura";

    public static String messageQueryNotaMissing = "No tiene nota";
    public static String messageQueryNotaUpdated = "Hemos actualizado la nota correctamente";
    public static String messageQueryNotaUpdatedFail = "Falló en la actualización de Nota";
    public static String messageQueryNotaDeleted = "Notas borradas para el alumno";

    public static String messageUserExist = "Ya tenemos un usuario con un Nombre o Email igual";
    public static String messageUserMissingFields = "Te faltan campos por rellenar";
    public static String messageUserErrorInsert = "Tenemos un problema en el servicio, Intentalo más tarde";
    public static String messageUserRegisterSubmitEmail = "Estás a un paso de completar tu registro, \n por favor, revisa tu correo";
    public static String messageUserRegisterSubmitEmailFail = "Tenemos problemas para enviarte un correo de confirmación";
    public static String messageUserValidateEmailFail = "Tenemos problemas validando tu Email, enlace de Validación Erróneo, faltan parametros";
    public static String messageUserValidateEmailFailID = "Tenemos problemas validando tu Email, enlace de Validación Erróneo";
    public static String messageUserValidateFail = "No podemos Validar tu cuenta intentalo más tarde.";
    public static String messageUserValidateOk = "Felicidades, Hemos validado tu cuenta.";
    public static String messageUserValidateEmailTimeOut = "Has sobrepasado el tiempo de validación";
    public static String messageUserLoginFailNombre = "Error en las credenciales, El Usuario no existe";
    public static String messageUserLoginFailPassword = "Contraseña Errónea";
    public static String messageUserLoginFailActivo = "Quieto parado! Este Usuario no ha sido validado";
    
    public static String messageFromServer = "messageFromServer";
    public static String LOGIN_ON = "loginOnFromServer";
    
    //email
    public static String emailSubjectValidate = "ASTOLFO SL: Hola %s - Correo de Activación";

    public static int MAX_RANDOM = 30;
    public static int MIN_RANDOM = 20;
    public static long MAX_TIME_TO_VALIDATE = 1000 * 60 * 60;//1hora
    

}
