/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.view;

import com.saivent.util.MetodosValidar;
import com.sistema.controller.CategoriaController;
import com.sistema.controller.ProductoController;
import com.sistema.controller.ProveedorController;
import com.sistema.controller.UnidadesMedidaController;
import com.sistema.modelo.CategoriaDTO;
import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
import com.sistema.modelo.UnidadesMedidaDTO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JSpinner;

/**
 *
 * @author Sistemas
 */
public class FProducto extends javax.swing.JInternalFrame {

    DefaultComboBoxModel dfmu = new DefaultComboBoxModel();
    DefaultComboBoxModel dfmp = new DefaultComboBoxModel();
    DefaultTableModel dtm = new DefaultTableModel();//Cree instancia de una tabla
    String varPub = "", msjbd = "DATOS NO ENCONTRADOS";
    int idp = 0;
    Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    ProductoController productosController = new ProductoController();
    UnidadesMedidaController unidadController = new UnidadesMedidaController();
    ProveedorController proveedoresController = new ProveedorController();
    CategoriaController categoriaController = new CategoriaController();

    public FProducto() {
        initComponents();
        llenarTbProductos("");
        desabPanCon();
        this.btnnuevo.requestFocusInWindow();
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        padquisicion.setVisible(false);
        llenarCategoria();
        llenarProveedores();
        llenarUnidades();
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

    public void llenarUnidades() {
        UnidadesMedidaController unidad = new UnidadesMedidaController();
        for (int i = 0; i < unidad.unidadesAll("").size(); i++) {
            cbunidad.addItem(unidad.unidadesAll("").get(i).getDescripcion());
        }
    }

    public void llenarProveedores() {
        ProveedorController controllerProv = new ProveedorController();
        for (int i = 0; i < controllerProv.proveedoresAll("").size(); i++) {
            cbproveedor.addItem(controllerProv.proveedoresAll("").get(i).getNombre());
        }
    }

    public void llenarCategoria() {
        CategoriaController controllerCat = new CategoriaController();
        for (int i = 0; i < controllerCat.categoriasAll("").size(); i++) {
            cbcategoria.addItem(controllerCat.categoriasAll("").get(i).getDescripcion());
        }
    }

    public void desabPanCon() {
        jLabel1.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel9.setEnabled(false);
        jLabel10.setEnabled(false);
        jLabel11.setEnabled(false);
        txtcod.setEnabled(false);
        txtnombre.setEnabled(false);
        cbunidad.setEnabled(false);
        sppcliente.setEnabled(false);
        spppublico.setEnabled(false);
        spcantidad.setEnabled(false);
        cbproveedor.setEnabled(false);
        btnaceptar.setEnabled(false);
        btncancelar.setEnabled(false);
        cbcategoria.setEnabled(false);
        jLabelCat.setEnabled(false);
        btnnuevo.setEnabled(true);
    }

    public void habPanCon() {

        jLabel1.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel9.setEnabled(true);
        jLabel10.setEnabled(true);
        jLabel11.setEnabled(true);
        txtcod.setEnabled(true);
        txtnombre.setEnabled(true);
        cbunidad.setEnabled(true);
        sppcliente.setEnabled(true);
        spppublico.setEnabled(true);
        spcantidad.setEnabled(true);
        cbproveedor.setEnabled(true);
        btnaceptar.setEnabled(true);
        btncancelar.setEnabled(true);
        cbcategoria.setEnabled(true);
        jLabelCat.setEnabled(true);
    }

    public void hablitarUISP() {
        ((JSpinner.DefaultEditor) sppcliente.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spppublico.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spcantidad.getEditor()).getTextField().setEditable(false);
    }//MetodoParaHablitarEditorSpiners

    public boolean valEntradas() {
        String mensaje = "";
        boolean estado = true;
        Double pcliente = Double.parseDouble(sppcliente.getValue().toString());
        Double ppublico = Double.parseDouble(spppublico.getValue().toString());
        Double cantidad = Double.parseDouble(spcantidad.getValue().toString());
        if (this.txtcod.getText().isEmpty() == true) {
            mensaje += "NO SE GENERO CODIGO \n";
            estado = false;
        }
        if (txtnombre.getText().isEmpty() == true) {
            mensaje += "NO SE INSERTO NOMBRE \n";
            estado = false;
        }
        if (cbunidad.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO UNIDAD DE MEDIDA \n";
            estado = false;
        }
        /* if (pcliente <= 0) {
            mensaje += "PRECIO CLIENTE DEBE SER MAYOR A CERO \n";
            sppcliente.setValue(0);
            estado = false;
        }*/
        if (ppublico <= 0) {
            mensaje += "PRECIO PUBLICO DEBE SER MAYOR A CERO \n";
            spppublico.setValue(0);
            estado = false;
        }
        if (cantidad <= 0) {
            mensaje += "CANTIDAD DEBE SER MAYOR A CERO \n";
            spcantidad.setValue(0);
            estado = false;
        }
        if (cbproveedor.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO PROVEEDOR \n";
            estado = false;
        }
        if (cbcategoria.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO CATEGORIA \n";
            estado = false;
        }

        if (mensaje.length() >= 4) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }
        return estado;

    }//MetodoParaValidarCajasDeTexto

