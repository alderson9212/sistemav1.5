package com.taecel.conexionservicio;

import com.google.gson.Gson;
import com.sistema.modelo.SaldoDTOLocal;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONObject;

public class conexionhttpLocal {

     String basePath = "http://189.153.206.209:8085/saldo/";
     Gson gson = new Gson();
    
    

    public SaldoDTOLocal consultarSaldo(Integer id) {
        SaldoDTOLocal saldo = new SaldoDTOLocal();
        try {
            JSONObject response = new JSONObject(peticionHttpGet(basePath + "consultar/id=" + id));
            saldo = gson.fromJson(response.toString(), SaldoDTOLocal.class);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"SIN ACCESO A SERVICIOS DE RECARGAS, CONTACTE SU PROVEEDOR","",JOptionPane.ERROR_MESSAGE);
        }
        return saldo;
    }

    public SaldoDTOLocal guardarSaldo(Integer id, Double monto) {
        SaldoDTOLocal saldo = new SaldoDTOLocal();
        try {
            JSONObject response = new JSONObject(peticionHttpGet(basePath + "guardar/id=" + id + "&monto=" + monto));
            saldo = gson.fromJson(response.toString(), SaldoDTOLocal.class);
        } catch (Exception ex) {
            System.out.println("Error al guardar registros de saldo:" + ex.getMessage());
        }
        return saldo;
    }

    public void eliminarSaldo(Integer id) {
        try {
            peticionHttpGet(basePath + "eliminar/id=" + id);
        } catch (Exception ex) {
            System.out.println("Error al eliminar registros de saldo:" + ex.getMessage());
        }
    }

    public static String peticionHttpGet(String urlParaVisitar) {
        String resultado="";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlParaVisitar)
                    .get()
                    .build();
            Response responseHTTP = client.newCall(request).execute();
            resultado = responseHTTP.body().string();
            System.out.println("Resultado:"+resultado);
        } catch (Exception e) {
            System.out.println("Error al consumir servicio:"+e.getMessage());
        }
        return resultado;
    }

 
}
