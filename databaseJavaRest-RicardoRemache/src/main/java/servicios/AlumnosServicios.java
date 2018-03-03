/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.Asignatura;
import model.Nota;

import utils.SqlQuery;

/**
 *
 * @author oscar
 */
public class AlumnosServicios {

    public AlumnosServicios() {

    }

    public List<Alumno> getAllAlumnos() {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.getAllAlumnosJDBC();
    }

    public Alumno updateAlumnoJDBC(Alumno alumno) {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.updateUserJDBC(alumno);
    }

    public Alumno insertAlumnoJDBC(Alumno alumno) {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.insertUserJDBC(alumno);
    }

    public int deleteAlumnoJDBC(long id) {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.deleteUserByIdJDBC(id);
    }

    public boolean deleteAlumnoForce(int i) throws SQLException {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.deleteUserByIddbUtils(i);
    }

    /**
     *
     * @param parametros
     * @return objeto alumno con sus parametros correspondientes
     * @throws UnsupportedEncodingException
     */
    public Alumno tratarParametros(Map<String, String[]> parametros) throws UnsupportedEncodingException {
        Alumno alumno = null;
        if (parametros != null && !parametros.isEmpty()) {

            alumno = new Alumno();

            Iterator<String> it = parametros.keySet().iterator();

            while (it.hasNext()) {
                String key = (String) it.next();
                String[] values = (String[]) parametros.get(key);
                if (values[0] != null && !values[0].isEmpty()) {

                    if (SqlQuery.ID.equalsIgnoreCase(key)) {
                        alumno.setId(Long.valueOf(values[0]));
                    } else if (SqlQuery.NOMBRE.equalsIgnoreCase(key)) {
                        alumno.setNombre(values[0]);
                    } else if (SqlQuery.FECHA_NACIMIENTO.equalsIgnoreCase(key)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parseDate = null;
                        try {
                            parseDate = dateFormat.parse(values[0]);
                            alumno.setFecha_nacimiento(new Date(parseDate.getTime()));
                        } catch (ParseException ex) {
                            Logger.getLogger(AlumnosServicios.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (SqlQuery.MAYOR_EDAD.equalsIgnoreCase(key)) {
                        alumno.setMayor_edad("on".equals(values[0]) ? Boolean.TRUE : Boolean.FALSE);
                    }
                }

            }

        }
        return alumno;
    }

    public boolean comprobarCamposAlumno(Alumno alumno, boolean update) {
        return alumno != null && alumno.getNombre() != null && alumno.getFecha_nacimiento() != null && (update ? alumno.getId() > 0 : true);
    }

    public boolean comprobarCamposAsignatura(Asignatura asig, boolean update) {
        return asig != null && asig.getNombre() != null && asig.getCiclo() != null && asig.getCurso() != null && (update ? asig.getId() > 0 : true);
    }

    public boolean comprobarCamposNota(Nota nota, boolean update) {
        return nota != null && nota.getId_alumno() > 0 && nota.getId_asignatura() > 0 && (update ? nota.getNota() > -1 : true);
    }

}//fin clase