    public void limpiarC() {//MetodoLimpiarCajaasDeTexto
        txtnombre.setText("");
        cbunidad.setSelectedIndex(0);
        sppcliente.setValue(0.0);
        spppublico.setValue(0.0);
        spcantidad.setValue(0);
        cbproveedor.setSelectedIndex(0);
        cbcategoria.setSelectedIndex(0);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
    }

    public void EventoTbProductMC() {
        int fseleccionada = tbproductos.getSelectedRow();
        if (fseleccionada >= 0) {
            habPanCon();
            this.btnmodificar.setEnabled(true);
            this.btneliminar.setEnabled(true);
            txtcod.setText(tbproductos.getValueAt(fseleccionada, 0).toString());
            txtnombre.setText(tbproductos.getValueAt(fseleccionada, 1).toString());
            cbunidad.setSelectedItem(tbproductos.getValueAt(fseleccionada, 9).toString());
            if (tbproductos.getValueAt(fseleccionada, 3).toString().toUpperCase().contains("SIN")) {
                sppcliente.setValue(0.0);
            } else {
                sppcliente.setValue(Double.parseDouble(tbproductos.getValueAt(fseleccionada, 3).toString()));
            }
            spppublico.setValue(Double.parseDouble(tbproductos.getValueAt(fseleccionada, 2).toString()));
            spcantidad.setValue(Integer.parseInt(tbproductos.getValueAt(fseleccionada, 4).toString()));
            cbproveedor.setSelectedItem(tbproductos.getValueAt(fseleccionada, 8).toString());
            cbcategoria.setSelectedItem(tbproductos.getValueAt(fseleccionada, 7).toString());
            // padquisicion.setText(tbproductos.getValueAt(fseleccionada, 7).toString());
            btnaceptar.setEnabled(false);
            btnnuevo.setEnabled(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        pcontenido = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        sppcliente = new javax.swing.JSpinner();
        cbunidad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        spppublico = new javax.swing.JSpinner();
        spcantidad = new javax.swing.JSpinner();
        cbproveedor = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelCat = new javax.swing.JLabel();
        cbcategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        plista = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        padquisicion = new javax.swing.JLabel();

        pnlDatos.setBackground(new java.awt.Color(0, 111, 111));
        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btncancelar)
                .addContainerGap())
        );

