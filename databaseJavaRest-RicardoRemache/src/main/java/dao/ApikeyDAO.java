/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Apikey;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import utils.Constantes;
import utils.SqlQuery;

/**
 *
 * @author Gato
 */
public class ApikeyDAO {

    public Apikey checkApiKeyJdbcTemplate(String apikey) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        Apikey apikeyObj = null;
        Object[] apikeyClient = new Object[]{apikey};

        String resultadoQuery = jtm.query(SqlQuery.SELECT_APIKEY, apikeyClient, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getString(Constantes.APIKEY) : null;
            }
        });

        if (resultadoQuery != null) {
            apikeyObj = (Apikey) jtm.queryForObject(SqlQuery.SELECT_APIKEY, apikeyClient,//funciona siempre que exista un dato en la base de datos
                    new BeanPropertyRowMapper(Apikey.class));
        }

        return apikeyObj;
    }

    public String selectDBDateJdbcTemplate() {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String resultadoQuery = jtm.query(SqlQuery.SELECT_CURRENT_DATE_DB, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? rs.getString(Constantes.FECHA) : null;
            }
        });

        return resultadoQuery;
    }

    public int updateCounterApikeyJDBCTemplate(Apikey apikey) {

        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        int rowsAffected = jtm.update(SqlQuery.UPDATE_COUNTER_APIKEY_BY_ID, apikey.getId());

        return rowsAffected;
    }

    public int resetCounterApikeyJDBCTemplate(Apikey apikey) {

        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        int rowsAffected = jtm.update(SqlQuery.RESET_COUNTER_APIKEY_BY_ID, apikey.getId());

        if (rowsAffected == 0) {
            rowsAffected = -1;
        }

        return rowsAffected;
    }

}//fin clase
