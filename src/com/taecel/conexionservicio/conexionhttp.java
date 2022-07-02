package com.taecel.conexionservicio;

import com.sistema.util.configTaecel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class conexionhttp {

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = okhttp3.MediaType.parse("application/x-www-form-urlencoded");

    configTaecel con = new configTaecel();

    public JSONObject urlConnect(String metodo, String url, String extra1) {
        JSONObject response = null;
        try {
            RequestBody body = RequestBody.create(mediaType, "key=" + con.getKey() + "&nip=" + con.getNip() + extra1);
            Request request = new Request.Builder()
                    .url(url)
                    .method(metodo.toUpperCase(), body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response responseHTTP = client.newCall(request).execute();
            String resultado = responseHTTP.body().string();
            response = new JSONObject(resultado);
            
        } catch (Exception e) {
            System.out.println("Error en el consumo:" + e.getMessage());
        }
        return response;
    }

}