        pcontenido.setBackground(new java.awt.Color(0, 111, 111));
        pcontenido.setForeground(new java.awt.Color(102, 255, 102));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        sppcliente.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 5.0f));

        cbunidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbunidadActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("CODIGO");

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setText("NOMBRE");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("UNIDAD MEDIDA");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setText("PRECIO CLIENTE");

        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodKeyReleased(evt);
            }
        });

        spppublico.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbproveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("PRECIO PUBLICO");

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(254, 254, 254));
        jLabel11.setText("CANTIDAD");

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("PROVEEDOR");

        jLabelCat.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelCat.setForeground(new java.awt.Color(254, 254, 254));
        jLabelCat.setText("CATEGORIA");

        cbcategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        javax.swing.GroupLayout pcontenidoLayout = new javax.swing.GroupLayout(pcontenido);
        pcontenido.setLayout(pcontenidoLayout);
        pcontenidoLayout.setHorizontalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pcontenidoLayout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jLabel7))
                        .addGroup(pcontenidoLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addGroup(pcontenidoLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(3, 3, 3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCat, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(30, 30, 30)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbproveedor, 0, 498, Short.MAX_VALUE)
                    .addComponent(spcantidad)
                    .addComponent(spppublico)
                    .addComponent(sppcliente)
                    .addComponent(cbunidad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnombre)
                    .addComponent(txtcod)
                    .addComponent(cbcategoria, 0, 498, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        pcontenidoLayout.setVerticalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sppcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spppublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCat)
                    .addComponent(cbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRODUCTOS");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pcontenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(pcontenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        plista.setBackground(new java.awt.Color(17, 140, 140));
        plista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        plista.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("PRODUCTO");

        btnnuevo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
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
        btneliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneliminarMouseClicked(evt);
            }
        });
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
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

        tbproductos.setAutoCreateColumnsFromModel(false);
        tbproductos.setBackground(new java.awt.Color(204, 204, 255));
        tbproductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbproductos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbproductos);

        padquisicion.setText("jLabel2");

        javax.swing.GroupLayout plistaLayout = new javax.swing.GroupLayout(plista);
        plista.setLayout(plistaLayout);
        plistaLayout.setHorizontalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plistaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar))
                    .addComponent(jScrollPane2)
                    .addGroup(plistaLayout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminar)
                        .addGap(31, 31, 31)
                        .addComponent(padquisicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                        .addComponent(btnsalir)))
                .addContainerGap())
        );
        plistaLayout.setVerticalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodificar)
                    .addComponent(btnnuevo)
                    .addComponent(padquisicion))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        try {
            ProductoDTO pr = new ProductoDTO();
            double pad = 0;
            if (valEntradas() == true) {
                int confirmar2 = JOptionPane.showConfirmDialog(null, "¿INGRESAR PRECIO DE AQUISICION?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar2 == JOptionPane.YES_OPTION) {
                    pad = Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
                }
                int confirmar = JOptionPane.showConfirmDialog(null, "¿SUS DATOS SON CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    pr.setIdproducto(Integer.parseInt(txtcod.getText()));
                    pr.setNombre(txtnombre.getText());
                    pr.setPrecio(Double.parseDouble(spppublico.getValue().toString()));
                    if (Double.parseDouble(sppcliente.getValue().toString()) == 0.0) {
                        pr.setPreciocliente(0.0);
                        pr.setActivarpreciocliente(false);
                    } else {
                        pr.setPreciocliente(Double.parseDouble(sppcliente.getValue().toString()));
                        pr.setActivarpreciocliente(true);
                    }
                    pr.setStock(Integer.parseInt(spcantidad.getValue().toString()));
                    pr.setPreciodeproveedor(pad);

                    CategoriaDTO cat = categoriaController.categoriaByNombre(cbcategoria.getSelectedItem().toString());
                    pr.setIdcategoria(cat.getIdcategoria());
                    ProveedorDTO prove = proveedoresController.proveedorByNombre(cbproveedor.getSelectedItem().toString());
                    pr.setIdproveedor(prove.getIdproveedor());
                    UnidadesMedidaDTO uni = unidadController.unidadByNombre(cbunidad.getSelectedItem().toString());
                    pr.setIdunidadm(uni.getIdunidadm());
                    boolean bandera = productosController.save(pr);
                    if (bandera) {
                        new MetodosValidar().ok();
                        limpiarC();
                        llenarTbProductos("");
                        desabPanCon();
                        btnnuevo.setEnabled(true);
                    } else {
                        new MetodosValidar().error();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AL GUARDAR PRODUCTO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        desabPanCon();
        limpiarC();
        this.btnnuevo.setEnabled(true);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        habPanCon();
        txtcod.setText(String.valueOf(leerCodigoProductos()));
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarTbProductos(txtBuscar.getText());
        txtBuscar.setText(this.txtBuscar.getText().toUpperCase());

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        try {
            ProductoDTO pr = new ProductoDTO();
            double pad = 0;
            if (valEntradas() == true) {
                int confirmar2 = JOptionPane.showConfirmDialog(null, "¿INGRESAR PRECIO DE AQUISICION?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar2 == JOptionPane.YES_OPTION) {
                    pad = Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
                }
                int confirmar = JOptionPane.showConfirmDialog(null, "¿MDIFICAR?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    pr.setIdproducto(Integer.parseInt(txtcod.getText()));
                    pr.setNombre(txtnombre.getText());
                    pr.setPrecio(Double.parseDouble(spppublico.getValue().toString()));
                    if (Double.parseDouble(sppcliente.getValue().toString()) == 0.0) {
                        pr.setPreciocliente(0.0);
                        pr.setActivarpreciocliente(false);
                    } else {
                        pr.setPreciocliente(Double.parseDouble(sppcliente.getValue().toString()));
                        pr.setActivarpreciocliente(true);
                    }
                    pr.setStock(Integer.parseInt(spcantidad.getValue().toString()));
                    pr.setPreciodeproveedor(pad);

                    CategoriaDTO cat = categoriaController.categoriaByNombre(cbcategoria.getSelectedItem().toString());
                    pr.setIdcategoria(cat.getIdcategoria());
                    ProveedorDTO prove = proveedoresController.proveedorByNombre(cbproveedor.getSelectedItem().toString());
                    pr.setIdproveedor(prove.getIdproveedor());
                    UnidadesMedidaDTO uni = unidadController.unidadByNombre(cbunidad.getSelectedItem().toString());
                    pr.setIdunidadm(uni.getIdunidadm());
                    boolean bandera = productosController.update(pr);
                    if (bandera) {
                        new MetodosValidar().ok_modificar();
                        limpiarC();
                        llenarTbProductos("");
                        desabPanCon();
                    } else {
                        new MetodosValidar().error_modificar();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AL GUARDAR PRODUCTO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        try {
            boolean ba = productosController.detelete(Integer.parseInt(txtcod.getText()));
            int conf = JOptionPane.showConfirmDialog(null, "¿ELIMINAR REGISTROS?", "", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                if (ba) {
                    new MetodosValidar().ok_eliminar();
                    desabPanCon();
                    limpiarC();
                    llenarTbProductos("");
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar productos:" + e.getMessage());
        }

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased

        this.txtnombre.setText(this.txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void tbproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosMouseClicked
        EventoTbProductMC();
    }//GEN-LAST:event_tbproductosMouseClicked

    private void cbunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbunidadActionPerformed

    }//GEN-LAST:event_cbunidadActionPerformed

    private void txtcodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyReleased

    }//GEN-LAST:event_txtcodKeyReleased

    private void btneliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminarMouseClicked

    }//GEN-LAST:event_btneliminarMouseClicked

    public void llenarTbProductos(String valor) {
        try {
            dtm = new DefaultTableModel();
            dtm.addColumn("CODIGO");
            dtm.addColumn("NOMBRE");
            dtm.addColumn("P.PUBLICO");
            dtm.addColumn("P.CLIENTE");
            dtm.addColumn("STOCK");
            dtm.addColumn("ACTIVA P.CLIENTE");
            dtm.addColumn("PRECIO DE COMPRA");
            dtm.addColumn("CATEGORIA");
            dtm.addColumn("PROVEEDOR");
            dtm.addColumn("U.MEDIDA");
            tbproductos.setModel(dtm);
            List<ProductoDTO> listaProductos = productosController.productosAll(valor);
            for (int i = 0; i < listaProductos.size(); i++) {
                ProductoDTO producto = listaProductos.get(i);
                Object[] fila = new Object[11];
                fila[0] = producto.getIdproducto();
                fila[1] = producto.getNombre();
                fila[2] = producto.getPrecio();
                if (producto.isActivarpreciocliente()) {
                    fila[3] = producto.getPreciocliente();
                } else {
                    fila[3] = "SIN PRECIO CLIENTE";
                }
                fila[4] = producto.getStock();
                fila[5] = producto.isActivarpreciocliente();
                fila[6] = producto.getPreciodeproveedor();

                CategoriaDTO cat = categoriaController.categoriaById(producto.getIdcategoria());
                fila[7] = cat.getDescripcion();
                ProveedorDTO prov = proveedoresController.proveedorById(producto.getIdproveedor());
                fila[8] = prov.getNombre();
                UnidadesMedidaDTO unidad = unidadController.unidadById(producto.getIdunidadm());
                fila[9] = unidad.getDescripcion();
                dtm.addRow(fila);
            }
            tbproductos.setModel(dtm);
        } catch (Exception ex) {
            System.out.println("Error al llenar la tabla de productos:" + ex.getMessage());
        }
    }

    public int leerCodigoProductos() {
        return productosController.generarSecuenciaId();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbcategoria;
    private javax.swing.JComboBox<String> cbproveedor;
    private javax.swing.JComboBox<String> cbunidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel padquisicion;
    private javax.swing.JPanel pcontenido;
    private javax.swing.JPanel plista;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JSpinner spcantidad;
    private javax.swing.JSpinner sppcliente;
    private javax.swing.JSpinner spppublico;
    public javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtcod;
    public javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
