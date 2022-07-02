package com.taecel.conexionservicio;

import com.google.gson.Gson;
import org.json.JSONObject;

import com.taecel.modelo.BalanceDTO;
import com.taecel.modelo.ProductsDTO;
import com.taecel.modelo.StatusDTO;
import com.taecel.modelo.TransaccionDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class metodosHTTP {

    conexionhttp conect = new conexionhttp();
    Gson gson = new Gson();

    public BalanceDTO getBalance() {
        BalanceDTO balanceResponse = null;
        try {
            JSONObject json = conect.urlConnect("POST", "https://taecel.com/app/api/getBalance", "");
            balanceResponse = gson.fromJson(json.toString(), BalanceDTO.class);

        } catch (Exception e) {
            System.out.println("Error al realizar el consumo:" + e.getMessage());
        }
        return balanceResponse;
    }

    public BalanceDTO getSales(String fecha, String idBolsa) {
        BalanceDTO balanceResponse = null;
        try {
            JSONObject json = conect.urlConnect("POST", "https://taecel.com/app/api/getSales", "&fecha=" + fecha + "&bolsa=" + idBolsa);
            balanceResponse = gson.fromJson(json.toString().replace("\\\\", ""), BalanceDTO.class);

        } catch (Exception e) {
            System.out.println("Error al realizar el consumo:" + e.getMessage());
        }
        return balanceResponse;
    }

    public ProductsDTO getProducts() {
        ProductsDTO productsResponse = null;
        int code = 0;
        String mensaje = "";
        try {
            JSONObject json = conect.urlConnect("POST", "https://taecel.com/app/api/getProducts", "");
            code = json.getInt("error");
            mensaje = json.getString("message");
            System.out.println("json products:" + json);
            productsResponse = gson.fromJson(json.toString(), ProductsDTO.class);

        } catch (Exception e) {
            System.out.println("Error al realizar el consumo getProducts:" + e.getMessage());
            if(code == 405){
                JOptionPane.showMessageDialog(null,mensaje,"",JOptionPane.ERROR_MESSAGE);
            }

        }
        return productsResponse;
    }

    public TransaccionDTO getTransaccion(String producto, String referencia, String monto) {
        TransaccionDTO transaccionResponse = null;
        try {
            JSONObject json = conect.urlConnect("POST", "https://taecel.com/app/api/RequestTXN", "&producto=" + producto + "&referencia=" + referencia + "&monto=" + monto);
            //System.out.println("Json Get Transaction:" + json);
            //{"data":{"transID":"220600386450"},"success":true,"extra":null,"error":0,"message":"Consulta Exitosa"}
            //{"data":[],"success":false,"extra":null,"error":"3128","message":"Numero recargado en menos de 10 minutos, por favor revise su reporte de ventas o espere para intentarlo nuevamente"}
            transaccionResponse = gson.fromJson(json.toString(), TransaccionDTO.class);
           // transaccionResponse.setSuccess(false);
            //transaccionResponse.setMessage("Numero recargado en menos de 10 minutos");
        } catch (Exception e) {
            System.out.println("Error al realizar el consumo de productos:" + e.getMessage());
        }
        return transaccionResponse;
    }

    public StatusDTO getStatus(String idtransaccion) {
        StatusDTO statusResponse = null;
        try {
            JSONObject json = conect.urlConnect("POST", "https://taecel.com/app/api/StatusTXN", "&transID=" + idtransaccion);
            System.out.println("Json Estatus:" + json);
            //{"data":{"Status":"Fracasada ","Cargo":"$0.00","Timeout":"0.00","Folio":"","IP":"189.158.2.224","Nota":"Saldo insuficiente","Abono":"$0.00","Via":"WS","Fecha":"2022-06-06 14:55:25","pin":"","Telefono":"9631562456","Monto":"$200.00","Saldo Final":"$68.90","Carrier":"Paquete Amigo Sin Limite","Bolsa":"Tiempo Aire","TransID":"220600386744","Región":"8","Comision":"$0.00"},"success":false,"extra":null,"error":"2130","message":"No cuenta con saldo suficiente para realizar la transacción"}
            //{"data":{"Status":"Exitosa","Cargo":"$0.00","Timeout":"2.25","Folio":"644439","IP":"189.158.2.224","Nota":"","Abono":"$0.00","Via":"WS","Fecha":"2022-06-06 15:04:19","pin":"","Telefono":"9631562456","Monto":"$20.00","Saldo Final":"$48.90","Carrier":"Paquete Amigo Sin Limite","Bolsa":"Tiempo Aire","TransID":"220600387497","Región":"8","Comision":"$0.00"},"success":true,"extra":null,"error":0,"message":"Transacción Exitosa"}
            statusResponse = gson.fromJson(json.toString(), StatusDTO.class);

        } catch (Exception e) {
            System.out.println("Error al realizar el consumo:" + e.getMessage());
        }
        return statusResponse;
    }

    public String productosTaecel(String contenido) {
        String contenidoResultado = "";
        try {
            File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + ".taecel");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
            }

            String rutaFile = System.getProperty("user.home") + System.getProperty("file.separator") + ".taecel";
            File foundFile = new File(rutaFile);
            if (foundFile.exists()) {
                FileReader fileRead = new FileReader(foundFile);
                BufferedReader br = new BufferedReader(fileRead);
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenidoResultado = contenidoResultado + linea;
                }
            } else {
                System.out.println("El fichero no existe: " + rutaFile);

            }

        } catch (Exception e) {
            System.out.println("Error al crear archivo productos:" + e.getMessage());
        }

        return contenidoResultado;

    }

}
