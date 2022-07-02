package com.saivent.principal;

import com.saivent.reportes.FReporteProductos;
import com.saivent.reportes.FReporteVentas;
import com.saivent.view.FCategorias;
import com.saivent.view.FClientes;
import com.saivent.view.FEstados;
import com.saivent.view.FLocalidades;
import com.saivent.view.FMunicipios;
import com.saivent.view.FProducto;
import com.saivent.view.FProveedor;
import com.saivent.view.FRecargas;

import com.saivent.view.FUnidadesM;
import com.saivent.view.FVentas;
import com.sistema.controller.NegocioController;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Run extends javax.swing.JFrame {

    boolean banderaProcesos = false;
    JDialog jd = new JDialog();

    public Run() {
        initComponents();
        this.setLocationRelativeTo(null);
        DecorarBTN();
        setMenuBar();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/carro_vacio.png")).getImage());
        menuServicio.setEnabled(false);
        itemPagoServicio.setEnabled(false);
        itemUsuarios.setEnabled(false);
    }

    int contBtn1 = 0;
    int contBtn2 = 0;

    public void DecorarBTN() {
        /*btnVertical1.setFocusable(true);
        btnVertical1.setContentAreaFilled(false);
        btnVertical1.setBounds(90, 20, 130, 30);
        btnVertical1.setFocusable(true);

        btnVertical2.setFocusable(true);
        btnVertical2.setContentAreaFilled(false);
        btnVertical2.setBounds(90, 20, 130, 30);
        btnVertical2.setFocusable(true);

        btnVertical3.setFocusable(true);
        btnVertical3.setContentAreaFilled(false);
        btnVertical3.setBounds(90, 20, 130, 30);
        btnVertical3.setFocusable(true);

        btnVertical4.setFocusable(true);
        btnVertical4.setContentAreaFilled(false);
        btnVertical4.setBounds(90, 20, 130, 30);
        btnVertical4.setFocusable(true);*/
    }

    //Cambio de estado
    public void StateChanged(WindowEvent e) {
        // minimized
        if ((e.getNewState() & Run.ICONIFIED) == Run.ICONIFIED) {
            System.out.println("minimized");
        } // maximized
        else if ((e.getNewState() & Run.MAXIMIZED_BOTH) == Run.MAXIMIZED_BOTH) {
            System.out.println("maximized");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDBloquear = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        txtmsj = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        bntBloquear = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmenuOperaciones = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        itemPagoServicio = new javax.swing.JMenuItem();
        jmenuReporte = new javax.swing.JMenu();
        menuServicio = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        itemRProductos = new javax.swing.JMenuItem();
        itemRPVentas = new javax.swing.JMenuItem();
        jmenuMantenimiento = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemUsuarios = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Key.png"))); // NOI18N

        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JDBloquearLayout = new javax.swing.GroupLayout(JDBloquear.getContentPane());
        JDBloquear.getContentPane().setLayout(JDBloquearLayout);
        JDBloquearLayout.setHorizontalGroup(
            JDBloquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JDBloquearLayout.setVerticalGroup(
            JDBloquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        txtmsj.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtmsj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtmsj.setText("Usuario:");

        jPanel2.setBackground(new java.awt.Color(0, 111, 111));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentResized(evt);
            }
        });

        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentResized(evt);
            }
        });

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
        );

        bntBloquear.setFont(new java.awt.Font("Noto Sans", 1, 10)); // NOI18N
        bntBloquear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bloquear.png"))); // NOI18N
        bntBloquear.setText("<html>Boquear <br>sistema<html>");
        bntBloquear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntBloquear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntBloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBloquearActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salida.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntBloquear)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(bntBloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtmsj, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtmsj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jmenuOperaciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jmenuOperaciones.setText("Operaciones");
        jmenuOperaciones.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jMenuItem13.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-comportamiento-de-las-ventas-30.png"))); // NOI18N
        jMenuItem13.setText("Ventas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jmenuOperaciones.add(jMenuItem13);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-pago-de-recarga-30.png"))); // NOI18N
        jMenu5.setText("Servicios");
        jMenu5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Message.png"))); // NOI18N
        jMenuItem7.setText("Recargas electronicas");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        itemPagoServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/How-to.png"))); // NOI18N
        itemPagoServicio.setText("Pago de servicios");
        itemPagoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPagoServicioActionPerformed(evt);
            }
        });
        jMenu5.add(itemPagoServicio);

        jmenuOperaciones.add(jMenu5);

        jMenuBar1.add(jmenuOperaciones);

        jmenuReporte.setText("Reportes");
        jmenuReporte.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        menuServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-factura-30.png"))); // NOI18N
        menuServicio.setText("Servicios");
        menuServicio.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pie chart.png"))); // NOI18N
        jMenuItem5.setText("Ventas");
        menuServicio.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Report.png"))); // NOI18N
        jMenuItem6.setText("Pago de servicios");
        menuServicio.add(jMenuItem6);

        jmenuReporte.add(menuServicio);

        itemRProductos.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        itemRProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/reporte-de-negocios.png"))); // NOI18N
        itemRProductos.setText("Productos");
        itemRProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRProductosActionPerformed(evt);
            }
        });
        jmenuReporte.add(itemRProductos);

        itemRPVentas.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        itemRPVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VENTASS (1).png"))); // NOI18N
        itemRPVentas.setText("Ventas");
        itemRPVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRPVentasActionPerformed(evt);
            }
        });
        jmenuReporte.add(itemRPVentas);

        jMenuBar1.add(jmenuReporte);

        jmenuMantenimiento.setText("Mantenimiento");
        jmenuMantenimiento.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-bienes-de-consumo-de-rápido-movimiento-48.png"))); // NOI18N
        jMenuItem1.setText("Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmenuMantenimiento.add(jMenuItem1);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/User group.png"))); // NOI18N
        jMenu1.setText("Personas");
        jMenu1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N

        itemUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/People.png"))); // NOI18N
        itemUsuarios.setText("Usuarios");
        jMenu1.add(itemUsuarios);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/personal1.png"))); // NOI18N
        jMenuItem4.setText("Clientes");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Delivery.png"))); // NOI18N
        jMenuItem2.setText("Proveedores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jmenuMantenimiento.add(jMenu1);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/TableCat.png"))); // NOI18N
        jMenu7.setText("Catalogos");
        jMenu7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem9.setText("Unidades");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem10.setText("Categorias");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem11.setText("Estados");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem12.setText("Municipios");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem14.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem14.setText("Localidades");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jmenuMantenimiento.add(jMenu7);

        jMenuItem15.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/config32x32.png"))); // NOI18N
        jMenuItem15.setText("Nombre negocio");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jmenuMantenimiento.add(jMenuItem15);

        jMenuBar1.add(jmenuMantenimiento);

        setJMenuBar(jMenuBar1);

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

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        limpiarJDesktopFrame();
        ventas();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void bntBloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBloquearActionPerformed
        bloquear();
    }//GEN-LAST:event_bntBloquearActionPerformed


    private void jPanel3ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentResized

    }//GEN-LAST:event_jPanel3ComponentResized

    private void jDesktopPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentResized
        JInternalFrame[] allFrames = jDesktopPane1.getAllFrames();
        for (JInternalFrame allFrame : allFrames) {
            allFrame.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        }
    }//GEN-LAST:event_jDesktopPane1ComponentResized

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified

    }//GEN-LAST:event_formWindowIconified

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified

    }//GEN-LAST:event_formWindowDeiconified

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        /* jPanel3.setSize(jPanel2.getWidth(), jPanel2.getHeight());
        jDesktopPane1.setSize(jPanel3.getWidth(), jPanel3.getHeight() - jPanel4.getHeight() - 12);
        System.out.println("MEdidad jdesktop:" + jDesktopPane1.getSize().width + "," + jDesktopPane1.getSize().height);*/
    }//GEN-LAST:event_formComponentResized

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        limpiarJDesktopFrame();
        recargas();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        limpiarJDesktopFrame();
        productos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        limpiarJDesktopFrame();
        clientes();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        limpiarJDesktopFrame();
        municipios();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        limpiarJDesktopFrame();
        unidadesm();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        limpiarJDesktopFrame();
        categorias();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        limpiarJDesktopFrame();
        estados();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        limpiarJDesktopFrame();
        localidades();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        limpiarJDesktopFrame();
        proveedores();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void itemPagoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPagoServicioActionPerformed
        limpiarJDesktopFrame();
    }//GEN-LAST:event_itemPagoServicioActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            if (validarDesbloquear()) {
                desbloquear();
                jd.setVisible(false);
                jPasswordField1.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL VERIFICAR SU CONTRASEÑA", "", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void itemRPVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRPVentasActionPerformed
        limpiarJDesktopFrame();
        reporteVentas();
    }//GEN-LAST:event_itemRPVentasActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        NegocioController negocio = new NegocioController();
        try {
            String nombre = JOptionPane.showInputDialog("INGRESA NOMBRE PARA TU NEGOCIO");
            boolean negocio_save = negocio.save(null);
            if (negocio_save) {
                JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR CONTACTE PROVEEDOR", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al guardar nombre del negocio:" + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void itemRProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRProductosActionPerformed
      limpiarJDesktopFrame();
      reporteProductos();
    }//GEN-LAST:event_itemRProductosActionPerformed

    public void controllerProcesos() {
        banderaProcesos = true;
    }

    public void controllerMantenimiento() {
        btnSalir.setVisible(false);
        jmenuOperaciones.setVisible(false);
        jmenuReporte.setText("DEPURACIONES");
        jmenuMantenimiento.setVisible(false);
        jMenuBar1.setVisible(false);
        jMenuItem13.setText("Ejecucion de querys");
        itemRPVentas.setText("Depuracion de municipios");
        banderaProcesos = true;
    }

    //Si presiono recursos -> ventas
    public void ventas() {
        FVentas fv = new FVentas();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
    }

    private void productos() {
        FProducto fv = new FProducto();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    public boolean validarDesbloquear() {
      /*  boolean bandera = false;
        try {
            UsuarioDTO user = new UsuariosController().usuarioByNombre(lblUsuario.getText().trim());
            if (user != null) {
                if (user.getPassword().equals(getMD5(jPasswordField1.getText().trim()))) {
                    bandera = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el usuario:" + e.getMessage());
        }
        return bandera;*/
      return false;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void bloquear() {
        jd = new JDialog(this, "DESBLOQUEAR", false);
        jd.add(JDBloquear.getContentPane());
        jd.setVisible(true);
        JDBloquear.setTitle("INGRESA CONTRASEÑA");
        jd.setSize(380, 75);
        jd.setLocationRelativeTo(null);
        jd.setDefaultCloseOperation(0);

        jMenuBar1.setEnabled(false);
        jmenuOperaciones.setEnabled(false);
        jmenuMantenimiento.setEnabled(false);
        jmenuReporte.setEnabled(false);
        btnSalir.setEnabled(false);
        bntBloquear.setEnabled(false);

        this.setEnabled(false);
    }

    public void desbloquear() {
        jMenuBar1.setEnabled(true);
        jmenuOperaciones.setEnabled(true);
        jmenuMantenimiento.setEnabled(true);
        jmenuReporte.setEnabled(true);
        btnSalir.setEnabled(true);
        bntBloquear.setEnabled(true);
        this.setEnabled(true);
    }

    private void clientes() {
        FClientes fv = new FClientes();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void unidadesm() {
        FUnidadesM fv = new FUnidadesM();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void categorias() {
        FCategorias fv = new FCategorias();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void proveedores() {
        FProveedor fv = new FProveedor();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void estados() {
        FEstados fv = new FEstados();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void municipios() {
        FMunicipios fv = new FMunicipios();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void localidades() {
        FLocalidades fv = new FLocalidades();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
        jMenuBar1.setVisible(true);
    }

    private void recargas() {
        FRecargas fr = new FRecargas();
        fr.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fr.setVisible(true);
        jDesktopPane1.add(fr);
        jMenuBar1.setVisible(true);
    }

    //Diseñar la barra de  menu
    private void setMenuBar() {
        if (this.jMenuBar1 != null) {
            GridBagConstraints gBconstraints = new GridBagConstraints();
            gBconstraints.gridx = 0;
            gBconstraints.gridy = 0;
            gBconstraints.gridwidth = 1;
            gBconstraints.gridheight = 1;
            gBconstraints.fill = GridBagConstraints.HORIZONTAL;
            gBconstraints.weightx = 1;
            //adding this JMenuBar
            this.jPanel4.add(jMenuBar1, gBconstraints);
        }
    }

    public void reporteVentas() {
        FReporteVentas fv = new FReporteVentas();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
    }

    public void reporteProductos() {
        FReporteProductos fv = new FReporteProductos();
        fv.setSize(jDesktopPane1.getWidth(), jDesktopPane1.getHeight());
        fv.setVisible(true);
        jDesktopPane1.add(fv);
    }

    public void limpiarJDesktopFrame() {
        jDesktopPane1.removeAll();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Run().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JDBloquear;
    private javax.swing.JButton bntBloquear;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenuItem itemPagoServicio;
    private javax.swing.JMenuItem itemRPVentas;
    private javax.swing.JMenuItem itemRProductos;
    private javax.swing.JMenuItem itemUsuarios;
    public javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    public javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JMenu jmenuMantenimiento;
    private javax.swing.JMenu jmenuOperaciones;
    private javax.swing.JMenu jmenuReporte;
    public javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menuServicio;
    public javax.swing.JLabel txtmsj;
    // End of variables declaration//GEN-END:variables
}
