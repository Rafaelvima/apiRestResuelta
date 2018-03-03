/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Nota;
import org.apache.commons.dbutils.QueryRunner;

import utils.SqlQuery;

/**
 *
 * @author daw
 */
public class NotasDAO {

    public Nota getNotaJDBC(int idAlumno, int idAsignatura) {
        Nota nota = null;

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            stmt = connection.prepareStatement(SqlQuery.SELECT_NOTA);

            stmt.setInt(1, idAsignatura);
            stmt.setInt(2, idAlumno);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int idAl = rs.getInt(SqlQuery.ID_ALUMNO);
                int idAs = rs.getInt(SqlQuery.ID_ASIGNATURA);
                int notaRs = rs.getInt(SqlQuery.NOTA);

                nota = new Nota();
                nota.setId_alumno(idAl);
                nota.setId_asignatura(idAs);
                nota.setNota(notaRs);
            }

        } catch (Exception e) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection.getInstance().cerrarConexion(connection);
        }
        return nota;
    }

    public boolean updateNotadbUtils(Nota nota) {
        int filas = -1;
        boolean updated = false;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    SqlQuery.UPDATE_NOTA,
                    nota.getNota(),
                    nota.getId_alumno(),
                    nota.getId_asignatura());

            if (filas > 0) {
                updated = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return updated;
    }

    public Nota insertUserJDBC(Nota nota) {

        Connection connection = null;
        boolean insertado = false;
        PreparedStatement stmt = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            stmt = connection.prepareStatement(SqlQuery.INSERT_NOTAS, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, (int) nota.getId_alumno());
            stmt.setInt(2, (int) nota.getId_asignatura());
            stmt.setInt(3, nota.getNota());

            if (stmt.executeUpdate() > 0) {
                insertado = Boolean.TRUE;
            } else {
                nota = null;
            }

        } catch (Exception e) {
            nota = null;
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection.getInstance().cerrarConexion(connection);

        }
        return nota;

    }//fin insert

    public boolean deleteNotadbUtils(int key) {
        int filas = -1;
        boolean borrado = Boolean.FALSE;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    SqlQuery.DELETE_NOTA_ALUMNO,
                    key);
            if (filas > 0) {
                borrado = Boolean.TRUE;
            }

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return borrado;
    }

}//fin clase
