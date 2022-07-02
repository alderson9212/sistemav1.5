/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author elliot
 */
public class Guardar_datos {
     
    Fichero fc=new Fichero();
    String base="",ip="",nombre="";
    
    public void LlenarCombo(JComboBox  combo){
         if (fc.getFichero() != null) {
            try {
                try (FileReader fr = new FileReader(fc.getFichero())) {
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    System.out.println("si");
                    while ((linea = br.readLine()) != null) {
                          if(!linea.equals("")){      
                           String[]string = linea.split(",");
                            base = string[0]; // 123
                            ip = string[1]; // 654321
                            nombre=string[2];
                         combo.addItem(nombre.replace("nombre=",""));
                        }
                    }
                  }            
              } catch (Exception e) {
                System.out.println("Excepcion leyendo fichero " + fc.fichero + ": " + e);
            }
       }
 }
    
 public String[]lista(String nombre){
     List<Object[]>listaR=null;
     String []datos=new String[2];
      if (fc.getFichero() != null) {
            try {
                try (FileReader fr = new FileReader(fc.getFichero())) {
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    while ((linea = br.readLine()) != null) {
                          if(!linea.trim().equals("") && linea.contains(nombre)){      
                           String[] string = linea.split(",");
                            base = string[0].replace("base=","").replace(",",""); // 123
                            ip = string[1].replace("ip=","").replace(",",""); // 654321
                         }
                       }
                  }            
              } catch (Exception e) {
                System.out.println("Excepcion leyendo fichero " + fc.fichero + ": " + e);
            }
          /*   listaR.add(ip.replace("ip=",""));
                            listaR.add(base.replace("base=","").replace(",",""));*/
      }else{
      }
      datos[0]=ip;
      datos[1]=base;
      
     return datos;
 }
 
  public String nombre_base(){
      if (fc.getFichero() != null) {
            try {
                try (FileReader fr = new FileReader(fc.getFichero())) {
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    while ((linea = br.readLine()) != null) {
                          if(!linea.trim().equals("") && linea.contains(nombre)){      
                           String[] string = linea.split(",");
                            base = string[0]; // 123
                            ip = string[1]; // 654321
                            nombre=string[2];
                         }
                    }
                  }            
              } catch (Exception e) {
                System.out.println("Excepcion leyendo fichero " + fc.fichero + ": " + e);
            }
      }else{
      }
       
     return base;
 }
  
  
    
    
}
