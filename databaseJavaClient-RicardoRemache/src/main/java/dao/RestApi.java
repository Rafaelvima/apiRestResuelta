/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import config.Configuration;
import utils.Constantes;

/**
 *
 * @author Gato
 */
public class RestApi {

    private static RestApi instance;
    private static HttpRequestFactory requestFactory;

    public static RestApi getInstance() {
        if (instance == null) {
            instance = new RestApi();
        }
        return instance;
    }

    public HttpRequestFactory crearServicio() {
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        final JsonFactory JSON_FACTORY = new JacksonFactory();
        if (requestFactory == null) {

            requestFactory
                    = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) {
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                            request.setConnectTimeout(100000);
                        }
                    });
        }
        return requestFactory;
    }

    public HttpHeaders getHeaderApikey() {
        HttpHeaders header = new HttpHeaders();
        return header.set(Constantes.APIKEY, Configuration.getInstance().getApiKeyPass());
    }

}//fin clase
