/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author root
 */
/**
 *
 * @author prometeo
 */
public class Fichero {

    String fichero = "SAIVent/database.config";
    String nbd = "";
    String ipbd = "";
    String nombreTienda = "";

    public Fichero() {
        leeFichero();
    }

    private String getHome() {
        return System.getProperty("user.home");
    }

    private String getSeparador() {
        return System.getProperty("file.separator");
    }

    public File getFichero() {
        String sf = getHome() + getSeparador() + fichero;
        File f = new File(sf);
        if (f.exists()) {
            return f;
        } else {
            System.out.println("El fichero no existe: " + sf);
            return null;
        }
    }

    public void leeFichero() {
        if (getFichero() != null) {
            try {
                try (FileReader fr = new FileReader(getFichero())) {
                    BufferedReader br = new BufferedReader(fr);
                    System.out.println("lineas:"+br.lines().toString());
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("linea:" + linea);
                        establece(linea);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Excepcion leyendo ficheros " + fichero + ": " + e);
            }
        } else {
            System.out.println("No se encontro el fichero.");
        }
    }

    private void establece(String linea) {
        try {
            String cadena[] = linea.split(",");
            System.out.println("PArte1:" + cadena[0]);
            System.out.println("PArte1:" + cadena[1]);
            System.out.println("PArte1:" + cadena[2]);
            String BaseLinea[]=cadena[0].split("=");
            String IPLinea[]=cadena[1].split("=");
            String NombreLinea[]=cadena[2].split("=");
            
            nbd=BaseLinea[1];
            ipbd=IPLinea[1];
            nombreTienda=NombreLinea[1];
            
        } catch (Exception e) {
            System.out.println("Error al leer linea:"+e.getMessage());
        }

    }

    public String getBd() {
        return nbd;
    }

    public String getIpbd() {
        return ipbd;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }
    
    
}
