/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.InputStream;
import javax.servlet.ServletContext;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author daw
 */
public class Configuration {

    private static Configuration config;

    private String apiKeyPass;

    private Configuration() {

    }

    public static Configuration getInstance() {

        return config;
    }

    public static Configuration getInstance(InputStream file, ServletContext sc) {
        if (config == null) {
            Yaml yaml = new Yaml();
            config = (Configuration) yaml.loadAs(file, Configuration.class);
        }
        return config;
    }

    public static Configuration getConfig() {
        return config;
    }

    public static void setConfig(Configuration config) {
        Configuration.config = config;
    }

    public String getApiKeyPass() {
        return apiKeyPass;
    }

    public void setApiKeyPass(String apiKeyPass) {
        this.apiKeyPass = apiKeyPass;
    }

}//fin clase
