/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import config.Configuration;
import dao.ApikeyDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apikey;

/**
 *
 * @author Gato
 */
public class ApikeyServicios {

    public ApikeyServicios() {
    }

    public Apikey checkApikey(String apikey) {
        ApikeyDAO dao = new ApikeyDAO();
        return dao.checkApiKeyJdbcTemplate(apikey);
    }

    public boolean checkLimitApikey(Apikey apikey) {
        boolean isOverLimit = false;
        try {
            ApikeyDAO dao = new ApikeyDAO();
            if (apikey.getFecha_ultima_peticion().compareTo(getDateBD(dao)) == 0) {
                if (Configuration.getInstance().getNumMaxPeticiones() > apikey.getNum_peticiones()) {
                    dao.updateCounterApikeyJDBCTemplate(apikey);
                } else {
                    isOverLimit = true;
                }

            } else {
                dao.resetCounterApikeyJDBCTemplate(apikey);

            }
        } catch (ParseException ex) {
            Logger.getLogger(ApikeyServicios.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isOverLimit;
    }

    private Date getDateBD(ApikeyDAO dao) throws ParseException {
        String fechaDB = dao.selectDBDateJdbcTemplate();
        return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fechaDB).getTime());

    }

}
