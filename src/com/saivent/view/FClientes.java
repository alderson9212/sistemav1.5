/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.view;

import com.saivent.util.MetodosValidar;
import com.sistema.controller.ClientesController;
import com.sistema.controller.EstadosController;
import com.sistema.controller.LocalidadesController;
import com.sistema.controller.MunicipiosController;
import com.sistema.modelo.ClienteDTO;
import com.sistema.modelo.ColoniaDTO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sistemas
 */
public class FClientes extends javax.swing.JInternalFrame {
    
    ClientesController controllerClientes = new ClientesController();
    
    public FClientes() {
        initComponents();
        ListarClientes("");
        desabilitarC();
        llenarCombos();
        diseñoVentana();
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

        jPanel1 = new javax.swing.JPanel();
        lblcodigo = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        lblgenero = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        cbcol = new javax.swing.JComboBox<>();
        cbmuni = new javax.swing.JComboBox<>();
        cbestado = new javax.swing.JComboBox<>();
        cbgenero = new javax.swing.JComboBox<>();
        txtAppaterno = new javax.swing.JTextField();
        txtApmaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.green);
        setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        setName("miframe"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 111, 111));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblcodigo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblcodigo.setForeground(new java.awt.Color(254, 254, 254));
        lblcodigo.setText("CODIGO");

        lblnombre.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(254, 254, 254));
        lblnombre.setText("NOMBRE");

        lbldireccion.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lbldireccion.setForeground(new java.awt.Color(254, 254, 254));
        lbldireccion.setText("DIRECCION");

        lbltelefono.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lbltelefono.setForeground(new java.awt.Color(254, 254, 254));
        lbltelefono.setText("TELEFONO");

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
            }
        });

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 111, 111));

        btnaceptar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptar.setText("ACEPTAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });

        lblgenero.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblgenero.setForeground(new java.awt.Color(254, 254, 254));
        lblgenero.setText("GENERO");

        lblemail.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblemail.setForeground(new java.awt.Color(254, 254, 254));
        lblemail.setText("EMAIL");

        cbcol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbcol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcolMouseClicked(evt);
            }
        });
        cbcol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcolActionPerformed(evt);
            }
        });

        cbmuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbgenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA", "HOMBRE", "MUJER" }));

        txtAppaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAppaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAppaternoKeyTyped(evt);
            }
        });

        txtApmaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApmaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApmaternoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblgenero)
                    .addComponent(lblemail)
                    .addComponent(lbltelefono)
                    .addComponent(lbldireccion)
                    .addComponent(lblnombre)
                    .addComponent(lblcodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtnombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApmaterno))
                    .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbgenero, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbcol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbmuni, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblcodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAppaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblnombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbldireccion)
                            .addComponent(cbmuni, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(cbcol)
                            .addComponent(cbestado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbgenero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblgenero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblemail)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 111, 111));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnsalir.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("CERRAR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        tbclientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbclientes);

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(253, 251, 251));
        jLabel8.setText("BUSCAR");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        btnnuevo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnmodificar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificar.setText("MODIFICAR");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btnnuevo)
                    .addComponent(btnmodificar)
                    .addComponent(btneliminar))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        this.btnnuevo.setEnabled(false);
        habilitarC();
        generarId();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        desabilitarC();
        limpiarC();
        this.btnnuevo.setEnabled(true);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        if (valEntradas() == true) {
            guardar();
        }
        

    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        this.txtnombre.setText(txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
//     MetodosValidar.soloNumeros(txttelefono,13);    
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tbclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMouseClicked
        EventMouseClick();
        
    }//GEN-LAST:event_tbclientesMouseClicked

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        if(valEntradas()){
            modificar();
        }
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
       eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        // MetodosValidar.soloLetrasNumeros(txtnombre, 100);
    }//GEN-LAST:event_txtnombreKeyTyped

    private void cbcolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcolMouseClicked

    }//GEN-LAST:event_cbcolMouseClicked

    private void cbcolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcolActionPerformed
     
    }//GEN-LAST:event_cbcolActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
        ListarClientes(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            if (txttelefono.getText().length() < 10) {
                evt.consume();
            }
        }       // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
