/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.view;

import com.saivent.util.MetodosValidar;
import com.saivent.util.Validaciones;
import com.sistema.controller.ProveedorController;
import com.sistema.modelo.ProveedorDTO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elliot
 */
public class FProveedor extends javax.swing.JInternalFrame {

    ProveedorController proveedorController = new ProveedorController();
    DefaultTableModel dtm = new DefaultTableModel();
    Validaciones val = new Validaciones();

    public FProveedor() {
        initComponents();
        Desabilitar();
        carga_informacion_Prov("");

        Dimension DimensionBarra = null;
        JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        DimensionBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    private void carga_informacion_Prov(String nombre) {
        List<ProveedorDTO> listaProv = null;
        String[] titulos = {"CODIGO", "NOMBRE", "EMAIL", "TELEFONO"};
        dtm = new DefaultTableModel(null, titulos);
        try {
            Object o[] = null;
            listaProv = proveedorController.proveedoresAll(nombre);
            for (int i = 0; i < listaProv.size(); i++) {
                dtm.addRow(o);
                dtm.setValueAt(listaProv.get(i).getIdproveedor(), i, 0);
                dtm.setValueAt(listaProv.get(i).getNombre(), i, 1);
                dtm.setValueAt(listaProv.get(i).getMail(), i, 2);
                dtm.setValueAt(listaProv.get(i).getTelefono(), i, 3);
            }
            tbcontenido.setModel(dtm);

        } catch (Exception e) {
            System.out.println("Error al listar proveedores:" + e.getMessage());
        }
    }

    private List<ProveedorDTO> buscarProv(String nombre) {

        List<ProveedorDTO> lista = null;

        return lista;
    }

    public void Desabilitar() {
        txtcodigo.setEnabled(false);

        txttelefono.setEnabled(false);
        txtemail.setEnabled(false);
        txtEmpresa.setEnabled(false);
        btnaceptar.setEnabled(false);
        btncancelar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        btnnuevo.setEnabled(true);

    }

    public void habField() {
        txtcodigo.setEnabled(true);
        txttelefono.setEnabled(true);
        txtemail.setEnabled(true);
        txtEmpresa.setEnabled(true);
        btnaceptar.setEnabled(true);
        btncancelar.setEnabled(true);

    }

    public void limpiarC() {

        txttelefono.setText("");
        txtemail.setText("");
        txtEmpresa.setText("");
        txtcodigo.setText("");
    }

    public boolean ValEntradas() {
        String msj = "";
        boolean es = true;
        if (this.txtcodigo.getText().isEmpty() == true) {
            msj += "NO SE INSERTO CODIGO \n";
            es = false;
        }
        if (txtEmpresa.getText().isEmpty() == true) {
            msj += "NO SE INSERTO NOMBRE \n";
            es = false;
        }

        if (msj.length() >= 6) {
            JOptionPane.showMessageDialog(null, msj, "", JOptionPane.WARNING_MESSAGE);

        }

        return es;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        j1 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        txtcodigo = new javax.swing.JTextField();
        btncancelar = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        j2 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        j4 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        j5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlLista = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcontenido = new javax.swing.JTable();

        pnlDatos.setBackground(new java.awt.Color(0, 111, 111));
        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        j1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j1.setForeground(new java.awt.Color(254, 254, 254));
        j1.setText("CODIGO");

        btnaceptar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptar.setText("ACEPTAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        txtcodigo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
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

        txtemail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });

        j2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j2.setForeground(new java.awt.Color(254, 254, 254));
        j2.setText("EMPRESA");

        txttelefono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        j4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j4.setForeground(new java.awt.Color(254, 254, 254));
        j4.setText("TELEFONO");

        txtEmpresa.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyReleased(evt);
            }
        });

        j5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j5.setForeground(new java.awt.Color(254, 254, 254));
        j5.setText("EMAIL");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PROVEEDORES");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(j2)
                            .addComponent(j1)
                            .addComponent(j4)
                            .addComponent(j5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnaceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncancelar))
                            .addComponent(txttelefono)
                            .addComponent(txtcodigo)
                            .addComponent(txtemail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmpresa))))
                .addGap(15, 15, 15))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(j2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono)
                    .addComponent(j4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j5))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlLista.setBackground(new java.awt.Color(0, 111, 111));
        pnlLista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnnuevo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
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

        btnmodificar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificar.setText("MODIFICAR");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("CERRAR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("BUSCAR");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        tbcontenido = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbcontenido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbcontenido.setEditingColumn(0);
        tbcontenido.setEditingRow(0);
        tbcontenido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcontenidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbcontenido);

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addComponent(jScrollPane1)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar)))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir))
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnmodificar)
                                .addComponent(btnnuevo)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        ProveedorDTO prov = new ProveedorDTO();
        if (this.ValEntradas() == true) {
            try {
                prov.setIdproveedor(Integer.parseInt(txtcodigo.getText()));
                prov.setNombre(txtEmpresa.getText());
                prov.setMail(txtemail.getText());
                prov.setTelefono(txttelefono.getText());

                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    boolean guardar = proveedorController.save(prov);
                    if (guardar) {
                        new MetodosValidar().ok();
                        carga_informacion_Prov("");
                        limpiarC();
                        Desabilitar();
                    } else {
                        new MetodosValidar().error();
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al registrar proveedor:" + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnaceptarActionPerformed


    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        Desabilitar();
        limpiarC();

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        this.limpiarC();
        habField();
        this.btneliminar.setEnabled(false);
        this.btnmodificar.setEnabled(false);
        this.btnnuevo.setEnabled(false);
        txtcodigo.setText(String.valueOf(proveedorController.generarSecuenciaId()));

    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int fila = tbcontenido.getSelectedRow();

        try {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿ELIMINAR DATOS?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_NO_OPTION) {
                boolean b = proveedorController.delete(Integer.parseInt(txtcodigo.getText()));
                if (b) {
                    Desabilitar();
                    limpiarC();
                    btnmodificar.setEnabled(false);
                    btneliminar.setEnabled(false);
                    carga_informacion_Prov("");
                    new MetodosValidar().ok_eliminar();
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al eliminar:" + ex.getMessage());
        }
    }//GEN-LAST:event_btneliminarActionPerformed
    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        ProveedorDTO prov = new ProveedorDTO();
        if (ValEntradas() == true) {
            try {
                prov.setIdproveedor(Integer.parseInt(txtcodigo.getText()));
                prov.setNombre(txtEmpresa.getText());
                prov.setTelefono(txttelefono.getText());
                prov.setMail(txtemail.getText());
                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    boolean bandera = proveedorController.update(prov);
                    if (bandera) {
                        new MetodosValidar().ok_modificar();
                        carga_informacion_Prov("");
                        limpiarC();
                        Desabilitar();
                    } else {
                        new MetodosValidar().error_modificar();
                    }

                }
            } catch (Exception e) {

            }
        }

    }//GEN-LAST:event_btnmodificarActionPerformed


    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        new MetodosValidar().soloNumeros(txttelefono, 13);
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
//        MetodosValidar.soloNumeros(txttelefono, 14);
    }//GEN-LAST:event_txtemailKeyPressed

    private void txtEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyReleased
        txtEmpresa.setText(this.txtEmpresa.getText().toUpperCase());
    }//GEN-LAST:event_txtEmpresaKeyReleased

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        carga_informacion_Prov(txtbuscar.getText());
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void tbcontenidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcontenidoMouseClicked
        int filaS = tbcontenido.getSelectedRow();
        if (filaS >= 0) {
            habField();
            btneliminar.setEnabled(true);
            btnmodificar.setEnabled(true);
            btnnuevo.setEnabled(false);
            btnaceptar.setEnabled(false);
            txtcodigo.setText(tbcontenido.getValueAt(filaS, 0).toString());
            txtEmpresa.setText(tbcontenido.getValueAt(filaS, 1).toString());
            txttelefono.setText(tbcontenido.getValueAt(filaS, 3).toString());
            txtemail.setText(tbcontenido.getValueAt(filaS, 2).toString());

        }
    }//GEN-LAST:event_tbcontenidoMouseClicked

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
        //MetodosValidar.soloNumeros(txtcodigo, 6);
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'A' || car > 'Z') && (car < 'a' || car > 'z') && (car < '@' || car > '@') && (car < '_' || car > '_') && (car < '.' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtemailKeyTyped

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        this.txtemail.setText(txtemail.getText().toLowerCase());       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailKeyReleased

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped

    }//GEN-LAST:event_txttelefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j4;
    private javax.swing.JLabel j5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JTable tbcontenido;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
