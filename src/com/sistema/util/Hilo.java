/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.util;

import com.saivent.util.ImageTable;
import com.saivent.view.FRecargas;
import com.sistema.modelo.ProductoDTO;
import com.taecel.conexionservicio.metodosHTTP;
import com.taecel.modelo.ProductsDTO;
import com.taecel.modelo.carriersModelo;
import com.taecel.modelo.productosTaecelClienteModelo;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wilmer
 */
public class Hilo implements Runnable {

    FRecargas fr = new FRecargas();
    JProgressBar barra = new JProgressBar(0, 500);
    ProductsDTO productos = new ProductsDTO();
    int tot = 0;

    public Hilo(JProgressBar barra, int tot) {
        this.barra = barra;
        this.tot = tot;
        productos = new metodosHTTP().getProducts();    
    }

    @Override
    public void run() {

        DefaultTableModel dtm = new DefaultTableModel();
        String titulos[] = {"COMPAÑIA", "IDENTIFICADOR"};

        try {
            fr.jTable1.setDefaultRenderer(Object.class, new ImageTable());
            dtm.setColumnIdentifiers(titulos);
            System.out.println("siiii:"+productos.getMessage());
            if (fr.cbProductos.getSelectedItem().toString().toUpperCase().contains("AIRE")) {
                ArrayList<carriersModelo> carriers = productos.getData().getCarriers();
                for (int i = 0; i < carriers.size(); i++) {
                    Thread.sleep(10);
                    System.out.println("Esta pasando");
                    JLabel label = new JLabel();
                    Object[] fila = new Object[2];
                    carriersModelo carrier = carriers.get(i);
                    if (carrier.getCategoria().toLowerCase().contains("tiempo aire") && !carrier.getID().equals("264") && !carrier.getID().equals("227") && !carrier.getID().equals("225") && !carrier.getID().equals("243")) {
                        System.setProperty("http.agent", "Chrome");
                        URL url = null;
                        try {
                            url = new URL(carrier.getLogotipo());
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Image image = null;
                        try {
                            image = ImageIO.read(url);
                        } catch (IOException ex) {
                            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        label.setIcon(new ImageIcon(image));
                        fila[0] = carrier.getNombre();
                        fila[1] = label;
                        fr.jTable1.setRowHeight(110);
                        dtm.addRow(fila);
                        
                        
                    }
                     barra.setValue(i);
                }
            }
            fr.jTable1.setModel(dtm);
            /*for (int i = 0; i <= tot; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                barra.setValue(i);
                if (barra.getValue() == tot) {
                    barra.setVisible(false);
                }
            }*/
        }
        catch(Exception e){
            
        }

    }
}
