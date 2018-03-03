/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.UnsupportedEncodingException;

import java.util.Iterator;
import java.util.Map;

import model.Asignatura;

import utils.Constantes;

/**
 *
 * @author daw
 */
public class AsignaturasServicios {

    public AsignaturasServicios() {

    }

    public Asignatura tratarParametros(Map<String, String[]> parametros) throws UnsupportedEncodingException {
        Asignatura asignatura = null;
        if (parametros != null && !parametros.isEmpty()) {

            asignatura = new Asignatura();

            Iterator<String> it = parametros.keySet().iterator();

            while (it.hasNext()) {
                String key = (String) it.next();
                String[] values = (String[]) parametros.get(key);
                if (values[0] != null && !values[0].isEmpty()) {

                    if (Constantes.ID.equalsIgnoreCase(key)) {
                        asignatura.setId(Long.valueOf(values[0]));
                    } else if (Constantes.NOMBRE.equalsIgnoreCase(key)) {
                        asignatura.setNombre(values[0]);
                    } else if (Constantes.CURSO.equalsIgnoreCase(key)) {

                        asignatura.setCurso(values[0]);

                    } else if (Constantes.CICLO.equalsIgnoreCase(key)) {
                        asignatura.setCiclo(values[0]);

                    }
                }

            }

        }
        return asignatura;
    }

}//FIN CLASE
