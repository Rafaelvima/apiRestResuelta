/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Asignatura;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.ConstantesError;
import utils.SqlQuery;

/**
 *
 * @author oscar
 */
public class AsignaturasDAO {

    public List<Asignatura> getAllAsignaturasdbUtils() {
        List<Asignatura> lista = null;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> handler
                    = new BeanListHandler<Asignatura>(Asignatura.class);
            lista = qr.query(con, SqlQuery.SELECT_ALL_ASIGNATURAS, handler);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    public Asignatura insertAsignaturadbUtils(Asignatura asignatura) {

        Connection con = null;
        boolean insertado = false;
        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            long id = qr.insert(con,
                    SqlQuery.INSERT_ASIGNATURA,
                    new ScalarHandler<Long>(),
                    asignatura.getNombre(), asignatura.getCurso(), asignatura.getCiclo());

            if (id > 0) {
                insertado = Boolean.TRUE;
                asignatura.setId(id);
            }

        } catch (Exception ex) {
            asignatura = null;
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return asignatura;

    }

    public Asignatura updateAsignaturasdbUtils(Asignatura asignatura) {
        int filas = -1;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    SqlQuery.UPDATE_ASIGNATURA,
                    asignatura.getNombre(),
                    asignatura.getCurso(),
                    asignatura.getCiclo(),
                    asignatura.getId());

        } catch (Exception ex) {
            asignatura = null;
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return asignatura;
    }

    public int deleteAsignaturadbUtils(long key) {
        int filasErased = -1;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filasErased = qr.update(con,
                    SqlQuery.DELETE_ASIGNATURA,
                    key);

        } catch (Exception ex) {
            if (ex.getMessage().contains(ConstantesError.errorForeingkey)) {
                filasErased = ConstantesError.CodeErrorClaveForanea;
            }
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return filasErased;
    }

    public boolean deleteAsignaturadbUtilsForce(int key) throws SQLException {
        int filasNota = -1;
        int filasAsigantura = -1;
        boolean borrado = Boolean.FALSE;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(Boolean.FALSE);
            QueryRunner qr = new QueryRunner();

            filasNota = qr.update(con,
                    SqlQuery.DELETE_NOTA_ASIGNATURA,
                    key);
            filasAsigantura = qr.update(con,
                    SqlQuery.DELETE_ASIGNATURA,
                    key);

            if (filasNota > 0 && filasAsigantura > 0) {
                borrado = Boolean.TRUE;
                con.commit();
            } else {
                con.rollback();
            }

        } catch (Exception ex) {
            if (con != null) {
                con.rollback();
            }

            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return borrado;
    }

}//Fin clase
