/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Iterator;
import java.util.Map;

import model.Nota;
import utils.Constantes;

/**
 *
 * @author daw
 */
public class NotasServicios {

    public NotasServicios() {

    }

    public Nota tratarParametros(Map<String, String[]> parametros) {
        Nota claves = null;
        if (parametros != null && !parametros.isEmpty()) {

            claves = new Nota();

            Iterator<String> it = parametros.keySet().iterator();

            while (it.hasNext()) {
                String key = (String) it.next();
                String[] values = (String[]) parametros.get(key);
                if (values[0] != null && !values[0].isEmpty()) {

                    if (Constantes.ID_ALUMNO.equalsIgnoreCase(key)) {
                        claves.setId_alumno(Long.valueOf(values[0]));
                    } else if (Constantes.ID_ASIGNATURA.equalsIgnoreCase(key)) {
                        claves.setId_asignatura(Long.valueOf(values[0]));
                    } else if (Constantes.NOTA.equalsIgnoreCase(key)) {
                        claves.setNota(Integer.valueOf(values[0]));
                    }
                }

            }

        }
        return claves;
    }

}//fin clase