//  MetodosValidar.soloNumeros(txtcodigo,6);
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'A' || car > 'Z') && (car < 'a' || car > 'z') && (car < '@' || car > '@') && (car < '_' || car > '_') && (car < '.' || car > '.')) {
            evt.consume();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailKeyTyped

    private void txtAppaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAppaternoKeyReleased
        txtAppaterno.setText(txtAppaterno.getText().toUpperCase());
    }//GEN-LAST:event_txtAppaternoKeyReleased

    private void txtAppaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAppaternoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAppaternoKeyTyped

    private void txtApmaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApmaternoKeyReleased
        txtApmaterno.setText(txtApmaterno.getText().toUpperCase());
    }//GEN-LAST:event_txtApmaternoKeyReleased

    private void txtApmaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApmaternoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApmaternoKeyTyped
    
    public void generarId() {
        txtcodigo.setText(String.valueOf(controllerClientes.generarSecuenciaId()));
    }
    
    private void ListarClientes(String nombre) {
        String[] titulos = {"CODIGO", "NOMBRE COMPLETO", "DIRECCION", "CORREO", "TELEFONO", "GENERO"};
        DefaultTableModel dtm = new DefaultTableModel(null, titulos);
        try {
            Object o[] = null;
            List<ClienteDTO> listC = controllerClientes.findAll(nombre);
            for (int i = 0; i < listC.size(); i++) {
                dtm.addRow(o);
                dtm.setValueAt(listC.get(i).getIdcliente(), i, 0);
                dtm.setValueAt(listC.get(i).getNombre(), i, 1);
                dtm.setValueAt(listC.get(i).getDomicilio(), i, 2);
                dtm.setValueAt(listC.get(i).getMail(), i, 3);
                dtm.setValueAt(listC.get(i).getTelefono(), i, 4);
                dtm.setValueAt(listC.get(i).getGenero(), i, 5);
                
            }
            tbclientes.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void guardar() {
        ClienteDTO cliente = new ClienteDTO();
        try {
            cliente.setIdcliente(Integer.parseInt(txtcodigo.getText()));
            cliente.setNombre(txtnombre.getText());
            cliente.setDomicilio(cbcol.getSelectedItem().toString() + "," + cbmuni.getSelectedItem().toString() + "," + cbestado.getSelectedItem().toString());
            cliente.setMail(txtemail.getText());
            if(!txttelefono.getText().equals("")){
               cliente.setTelefono(txttelefono.getText());
            } else{
                cliente.setTelefono("0");
            }             
            cliente.setGenero(cbgenero.getSelectedItem().toString());
            ColoniaDTO colonia = new LocalidadesController().coloniaByNombre(cbcol.getSelectedItem().toString());
            cliente.setIdcolonia(colonia.getIdcolonia());
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Guardar registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                boolean registro = controllerClientes.save(cliente);
                if (registro) {
                    new MetodosValidar().ok();
                    limpiarC();
                    desabilitarC();
                    ListarClientes("");
                } else {
                    new MetodosValidar().error();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al guardar estado:" + e.getMessage());
        }
        
    }
    
    public void modificar() {
        ClienteDTO cliente = new ClienteDTO();
        try {
            cliente.setIdcliente(Integer.parseInt(txtcodigo.getText()));
            cliente.setNombre(txtnombre.getText());
            cliente.setDomicilio(cbcol.getSelectedItem().toString() + "," + cbmuni.getSelectedItem().toString() + "," + cbestado.getSelectedItem().toString());
            cliente.setMail(txtemail.getText());
            if(!txttelefono.getText().equals("")){
               cliente.setTelefono(txttelefono.getText());
            } else{
                cliente.setTelefono("0");
            }  
            cliente.setGenero(cbgenero.getSelectedItem().toString());
            ColoniaDTO colonia = new LocalidadesController().coloniaByNombre(cbcol.getSelectedItem().toString());
            cliente.setIdcolonia(colonia.getIdcolonia());
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Modificar registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                boolean registro = controllerClientes.update(cliente);
                if (registro) {
                    new MetodosValidar().ok_modificar();
                    limpiarC();
                    desabilitarC();
                    ListarClientes("");
                } else {
                    new MetodosValidar().error_modificar();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al moficar registros:" + e.getMessage());
        }
    }
    
    public void eliminar() {
        try {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Eliminar registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                boolean registro = controllerClientes.delete(Integer.parseInt(txtcodigo.getText()));
                if (registro) {
                    new MetodosValidar().ok_eliminar();
                    limpiarC();
                    desabilitarC();
                    ListarClientes("");
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar registros");
        }
    }
    
    public void llenarCombos() {
        
        for (int i = 0; i < new LocalidadesController().coloniasAll("").size(); i++) {
            cbcol.addItem(new LocalidadesController().coloniasAll("").get(i).getNombre());
        }
        
        for (int i = 0; i < new MunicipiosController().municipiosAll("").size(); i++) {
            cbmuni.addItem(new MunicipiosController().municipiosAll("").get(i).getNombre());
        }
        
        for (int i = 0; i < new EstadosController().estadosAll("").size(); i++) {
            cbestado.addItem(new EstadosController().estadosAll("").get(i).getNombre());
        }
        
    }
    
    
    public void particionarDom(String cadena) {
        String c, m, e;
        System.out.println("cadenaaaaaaaaaaaa:" + cadena);
        String[] Part = cadena.split(",");
        for (int i = 0; i < Part.length; i++) {
            c = Part[0];
            m = Part[1];
            e = Part[2];
            cbcol.setSelectedItem(c);
            cbmuni.setSelectedItem(m);
            cbestado.setSelectedItem(e);
        }
    }
    
    public void particionarNombre(String cadena) {
        String n, ap, am;
        int filaS = tbclientes.getSelectedRow();
        String[] Part = cadena.trim().split(" ");
        for (int i = 0; i < Part.length; i++) {
            System.out.println("entroooooooooooooooooooooooooooo");
            n = Part[0];
            ap = Part[1];
            am = Part[2];
            txtnombre.setText(n);
            txtAppaterno.setText(ap);
            txtApmaterno.setText(am);
        }
    }
    
    public void EventMouseClick() {
        int filas = tbclientes.getSelectedRow();
        if (filas >= 0) {
            
            this.habilitarC();
            this.btnaceptar.setEnabled(false);
            this.btnmodificar.setEnabled(true);
            this.btneliminar.setEnabled(true);
            this.btnnuevo.setEnabled(false);
            this.txtcodigo.setText(tbclientes.getValueAt(filas, 0).toString());
            particionarNombre(tbclientes.getValueAt(filas, 1).toString());            
            particionarDom(tbclientes.getValueAt(filas, 2).toString());
            txttelefono.setText(tbclientes.getValueAt(filas, 4).toString()); 
            if(tbclientes.getValueAt(filas, 5) != null){
                cbgenero.setSelectedItem(tbclientes.getValueAt(filas, 5).toString());
            }else{
                cbgenero.setSelectedIndex(0);
            }            
            if (!tbclientes.getValueAt(filas, 3).toString().equals("")) {                
                txtemail.setText(tbclientes.getValueAt(filas, 3).toString());
            } else {
                txtemail.setText("");
            }
            
        }
    }//EventoDeMouseClicked

    public void desabilitarC() {
        //this.lblcodigo.setEnabled(false);
        //this.lblnombre.setEnabled(false);
        //this.lbldireccion.setEnabled(false);
        //this.lbltelefono.setEnabled(false);
        //lblgenero.setEnabled(false);
        //lblemail.setEnabled(false);
        this.btnaceptar.setEnabled(false);
        this.btncancelar.setEnabled(false);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        this.txtcodigo.setEnabled(false);
        this.txtnombre.setEnabled(false);
        this.txttelefono.setEnabled(false);
        cbgenero.setEnabled(false);
        txtemail.setEnabled(false);
        cbcol.setEnabled(false);
        cbmuni.setEnabled(false);
        cbestado.setEnabled(false);
        txtApmaterno.setEnabled(false);
        txtAppaterno.setEnabled(false);
        
    }
    
    public void habilitarC() {
        this.lblcodigo.setEnabled(true);
        this.lblnombre.setEnabled(true);
        this.lbldireccion.setEnabled(true);
        this.lbltelefono.setEnabled(true);
        lblgenero.setEnabled(true);
        lblemail.setEnabled(true);
        this.btnaceptar.setEnabled(true);
        this.btncancelar.setEnabled(true);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        this.txtcodigo.setEnabled(true);
        this.txtnombre.setEnabled(true);
        this.txttelefono.setEnabled(true);
        cbgenero.setEnabled(true);
        txtemail.setEnabled(true);
        cbcol.setEnabled(true);
        cbmuni.setEnabled(true);
        cbestado.setEnabled(true);
        txtAppaterno.setEnabled(true);
        txtApmaterno.setEnabled(true);
        txttelefono.setText("0");
        
    }
    
    public void limpiarC() {
//        this.Generarnumeracion();
        this.txtnombre.setText("");
        this.txttelefono.setText("");
        txtcodigo.setText("");
        cbgenero.setSelectedIndex(0);
        txtemail.setText("");
        cbcol.setSelectedIndex(0);
        cbmuni.setSelectedIndex(0);
        cbestado.setSelectedIndex(0);
        txtAppaterno.setText("");
        txtApmaterno.setText("");
    }
    
    public boolean valEntradas() {
        String mensaje = "";
        boolean estado = true;
        if (txtcodigo.getText().isEmpty()) {
            mensaje += "ID NO DEBE ESTAR VACIO \n";
            estado = false;
        }
        if (txtnombre.getText().isEmpty()) {
            mensaje += "NO SE INSERTO UN NOMBRE VALIDO \n";
            estado = false;
        }
        
        if (cbgenero.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO GENERO \n";
            estado = false;
        }
        if (txtemail.getText().isEmpty() == false && valcorreo() == false) {
            mensaje += "VERIFICA DIRECCION DE CORREO @";
            estado = false;
        }
        if (mensaje.length() >= 6) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);
            
        }
        
        return estado;
    }
    
    public boolean valcorreo() {
        boolean correo = false;
        for (int i = 0; i < txtemail.getText().length(); i++) {
            if (txtemail.getText().charAt(i) == '@') {
                correo = true;
            }
        }
        return correo;
    }
    //public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */

    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
 /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FClientes().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbcol;
    private javax.swing.JComboBox<String> cbestado;
    private javax.swing.JComboBox<String> cbgenero;
    private javax.swing.JComboBox<String> cbmuni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblgenero;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtApmaterno;
    private javax.swing.JTextField txtAppaterno;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
