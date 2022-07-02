/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.view;

import com.google.gson.Gson;
import com.saivent.util.Fichero;
import com.saivent.util.ImageTable;
import com.saivent.util.MetodosValidar;
import com.sistema.modelo.SaldoDTOLocal;
import com.sistema.util.Hilo;
import com.sistema.util.configTaecel;
import com.taecel.conexionservicio.conexionhttpLocal;
import com.taecel.conexionservicio.metodosHTTP;
import com.taecel.modelo.ProductsDTO;
import com.taecel.modelo.StatusDTO;
import com.taecel.modelo.TransaccionDTO;
import com.taecel.modelo.carriersModelo;
import com.taecel.modelo.productoModel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wilmer
 */
public class FRecargas extends javax.swing.JInternalFrame {

    String comision = "", vigencia = "", nota = "", compania = "";
    metodosHTTP metodos = new metodosHTTP();
    Gson gson = new Gson();
    ProductsDTO productos = null;//new ProductsDTO();
    Timer time;
    ActionListener ac;
    int x = 0;
    JDialog dialogov;
    conexionhttpLocal locaService = new conexionhttpLocal();

    public FRecargas() {
        initComponents();
        diseñoVentana();
        leerSaldo();
       btnRecargar.setEnabled(false);

    }

    public void diseñoVentana() {
        Dimension DimensionBarra = null;
        JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        DimensionBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdVender = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jlVenta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbMontoCompa = new javax.swing.JComboBox<>();
        pnlVentas = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtConfirmarNumero = new javax.swing.JTextField();
        lblCostoProducto = new javax.swing.JLabel();
        lblComisionServicio = new javax.swing.JLabel();
        lblVigencia = new javax.swing.JLabel();
        lblNota = new javax.swing.JLabel();
        jlabelCostoProducto = new javax.swing.JLabel();
        jlabelComisionServicio = new javax.swing.JLabel();
        jlabelVigencia = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnRecargar = new javax.swing.JButton();
        lblVigencia1 = new javax.swing.JLabel();
        jLabelCodProducto = new javax.swing.JLabel();
        loading = new javax.swing.JDialog();
        jProgressBar1 = new javax.swing.JProgressBar();
        porcentaje = new javax.swing.JLabel();
        keys = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        key = new javax.swing.JTextField();
        secretKey = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        saveSecret = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbProductos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnsalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        totalSaldoRecarga = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblIdUser = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(0, 111, 111));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlVenta.setBackground(new java.awt.Color(0, 111, 111));
        jlVenta.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jlVenta.setForeground(new java.awt.Color(255, 255, 255));
        jlVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto ->");

