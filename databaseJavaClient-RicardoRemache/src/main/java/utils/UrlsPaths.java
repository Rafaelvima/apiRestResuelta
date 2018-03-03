/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author daw
 */
public class UrlsPaths {

    public static final String ALUMNOS = "/privado/alumnos";
    public static final String ASIGNATURAS = "/privado/asignaturas";
    public static final String NOTAS = "/privado/notas";
    public static final String REGISTRO = "/registro";
    public static final String PRIVADO = "/privados/*";

    public String logout = REGISTRO + "?" + Constantes.actionJSP + "=" + Constantes.LOGOUT;

    private static final String PRERELATIVEPATH = "..";

    public static String getAlumnos() {
        return ALUMNOS;
    }

    public static String getAsignaturas() {
        return ASIGNATURAS;
    }

    public static String getNotas() {
        return NOTAS;
    }

    public static String getRegistro() {
        return REGISTRO;
    }

    public static String getAlumnosRelative() {
        return PRERELATIVEPATH + ALUMNOS;
    }

    public static String getAsignaturasRelative() {
        return PRERELATIVEPATH + ASIGNATURAS;
    }

    public static String getNotasRelative() {
        return PRERELATIVEPATH + NOTAS;
    }

    public static String getRegistroRelative() {
        return PRERELATIVEPATH + REGISTRO;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

}//fin clase
