/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import model.Alumno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;

import utils.ConstantesError;

import utils.SqlQuery;

/**
 *
 * @author oscar
 */
public class AlumnosDAO {

    public List<Alumno> getAllAlumnosJDBC() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getInstance().getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery(SqlQuery.SELECT_ALL_ALUMNOS);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt(SqlQuery.ID);
                String nombre = rs.getString(SqlQuery.NOMBRE);
                Date fn = rs.getDate(SqlQuery.FECHA_NACIMIENTO);
                Boolean mayor = rs.getBoolean(SqlQuery.MAYOR_EDAD);
                nuevo = new Alumno();
                nuevo.setFecha_nacimiento(fn);
                nuevo.setId(id);
                nuevo.setMayor_edad(mayor);
                nuevo.setNombre(nombre);
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;

    }

    public Alumno updateUserJDBC(Alumno alumno) {
        boolean updated = false;
        Connection connection = null;

        PreparedStatement stmt = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            stmt = connection.prepareStatement(SqlQuery.UPDATE_ALUMNO);

            stmt.setString(1, alumno.getNombre());
            stmt.setDate(2, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, alumno.getMayor_edad());
            stmt.setInt(4, (int) alumno.getId());

            if (stmt.executeUpdate() > 0) {
                updated = Boolean.TRUE;
            }else{
            alumno = null;
            }
        } catch (Exception e) {
            alumno = null;
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
        return alumno;
    }//fin update

    public Alumno insertUserJDBC(Alumno alumno) {
        Connection connection = null;
        boolean insertado = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            stmt = connection.prepareStatement(SqlQuery.INSERT_ALUMNO, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, alumno.getNombre());
            stmt.setDate(2, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, alumno.getMayor_edad());

            if (stmt.executeUpdate() > 0) {
                insertado = Boolean.TRUE;
            }
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                BigDecimal bd = rs.getBigDecimal(1);
                alumno.setId(bd.longValue());
            }

        } catch (Exception e) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
            alumno = null;
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
        return alumno;

    }//fin insert

    public int deleteUserByIdJDBC(long key) {

        int filasErased = -1;
        Connection connection = null;

        PreparedStatement stmt = null;
        try {
            connection = DBConnection.getInstance().getConnection();;

            stmt = connection.prepareStatement(SqlQuery.DELETE_ALUMNO, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, key);

            filasErased = stmt.executeUpdate();

        } catch (Exception e) {

            if (e.getMessage().contains(ConstantesError.errorForeingkey)) {
                filasErased = ConstantesError.CodeErrorClaveForanea;
            }
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
        return filasErased;
    }

    //DELETE_FORCE
    public boolean deleteUserByIddbUtils(int key) throws SQLException {

        int filasNota = -1;
        int filasAlumno = -1;
        boolean borrado = Boolean.FALSE;

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(Boolean.FALSE);
            QueryRunner qr = new QueryRunner();

            filasNota = qr.update(con,
                    SqlQuery.DELETE_NOTA_ALUMNO,
                    key);
            filasAlumno = qr.update(con,
                    SqlQuery.DELETE_ALUMNO,
                    key);

            if (filasNota > 0 && filasAlumno > 0) {
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

}//fin clase