        cbMontoCompa.setFont(new java.awt.Font("Noto Sans", 3, 12)); // NOI18N
        cbMontoCompa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        cbMontoCompa.setToolTipText("");
        cbMontoCompa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMontoCompaActionPerformed(evt);
            }
        });

        pnlVentas.setBackground(new java.awt.Color(0, 111, 111));
        pnlVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NUMERO:");

        txtNumero.setToolTipText("ingresa tu numero\n");
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CONFIRMA:");

        txtConfirmarNumero.setToolTipText("confirmar tu numero\n");
        txtConfirmarNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarNumeroKeyReleased(evt);
            }
        });

        lblCostoProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoProducto.setText("COSTO PRODUCTO:");

        lblComisionServicio.setForeground(new java.awt.Color(255, 255, 255));
        lblComisionServicio.setText("COMISION:");

        lblVigencia.setForeground(new java.awt.Color(255, 255, 255));
        lblVigencia.setText("VIGENCIA:");

        lblNota.setForeground(new java.awt.Color(255, 255, 255));
        lblNota.setText("Nota :");

        jlabelCostoProducto.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlabelCostoProducto.setForeground(new java.awt.Color(255, 255, 255));
        jlabelCostoProducto.setText("jLabel6");

        jlabelComisionServicio.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlabelComisionServicio.setForeground(new java.awt.Color(255, 255, 255));
        jlabelComisionServicio.setText("jLabel6");

        jlabelVigencia.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlabelVigencia.setForeground(new java.awt.Color(255, 255, 255));
        jlabelVigencia.setText("jLabel6");

        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        btnRecargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/OK.png"))); // NOI18N
        btnRecargar.setText("RECARGAR");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });

        lblVigencia1.setForeground(new java.awt.Color(255, 255, 255));
        lblVigencia1.setText("NOMBRE PRODUCTO:");

        jLabelCodProducto.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelCodProducto.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCodProducto.setText("jLabel6");

        javax.swing.GroupLayout pnlVentasLayout = new javax.swing.GroupLayout(pnlVentas);
        pnlVentas.setLayout(pnlVentasLayout);
        pnlVentasLayout.setHorizontalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                .addComponent(lblVigencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                .addComponent(lblCostoProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelCostoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                .addComponent(lblComisionServicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlabelComisionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                .addComponent(lblVigencia1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(17, 17, 17)
                        .addComponent(txtNumero))
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(7, 7, 7)
                        .addComponent(txtConfirmarNumero))
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addComponent(lblNota, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVentasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRecargar)
                .addGap(67, 67, 67))
        );
        pnlVentasLayout.setVerticalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel5))
                    .addComponent(txtConfirmarNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelCostoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComisionServicio)
                    .addComponent(jlabelComisionServicio))
                .addGap(4, 4, 4)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVigencia)
                    .addComponent(jlabelVigencia))
                .addGap(4, 4, 4)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVigencia1)
                    .addComponent(jLabelCodProducto))
                .addGap(14, 14, 14)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblNota))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRecargar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMontoCompa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMontoCompa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdVenderLayout = new javax.swing.GroupLayout(jdVender.getContentPane());
        jdVender.getContentPane().setLayout(jdVenderLayout);
        jdVenderLayout.setHorizontalGroup(
            jdVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdVenderLayout.setVerticalGroup(
            jdVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        porcentaje.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        porcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        porcentaje.setText("0%");

        javax.swing.GroupLayout loadingLayout = new javax.swing.GroupLayout(loading.getContentPane());
        loading.getContentPane().setLayout(loadingLayout);
        loadingLayout.setHorizontalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(porcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        loadingLayout.setVerticalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setText("Key:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setText("secretKey:");

        saveSecret.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save.png"))); // NOI18N
        saveSecret.setText("Guardar");
        saveSecret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSecretActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout keysLayout = new javax.swing.GroupLayout(keys.getContentPane());
        keys.getContentPane().setLayout(keysLayout);
        keysLayout.setHorizontalGroup(
            keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(key)
                    .addComponent(secretKey, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keysLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveSecret)
                .addGap(143, 143, 143))
        );
        keysLayout.setVerticalGroup(
            keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keysLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(secretKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveSecret)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 111, 111));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("SELECCIONE:");

        cbProductos.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        cbProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiempo Aire", "Paquete" }));
        cbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductosActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Noto Sans", 3, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COMPAÑIA", "IDENTIFICADOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEditingRow(0);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnsalir.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("CERRAR");
        btnsalir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnsalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("BOLSA TIEMPO AIRE : $");

        totalSaldoRecarga.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        totalSaldoRecarga.setForeground(new java.awt.Color(254, 254, 254));
        totalSaldoRecarga.setText("0");

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("ID USUARIO: ");

        lblIdUser.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblIdUser.setForeground(new java.awt.Color(254, 254, 254));
        lblIdUser.setText("00001");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalSaldoRecarga)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdUser)
                .addContainerGap(116, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalSaldoRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductosActionPerformed
        jTable1.removeAll();
        if (cbProductos.getSelectedIndex() == 0) {
            loading.setSize(430, 90);
            loading.setLocationRelativeTo(null);
            loading.setVisible(true);
            DefaultTableModel dtm = new DefaultTableModel();
            String titulos[] = {"COMPAÑIA", "IDENTIFICADOR"};
            ac = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (productos == null && x <= 2) {
                        x = 10;
                        productos = new metodosHTTP().getProducts();
                    }
                    if (productos != null && x < 30) {
                        x = 98;
                    }
                    jProgressBar1.setValue(x++);
                    porcentaje.setText(String.valueOf(jProgressBar1.getValue()) + "%");
                    jTable1.setDefaultRenderer(Object.class, new ImageTable());
                    dtm.setColumnIdentifiers(titulos);
                    if (cbProductos.getSelectedItem().toString().toUpperCase().contains("AIRE")) {
                        ArrayList<carriersModelo> carriers = productos.getData().getCarriers();
                        for (int i = 0; i < carriers.size(); i++) {
                            JLabel label = new JLabel();
                            Object[] fila = new Object[2];
                            carriersModelo carrier = carriers.get(i);
                            if (carrier.getCategoria().toLowerCase().contains("tiempo aire") && !carrier.getID().equals("264") && !carrier.getID().equals("227") && !carrier.getID().equals("225") && !carrier.getID().equals("243")) {
                                try {
                                    System.setProperty("http.agent", "Chrome");
                                    URL url = new URL(carrier.getLogotipo());
                                    Image image;
                                    try {
                                        image = ImageIO.read(url);
                                        label.setIcon(new ImageIcon(image));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FRecargas.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    fila[0] = carrier.getNombre();
                                    fila[1] = label;
                                    jTable1.setRowHeight(110);
                                    dtm.addRow(fila);
                                } catch (Exception ex) {
                                    System.out.println("Error al cargar imagen:" + ex.getMessage());
                                }
                            }
                        }
                        if (jProgressBar1.getValue() == 100) {
                            time.stop();
                            loading.dispose();
                            jTable1.setModel(dtm);
                        }
                    }
                    // 

                }

            };

            time = new Timer(100, ac);
            time.start();
        }///Aqui termina productos tiempo aire
        else if (cbProductos.getSelectedIndex() == 1) {

            loading.setSize(430, 90);
            loading.setLocationRelativeTo(null);
            loading.setVisible(true);
            DefaultTableModel dtm = new DefaultTableModel();
            String titulos[] = {"COMPAÑIA", "IDENTIFICADOR"};
            ac = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (productos == null && x <= 2) {
                        x = 10;
                        productos = new metodosHTTP().getProducts();
                    }
                    if (productos != null && x < 30) {
                        x = 98;
                    }
                    jProgressBar1.setValue(x++);
                    porcentaje.setText(String.valueOf(jProgressBar1.getValue()) + "%");
                    jTable1.setDefaultRenderer(Object.class, new ImageTable());
                    dtm.setColumnIdentifiers(titulos);
                    if (cbProductos.getSelectedItem().toString().toUpperCase().contains("PAQUET")) {
                        ArrayList<carriersModelo> carriers = productos.getData().getCarriers();
                        for (int i = 0; i < carriers.size(); i++) {
                            JLabel label = new JLabel();
                            Object[] fila = new Object[2];
                            carriersModelo carrier = carriers.get(i);
                            if (carrier.getCategoria().toLowerCase().contains("paque")) {
                                try {
                                    System.setProperty("http.agent", "Chrome");
                                    URL url = new URL(carrier.getLogotipo());
                                    Image image;
                                    try {
                                        image = ImageIO.read(url);
                                        label.setIcon(new ImageIcon(image));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FRecargas.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    fila[0] = carrier.getNombre();
                                    fila[1] = label;
                                    jTable1.setRowHeight(110);
                                    dtm.addRow(fila);
                                } catch (Exception ex) {
                                    System.out.println("Error al cargar imagen:" + ex.getMessage());
                                }
                            }
                        }
                        if (jProgressBar1.getValue() == 100) {
                            time.stop();
                            loading.dispose();
                            jTable1.setModel(dtm);
                        }
                    }
                    // 

                }

            };

            time = new Timer(100, ac);
            time.start();
        }
        leerSaldo();
    }//GEN-LAST:event_cbProductosActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        try {
            llenarComboProductos(jTable1.getValueAt(fila, 0).toString(), cbProductos.getSelectedItem().toString().toUpperCase());
            jdVender.setTitle("DETALLES RECARGA");
            jdVender.setSize(350, 428);
            jdVender.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Message.png")).getImage());
            jdVender.setLocationRelativeTo(null);
            ///Border bordejpanel = new TitledBorder(new EtchedBorder(), jTable1.getValueAt(fila, 0).toString());
            //pnlVentas.setBorder(bordejpanel);
            jlVenta.setText(cbProductos.getSelectedItem() + "," + jTable1.getValueAt(fila, 0).toString());
            jdVender.setVisible(true);
            jLabelCodProducto.setText(cbMontoCompa.getSelectedItem().toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cbMontoCompaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMontoCompaActionPerformed
        if (cbMontoCompa.getSelectedIndex() > 0) {
            btnRecargar.setEnabled(true);
            try {
                String[] cadenaServ = jlVenta.getText().split(",");
                setDetallesTA(cadenaServ[1].toUpperCase(), cadenaServ[0].toUpperCase());
            } catch (Exception e) {
                System.out.println("Error al obtener desc:" + e.getMessage());
            }
        }else{
             btnRecargar.setEnabled(false);
        }


    }//GEN-LAST:event_cbMontoCompaActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        try {

            if (!txtNumero.getText().equals("") && !txtConfirmarNumero.equals("") && !jLabelCodProducto.getText().equals("")) {
                if (txtNumero.getText().length() >= 10 || txtConfirmarNumero.getText().length() >= 10) {
                    if (!txtConfirmarNumero.getText().equals(txtNumero.getText())) {

                        JOptionPane.showMessageDialog(null, "LOS NUMEROS NO COINCIDEN", "", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("" + jLabelCodProducto.getText() + "," + txtConfirmarNumero.getText() + "," + cbMontoCompa.getSelectedItem().toString());
                        TransaccionDTO transaction = metodos.getTransaccion(jLabelCodProducto.getText(), txtConfirmarNumero.getText(), cbMontoCompa.getSelectedItem().toString());

                        StatusDTO estatus = metodos.getStatus(transaction.getData().getTransID());
                        if (transaction.isSuccess()) {
                            if (estatus.isSuccess()) {
                                JOptionPane.showMessageDialog(null, "¡¡¡" + estatus.getMessage() + "!!!" + "\n"
                                        + "Folio:" + estatus.getData().getFolio() + "\n"
                                        + "Saldo actual:" + saldoActual(Double.parseDouble(cbMontoCompa.getSelectedItem().toString())) + ""
                                        + "Fecha:" + estatus.getData().getFecha());
                                dialogov.setVisible(false);
                                txtNumero.setText("");
                                txtConfirmarNumero.setText("");
                                cbMontoCompa.setSelectedIndex(0);
                                leerSaldo();
                            } else {
                                System.out.println("Estatus:");
                                txtNumero.setText("" + estatus);
                                txtConfirmarNumero.setText("");
                                cbMontoCompa.setSelectedIndex(0);
                                dialogov.setVisible(false);
                                txtNumero.setText("");
                                txtConfirmarNumero.setText("");
                                cbMontoCompa.setSelectedIndex(0);
                                JOptionPane.showMessageDialog(null, estatus.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            txtNumero.setText("");
                            txtConfirmarNumero.setText("");
                            cbMontoCompa.setSelectedIndex(0);
                            dialogov.setVisible(false);
                            JOptionPane.showMessageDialog(null, transaction.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "LOS NUMEROS DEBEN SER 10 DIGITOS", "", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "INGRESE DATOS VALIDOS", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al enviar recarga:" + e.getMessage());
        }


    }//GEN-LAST:event_btnRecargarActionPerformed

    private void txtConfirmarNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarNumeroKeyReleased
        new MetodosValidar().soloNumeros(txtConfirmarNumero, 10);
    }//GEN-LAST:event_txtConfirmarNumeroKeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        new MetodosValidar().soloNumeros(txtNumero, 10);
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void saveSecretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSecretActionPerformed
        guardarKeys(key.getText(), secretKey.getText());

    }//GEN-LAST:event_saveSecretActionPerformed

    public void llenarComboProductos(String compa, String serv) {
        cbMontoCompa.removeAllItems();
        limpiarControlesRecargar();
        cbMontoCompa.addItem("SELECCIONE");
        try {
            ProductsDTO prod = productos;// metodos.getProducts();
            ArrayList<productoModel> modelosProducto = prod.getData().getProductos();
            for (int i = 0; i < modelosProducto.size(); i++) {
                productoModel producto = modelosProducto.get(i);
                if (producto.getBolsaID().equals("1") && producto.getCarrier().equalsIgnoreCase(compa) && producto.getCategoria().toUpperCase().contains(serv)) {
                    cbMontoCompa.addItem(producto.getMonto().toUpperCase());
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar montos:" + e.getMessage());
        }

    }

    public void setDetallesTA(String compa, String tipa) {
        try {
            ProductsDTO prod = productos;//metodos.getProducts();
            ArrayList<productoModel> modelosProducto = prod.getData().getProductos();
            for (int i = 0; i < modelosProducto.size(); i++) {
                productoModel producto = modelosProducto.get(i);
                if (producto.getBolsaID().equals("1") && producto.getCarrier().toUpperCase().contains(compa) && producto.getCategoria().toUpperCase().contains(tipa) && producto.getMonto().equals(cbMontoCompa.getSelectedItem().toString())) {
                    jlabelCostoProducto.setText("$" + producto.getMonto() + " pesos.");
                    jlabelVigencia.setText(producto.getVigencia());
                    jlabelComisionServicio.setText("$0.00 pesos.");
                    jTextArea1.setText(producto.getDescripcion());
                    jLabelCodProducto.setText(producto.getCodigo());
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar montos Detalles:" + e.getMessage());
        }
    }

    public void showBarProgres(boolean bandera) {
        try {
            JDialog dialogov = new JDialog(loading, "DATOS RECARGA", true);
            dialogov.add(loading.getContentPane());
            dialogov.setSize(480, 140);
            dialogov.setLocationRelativeTo(null);
            dialogov.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Message.png")).getImage());
            ///Border bordejpanel = new TitledBorder(new EtchedBorder(), jTable1.getValueAt(fila, 0).toString());
            //pnlVentas.setBorder(bordejpanel);

            dialogov.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void limpiarControlesRecargar() {
        jlabelCostoProducto.setText("");
        jlabelComisionServicio.setText("");
        jlabelVigencia.setText("");
        jTextArea1.setText("");
        jLabelCodProducto.setText("");
    }

    public void guardarKeys(String key, String secretKey) {
        String raiz = System.getProperty("user.home");
        String separa = System.getProperty("file.separator");

        try {
            String ruta = raiz + separa + ".red1";
            String ckey = "key=" + key + "\n"
                    + "nip=" + secretKey;

            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ckey);
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar claves:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void leerSaldo() {
        SaldoDTOLocal modeloSaldo = locaService.consultarSaldo(Integer.parseInt(lblIdUser.getText().trim()));
        totalSaldoRecarga.setText(String.valueOf(modeloSaldo.getTotal()));
    }

    public double saldoActual(double vendido) {
        int iduser = Integer.parseInt(lblIdUser.getText());
        double total = Double.parseDouble(totalSaldoRecarga.getText()) - vendido;
        try {
            SaldoDTOLocal saldoLocal = locaService.guardarSaldo(iduser, total);
            total = saldoLocal.getTotal();
        } catch (Exception e) {
        }
        return total;
    }

    /*public void jDKey() {
        String raiz = System.getProperty("user.home");
        String separa = System.getProperty("file.separator");

        try {
            System.out.println("try");
            configTaecel confuracion = new configTaecel();
            System.out.println("CONFIGURATION:"+confuracion.bandera);
            if(confuracion.bandera){
            if (confuracion.obtenerTxt() == null) {
                if(confuracion.getKey().equals("") && confuracion.getNip().equals("")){                    
                jdKey = new JDialog(keys, "KEYS", true);
                jdKey.add(keys.getContentPane());
                jdKey.setSize(410, 150);
                jdKey.setLocationRelativeTo(null);
                jdKey.setVisible(true);
                }
            }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar archivo");
        }

    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbMontoCompa;
    public javax.swing.JComboBox<String> cbProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCodProducto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JDialog jdVender;
    private javax.swing.JLabel jlVenta;
    private javax.swing.JLabel jlabelComisionServicio;
    private javax.swing.JLabel jlabelCostoProducto;
    private javax.swing.JLabel jlabelVigencia;
    private javax.swing.JTextField key;
    private javax.swing.JDialog keys;
    private javax.swing.JLabel lblComisionServicio;
    private javax.swing.JLabel lblCostoProducto;
    private javax.swing.JLabel lblIdUser;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblVigencia;
    private javax.swing.JLabel lblVigencia1;
    private javax.swing.JDialog loading;
    private javax.swing.JPanel pnlVentas;
    private javax.swing.JLabel porcentaje;
    private javax.swing.JButton saveSecret;
    private javax.swing.JTextField secretKey;
    private javax.swing.JLabel totalSaldoRecarga;
    private javax.swing.JTextField txtConfirmarNumero;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
