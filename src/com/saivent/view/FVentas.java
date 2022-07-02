package com.saivent.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.saivent.principal.Run;
import com.saivent.reportes.PlantillaReporteNota;
import com.saivent.util.MetodosValidar;
import com.sistema.controller.CategoriaController;
import com.sistema.controller.ClientesController;
import com.sistema.controller.NegocioController;
import com.sistema.controller.ProductoController;
import com.sistema.controller.ProveedorController;
import com.sistema.controller.UnidadesMedidaController;
import com.sistema.controller.VentasController;
import com.sistema.modelo.CategoriaDTO;
import com.sistema.modelo.ClienteDTO;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
import com.sistema.modelo.ReporteNotaVentaModelo;
import com.sistema.modelo.TiposVentaDTO;
import com.sistema.modelo.UnidadesMedidaDTO;
import com.sistema.modelo.VentaRealizadaDTO;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FVentas extends javax.swing.JInternalFrame {

    String codCliente = "0000000";//variable q acumula el codigo del cliente cuando se selecciona y misma que nos sirve para validar nombre al insertar venta
    DefaultComboBoxModel dfcb = new DefaultComboBoxModel();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmTicket = new DefaultTableModel();


    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Run principal = new Run();

    VentasController venController = new VentasController();
    ProductoController productoController = new ProductoController();
    UnidadesMedidaController unidadesController = new UnidadesMedidaController();
    ProveedorController proveedoresController = new ProveedorController();
    CategoriaController categoriaController = new CategoriaController();
    ClientesController clientesController = new ClientesController();

    public FVentas() {
        initComponents();
        //Inicio llenando los combo para los tipos de ventas
        diseñarVentana();
        diseñarJDateChooser();
        llenarNombreNegocio();

    }

    //Metodo para diseñar mi ventana
    public void diseñarVentana() {
        //Inserto item a mi combo default
        dfcb.addElement("SELECCIONE");
        //añado columnas a mi modelo tabla
        dtmTicket.addColumn("ID");
        dtmTicket.addColumn("PRODUCTO");
        dtmTicket.addColumn("PRECIO");
        dtmTicket.addColumn("CANTIDAD");
        dtmTicket.addColumn("TOTAL");
        tbTicket.setModel(dtmTicket);
        //llamo el metodo para cargar los metodos de venta
        llenarCBM();

        //Txt cliente inicia con valor xxx por si es venta a publico el cliente se van con XXXXXXXXXXXXXXXXXXXX
        txtCliente.setText("XXXXXXXXXXXXXXXXXXXXXXXXX");
        //como mi ticket inicia vacio mi boton debe estar inabilitado
        btnVaciarTicket.setEnabled(false);
        //A mi objeto de calendar le paso la fecha actual del sistema
        setFechaTiempoReal();
        //Inicio botones desabilitados
        btnAdd.setEnabled(false);
        inicioFrame();

        Dimension DimensionBarra = null;
        JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        DimensionBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    /*@Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
     */
    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        if (Integer.parseInt(hora) == 00) {
            hora = "12";
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogClientes = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtclientesb = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpanelJDClientesTB = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientes1 = new javax.swing.JTable();
        jDialogProducto = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtProductoB = new javax.swing.JTextField();
        btnsalir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnaddProd = new javax.swing.JButton();
        jDialogProductosAdmin = new javax.swing.JDialog();
        plista = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbproductosAdmin = new javax.swing.JTable();
        pnlDatos1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        pcontenido = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        sppcliente = new javax.swing.JSpinner();
        cbunidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        spppublico = new javax.swing.JSpinner();
        spcantidad = new javax.swing.JSpinner();
        cbproveedor = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblTituloNegocio = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbTipoVenta = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        txtCod = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnbprod = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTicket = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();
        btnVaciarTicket = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtTotalIva = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setText("BUSCAR");

        txtclientesb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclientesbKeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 111, 111));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SELECCION DE CLIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel5)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jpanelJDClientesTB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tbClientes1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbClientes1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientes1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbClientes1);

        javax.swing.GroupLayout jpanelJDClientesTBLayout = new javax.swing.GroupLayout(jpanelJDClientesTB);
        jpanelJDClientesTB.setLayout(jpanelJDClientesTBLayout);
        jpanelJDClientesTBLayout.setHorizontalGroup(
            jpanelJDClientesTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelJDClientesTBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpanelJDClientesTBLayout.setVerticalGroup(
            jpanelJDClientesTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelJDClientesTBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpanelJDClientesTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelJDClientesTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogClientesLayout = new javax.swing.GroupLayout(jDialogClientes.getContentPane());
        jDialogClientes.getContentPane().setLayout(jDialogClientesLayout);
        jDialogClientesLayout.setHorizontalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogClientesLayout.setVerticalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setText("BUSCAR");

        txtProductoB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoBKeyReleased(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 111, 111));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SELECCION DE PRODUCTOS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tbProductos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbProductos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnaddProd.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaddProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/gestiones-menu.png"))); // NOI18N
        btnaddProd.setText("Productos");
        btnaddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnsalir)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddProd)))))
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnaddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogProductoLayout = new javax.swing.GroupLayout(jDialogProducto.getContentPane());
        jDialogProducto.getContentPane().setLayout(jDialogProductoLayout);
        jDialogProductoLayout.setHorizontalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProductoLayout.setVerticalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        plista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        plista.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setText("BUSCAR");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tbproductosAdmin.setBackground(new java.awt.Color(204, 204, 255));
        tbproductosAdmin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbproductosAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproductosAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosAdminMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbproductosAdmin);

        javax.swing.GroupLayout plistaLayout = new javax.swing.GroupLayout(plista);
        plista.setLayout(plistaLayout);
        plistaLayout.setHorizontalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plistaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
                .addContainerGap())
        );
        plistaLayout.setVerticalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnaceptar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptar.setText("MODIFICAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btncancelar)
                .addContainerGap())
        );

        pcontenido.setForeground(new java.awt.Color(102, 255, 102));

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        sppcliente.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbunidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbunidadActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setText("CODIGO");

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel11.setText("NOMBRE");

        jLabel13.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel13.setText("UNIDAD MEDIDA");

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel15.setText("PRECIO CLIENTE");

        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodKeyReleased(evt);
            }
        });

        spppublico.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbproveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel16.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel16.setText("PRECIO PUBLICO");

        jLabel17.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel17.setText("CANTIDAD");

        jLabel18.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel18.setText("PROVEEDOR");

        javax.swing.GroupLayout pcontenidoLayout = new javax.swing.GroupLayout(pcontenido);
        pcontenido.setLayout(pcontenidoLayout);
        pcontenidoLayout.setHorizontalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcontenidoLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel8))
                    .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(pcontenidoLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcod)
                    .addComponent(txtnombre)
                    .addComponent(cbunidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spcantidad)
                    .addComponent(cbproveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spppublico)
                    .addComponent(sppcliente))
                .addGap(21, 21, 21))
        );
        pcontenidoLayout.setVerticalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sppcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spppublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDatos1Layout = new javax.swing.GroupLayout(pnlDatos1);
        pnlDatos1.setLayout(pnlDatos1Layout);
        pnlDatos1Layout.setHorizontalGroup(
            pnlDatos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pcontenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlDatos1Layout.setVerticalGroup(
            pnlDatos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatos1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pcontenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogProductosAdminLayout = new javax.swing.GroupLayout(jDialogProductosAdmin.getContentPane());
        jDialogProductosAdmin.getContentPane().setLayout(jDialogProductosAdminLayout);
        jDialogProductosAdminLayout.setHorizontalGroup(
            jDialogProductosAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogProductosAdminLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jDialogProductosAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialogProductosAdminLayout.setVerticalGroup(
            jDialogProductosAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogProductosAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jLabel14.setText("jLabel14");

        setBackground(new java.awt.Color(0, 111, 111));
        setForeground(new java.awt.Color(51, 0, 51));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnlDatos.setBackground(new java.awt.Color(0, 111, 111));
        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblTituloNegocio.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        lblTituloNegocio.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloNegocio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloNegocio.setText("AGROVETERINARIA EL CAMPO, MAYOREO Y MENUDEO");

        jPanel9.setBackground(new java.awt.Color(0, 111, 111));

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CLIENTE");

        cbTipoVenta.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cbTipoVenta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(243, 250, 244), null, null));
        cbTipoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbTipoVentaMousePressed(evt);
            }
        });
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TIPO VENTA");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PRODUCTO");

        txtCliente.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(0, 111, 111));

        txtCod.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(254, 254, 254));
        jLabel20.setText("X");

        txtCantidad.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnbprod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Find.png"))); // NOI18N
        btnbprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbprodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnbprod, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCantidad)
                        .addComponent(txtCod))))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(btnbprod, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 111, 111));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(0, 111, 111));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblTituloNegocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloNegocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        tbTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTicketMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbTicket);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Exit.png"))); // NOI18N
        jButton1.setText("CERRAR");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancel.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVender.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Apply.png"))); // NOI18N
        btnVender.setText("VENDER");
        btnVender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVender.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        btnVaciarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btnVaciarTicket.setText("ELIMINAR REGISTRO");
        btnVaciarTicket.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVaciarTicket.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVaciarTicket.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnVaciarTicket.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVaciarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarTicketActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(0, 111, 111));

        jLabel19.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(254, 254, 254));
        jLabel19.setText("SUB-TOTAL :");

        jLabel21.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(254, 254, 254));
        jLabel21.setText("IVA :");

        jLabel22.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(254, 254, 254));
        jLabel22.setText("TOTAL :");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(txtSubTotal)
                    .addComponent(txtTotalVenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVaciarTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnVender)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVaciarTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 209, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVender)
                            .addComponent(btnCancelar)
                            .addComponent(jButton1))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean activador = false;
    private void tbClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientes1MouseClicked
        int fs = tbClientes1.getSelectedRow();
        if (fs >= 0) {
            txtCliente.setText(tbClientes1.getValueAt(fs, 1).toString());
            codCliente = tbClientes1.getValueAt(fs, 0).toString();
            cbTipoVenta.setEnabled(false);
            jDialogClientes.setVisible(false);
        }
    }//GEN-LAST:event_tbClientes1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jDialogClientes.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtclientesbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclientesbKeyReleased
        this.txtclientesb.setText(this.txtclientesb.getText().toUpperCase());
        cargarTBC(txtclientesb.getText());
    }//GEN-LAST:event_txtclientesbKeyReleased

    private void txtProductoBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoBKeyReleased
        txtProductoB.setText(this.txtProductoB.getText().toUpperCase());
        cargarTBP(txtProductoB.getText());
    }//GEN-LAST:event_txtProductoBKeyReleased

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.jDialogProducto.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        int fs = tbProductos.getSelectedRow();
        if (fs >= 0) {
            txtCod.setText(tbProductos.getValueAt(fs, 0).toString());
            if (Integer.parseInt(tbProductos.getValueAt(fs, 5).toString()) <= 0) {
                JOptionPane.showMessageDialog(null, "¡¡¡VERIFIQUE SU EXISTENCIA!!!", "", JOptionPane.WARNING_MESSAGE);
            } else {
                jDialogProducto.setVisible(false);
                btnAdd.setEnabled(true);

            }

        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btnaddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddProdActionPerformed
        desabComponentesInit();
        productosView();

    }//GEN-LAST:event_btnaddProdActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        this.txtCliente.setText(this.txtCliente.getText().toUpperCase());
    }//GEN-LAST:event_txtClienteKeyReleased

    private void cbTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoVentaActionPerformed
        cargarTBC("");
        if (cbTipoVenta.getSelectedItem().toString().toUpperCase().contains("CLIENTE")) {
            jDialogClientes.setModal(true);
            jDialogClientes.setTitle("LISTA CLIENTES");
            jDialogClientes.setSize(483, 400);
            jDialogClientes.setLocationRelativeTo(null);
            jDialogClientes.setVisible(true);
            jDialogClientes.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Person.png")).getImage());
            
        } else {
            txtCliente.setEditable(false);
        }
    }//GEN-LAST:event_cbTipoVentaActionPerformed

    private void btnbprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbprodActionPerformed
        cargarTBP("");
        try {
            jDialogProducto.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Shopping_cart.png")).getImage());
            jDialogProducto.setModal(true);
            jDialogProducto.setTitle("LISTA PRODUCTOS");
            jDialogProducto.setSize(556, 400);
            jDialogProducto.setLocationRelativeTo(null);
            jDialogProducto.setVisible(true);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
        txtCantidad.requestFocus();
    }//GEN-LAST:event_btnbprodActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.insertarDatosTicket();
        btnVender.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbTipoVentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTipoVentaMousePressed


    }//GEN-LAST:event_cbTipoVentaMousePressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarTbProductos(txtBuscar.getText());
        txtBuscar.setText(this.txtBuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tbproductosAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosAdminMouseClicked
        EventoTbProductoMouseClicked();
    }//GEN-LAST:event_tbproductosAdminMouseClicked

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        try {
            int confirmar2 = JOptionPane.showConfirmDialog(null, "¿CONFIRMAR CAMBIO?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar2 == JOptionPane.YES_OPTION) {
                if (Integer.parseInt(spcantidad.getValue().toString()) > 0) {
                    int cantidad = Integer.parseInt(spcantidad.getValue().toString());
                    int updates = productoController.modificarProductoStock(Integer.parseInt(txtcod.getText()), cantidad);
                    if (updates > 0) {
                        JOptionPane.showMessageDialog(null, "Stock modificado a:" + spcantidad.getValue());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar existencias", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                llenarTbProductos("");
                cargarTBP("");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar productos:" + e.getMessage());
        }

    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        jDialogProductosAdmin.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased

        this.txtnombre.setText(this.txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void cbunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbunidadActionPerformed

    private void txtcodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyReleased

    }//GEN-LAST:event_txtcodKeyReleased

    private void btnVaciarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarTicketActionPerformed
        int fila = tbTicket.getSelectedRow();
        if (fila >= 0) {
            dtmTicket.removeRow(fila);
            JOptionPane.showMessageDialog(null, "ELIMINADO DEL CARRITO COMPRAS");
            calcTot();
            btnVaciarTicket.setEnabled(false);
        }

    }//GEN-LAST:event_btnVaciarTicketActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        generarVenta();
        btnCancelar();
    }//GEN-LAST:event_btnVenderActionPerformed

    private void tbTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTicketMouseClicked

    }//GEN-LAST:event_tbTicketMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnCancelar();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyReleased
        new MetodosValidar().soloNumeros(txtCod, 4);
    }//GEN-LAST:event_txtCodKeyReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        new MetodosValidar().soloNumeros(txtCantidad, 2);
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            ProductoDTO prod = productoController.productoById(Integer.parseInt(txtCod.getText()));
            if (prod.getStock() >= Integer.parseInt(txtCantidad.getText())) {
                insertarDatosTicket();
                btnVender.setEnabled(true);
                btnCancelar.setEnabled(true);
                txtCantidad.setText("");
            } else {
                new MetodosValidar().advertencia("VERIFIQUE EXISTENCIAS");
            }

        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    public void inicioFrame() {
        txtSubTotal.setEditable(false);
        txtTotalIva.setEditable(false);
        txtTotalVenta.setEditable(false);
        txtSubTotal.setText("0.0");
        txtTotalIva.setText("0.0");
        txtTotalVenta.setEditable(false);
        btnVender.setEnabled(false);

    }

    public void calcTot() {
        double total = 0;
        double IVA = 0;

        double a;
        double b = 0;
        for (int i = 0; i < dtmTicket.getRowCount(); i++) {
            String Calculo = String.valueOf(dtmTicket.getValueAt(i, 4));
            a = Double.parseDouble(Calculo);
            b = b + a;
            if (i == dtmTicket.getRowCount() - 1) {
                IVA = b * 0.16;
                total = b;
            }
        }
        txtTotalIva.setText("0.0");//String.valueOf(IVA));
        txtSubTotal.setText(String.valueOf(b));
        double total_venta = Double.parseDouble(txtSubTotal.getText()) + Double.parseDouble(txtTotalIva.getText());
        txtTotalVenta.setText(String.valueOf(total_venta));
    }

    public void productosView() {        
        
        try {
            llenarTbProductos("");
            jDialogProductosAdmin.setTitle("ADMINISTRACION DE PRODUCTOS");
            jDialogProductosAdmin.setModal(true);
            jDialogProductosAdmin.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Shopping_cart.png")).getImage());
            jDialogProductosAdmin.setSize(850, 525);
            jDialogProductosAdmin.setLocationRelativeTo(null);
            jDialogProductosAdmin.setVisible(true); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void EventoTbProductoMouseClicked() {
        desabComponentesProductos();
        llenarUnidades();
        llenarProveedores();
        int fseleccionada = tbproductosAdmin.getSelectedRow();
        if (fseleccionada >= 0) {
            txtcod.setText(tbproductosAdmin.getValueAt(fseleccionada, 0).toString());
            txtnombre.setText(tbproductosAdmin.getValueAt(fseleccionada, 1).toString());
            cbunidad.setSelectedItem(tbproductosAdmin.getValueAt(fseleccionada, 9));
            if (!tbproductosAdmin.getValueAt(fseleccionada, 5).toString().toUpperCase().contains("FALSE")) {
                sppcliente.setValue(Double.parseDouble(tbproductosAdmin.getValueAt(fseleccionada, 3).toString()));
            }
            spppublico.setValue(Double.parseDouble(tbproductosAdmin.getValueAt(fseleccionada, 2).toString()));
            spcantidad.setValue(Integer.parseInt(tbproductosAdmin.getValueAt(fseleccionada, 4).toString()));
            cbproveedor.setSelectedItem(tbproductosAdmin.getValueAt(fseleccionada, 8));
            btnaceptar.setEnabled(true);
        }

    }

    public void llenarUnidades() {
        List<UnidadesMedidaDTO> lista = unidadesController.unidadesAll("");
        for (int x = 0; x < lista.size(); x++) {
            UnidadesMedidaDTO unidad = lista.get(x);
            cbunidad.addItem(unidad.getDescripcion());
        }
    }

    public void llenarProveedores() {
        List<ProveedorDTO> lista = proveedoresController.proveedoresAll("");
        for (int x = 0; x < lista.size(); x++) {
            ProveedorDTO provedor = lista.get(x);
            cbproveedor.addItem(provedor.getNombre());
        }
    }

    public void llenarTbProductos(String nombre) {
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
            tbproductosAdmin.setModel(dtm);
            List<ProductoDTO> listaProductos = null;
            if (nombre.trim().equals("")) {
                listaProductos = productoController.productosAll("");
            } else {
                listaProductos = productoController.productosAll(nombre);
            }
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
                UnidadesMedidaDTO unidad = unidadesController.unidadById(producto.getIdunidadm());
                fila[9] = unidad.getDescripcion();
                dtm.addRow(fila);

            }
            tbproductosAdmin.setModel(dtm);
        } catch (Exception ex) {

        }

    }

    public void desabComponentesProductos() {
        txtcod.setEnabled(false);
        txtnombre.setEnabled(false);
        cbunidad.setEnabled(false);
        spppublico.setEnabled(false);
        sppcliente.setEnabled(false);
        cbproveedor.setEnabled(false);
        spcantidad.setEnabled(true);

    }

    public void desabComponentesInit() {
        txtcod.setEnabled(false);
        txtnombre.setEnabled(false);
        cbunidad.setEnabled(false);
        spppublico.setEnabled(false);
        sppcliente.setEnabled(false);
        cbproveedor.setEnabled(false);
        spcantidad.setEnabled(false);

    }

    /*-----------------------------Metodo para preparar tabla para ticket----------------------------*/
    public void insertarDatosTicket() {
        btnVaciarTicket.setEnabled(true);
        try {
            //Obtenemos la descripcion completa del producto por nombre
            List<String> lista_id = new ArrayList<>();
            int cantidad = 0;// Integer.parseInt(txtCantidad.getText());
            boolean bandera = false;
            ProductoDTO producto = productoController.productoById(Integer.parseInt(txtCod.getText()));
            double precio = producto.getPrecio();
            for (int i = 0; i < tbTicket.getRowCount(); i++) {
                lista_id.add(tbTicket.getValueAt(i, 0).toString());
            }

            String id = txtCod.getText();
            Object[] fila = new Object[5];

            if (lista_id.size() > 0) {
                for (int i = 0; i < lista_id.size(); i++) {
                    int idd = Integer.parseInt(lista_id.get(i));
                    if (idd == Integer.parseInt(txtCod.getText())) {
                        tbTicket.setValueAt(Integer.parseInt(tbTicket.getValueAt(i, 3).toString()) + Integer.parseInt(txtCantidad.getText()), i, 3);
                        tbTicket.setValueAt(Double.parseDouble(tbTicket.getValueAt(i, 2).toString()) * Integer.parseInt(tbTicket.getValueAt(i, 3).toString()), i, 4);
                        bandera = true;
                    }
                }
            }
            if (!bandera) {
                fila[0] = id;
                fila[1] = producto.getNombre();
                if (cbTipoVenta.getSelectedItem().toString().toLowerCase().contains("cliente")) {
                    fila[2] = producto.getPreciocliente();
                } else {
                    fila[2] = producto.getPrecio();
                }
                fila[3] = Integer.parseInt(txtCantidad.getText());
                if (cbTipoVenta.getSelectedItem().toString().toUpperCase().contains("CLIENTE")) {
                    fila[4] = producto.getPreciocliente() * Integer.parseInt(txtCantidad.getText());
                } else {
                    fila[4] = producto.getPrecio() * Integer.parseInt(txtCantidad.getText());
                }
                dtmTicket.addRow(fila);

            }
            tbTicket.setModel(dtmTicket);
            calcTot();

        } catch (Exception e) {
            System.out.println("Error al obtener descripcion del producto:" + e.getMessage());
        }
    }

    public void calculoTotal() {
        for (int x = 0; x < tbTicket.getRowCount(); x++) {
            txtSubTotal.setText(tbproductosAdmin.getValueAt(x, 4).toString());
        }
    }

    /*-----------------------------Metodo para obtener la fecha actual del sistema-------------------*/
    public void setFechaTiempoReal() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = new GregorianCalendar();
        String dia = Integer.toString(c2.get(Calendar.DATE));
        int mes = Integer.parseInt(Integer.toString(c2.get(Calendar.MONTH))) + 1;
        String annio = Integer.toString(c2.get(Calendar.YEAR));
        String mesr = String.valueOf(mes);
        if ((Integer.parseInt(dia) < 10)) {
            dia = "0" + Integer.toString(c2.get(Calendar.DATE));
        }
        if (mes < 10) {
            mesr = "0" + String.valueOf(mes);
        }

    }

    /*-----------------------------Metodo para llenar combo de tipos de ventas-------------------*/
    public void llenarCBM() {
        List<TiposVentaDTO> lista = venController.tiposVenta();
        for (int i = 0; i < lista.size(); i++) {
            cbTipoVenta.addItem(lista.get(i).getIdtipoventa() + ".- " + lista.get(i).getDescripcion());
        }
    }

    /*-----------------------------Metodo para llenar el catalogo de clientes-------------------*/
    public void cargarTBC(String valorB) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        this.tbClientes1.setModel(modelo);
        try {
            List<ClienteDTO> clientes = clientesController.findAll(valorB);
            for (int i = 0; i < clientes.size(); i++) {
                Object[] obj = new Object[4];
                obj[0] = clientes.get(i).getIdcliente();
                obj[1] = clientes.get(i).getNombre();
                obj[2] = clientes.get(i).getDomicilio();
                obj[3] = clientes.get(i).getTelefono();
                modelo.addRow(obj);
            }
            tbClientes1.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS CLIENTES", "", JOptionPane.ERROR_MESSAGE);

        }
    }

    /*-----------------------------Metodo para cargar el catalogo de productos-------------------*/
    public void cargarTBP(String valorB) {
        try {
            dtm = new DefaultTableModel();
            dtm.addColumn("CODIGO");
            dtm.addColumn("NOMBRE");
            dtm.addColumn("P.PUBLICO");
            dtm.addColumn("P.CLIENTE");
            dtm.addColumn("U.MEDIDA");
            dtm.addColumn("STOCK");
            tbProductos.setModel(dtm);
            List<ProductoDTO> listaProductos = null;
            if (valorB.trim().equals("")) {
                listaProductos = productoController.productosAll("");
            } else {
                listaProductos = productoController.productosAll(valorB);
            }
            for (int i = 0; i < listaProductos.size(); i++) {
                ProductoDTO producto = listaProductos.get(i);
                Object[] fila = new Object[6];
                fila[0] = producto.getIdproducto();
                fila[1] = producto.getNombre();
                fila[2] = producto.getPrecio();
                if (producto.isActivarpreciocliente()) {
                    fila[3] = producto.getPreciocliente();
                } else {
                    fila[3] = "SIN PRECIO CLIENTE";
                }
                UnidadesMedidaDTO unidad = unidadesController.unidadById(producto.getIdunidadm());

                fila[4] = unidad.getDescripcion();
                fila[5] = producto.getStock();
                dtm.addRow(fila);

            }
            tbProductos.setModel(dtm);

        } catch (Exception e) {
            System.out.println("Error al llenar catalogo de productos:" + e.getMessage());
        }

    }

    public void generarVenta() {
        Random r = new Random();
        int random = r.nextInt(90000) + 10000;
        int opcion = JOptionPane.showConfirmDialog(null, "¿CONFIRMAR VENTA?", "", JOptionPane.YES_NO_OPTION);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String jd = sdf.format(jDateChooser1.getDate());
        String hoy = sdf.format(new Date());
        int insertaDetalles = 0;
        ReporteNotaVentaModelo rpNota = new ReporteNotaVentaModelo();
        rpNota.setFecha(fecha());
        rpNota.setNombreNegocio(lblTituloNegocio.getText());
        rpNota.setTotalVenta(Double.parseDouble(txtTotalVenta.getText()));

        List<DetalleVentaDTO> listaReporte = new ArrayList<>();
        if (opcion == JOptionPane.YES_OPTION) {
            //primero descontamos de la base de datos el total de productos
            //Insertamos la venta
            VentaRealizadaDTO venta = new VentaRealizadaDTO();
            venta.setIdticket(random);
            Timestamp timestamp = null;
            if (!jd.equals(hoy)) {
                timestamp = new java.sql.Timestamp(jDateChooser1.getDate().getTime());
            } else {
                timestamp = new java.sql.Timestamp(new Date().getTime());

            }
            venta.setFecha(timestamp);
            venta.setTipoventa(cbTipoVenta.getSelectedItem().toString());
            venta.setTotalventa(Double.parseDouble(txtTotalVenta.getText()));
            venta.setUsuario(1);

            int insertaRegistro = venController.insertarVentaRealizada(venta);
            for (int y = 0; y < tbTicket.getRowCount(); y++) {
                ProductoDTO producto = productoController.productoById(Integer.parseInt(tbTicket.getValueAt(y, 0).toString()));
                int eliminarProductos = productoController.modificarProductoStock(Integer.parseInt(tbTicket.getValueAt(y, 0).toString()), producto.getStock() - Integer.parseInt(tbTicket.getValueAt(y, 3).toString()));

                DetalleVentaDTO detalle_venta = new DetalleVentaDTO();
                detalle_venta.setTicket(venta.getIdticket());
                detalle_venta.setProducto(producto.getNombre());
                detalle_venta.setTotalProducto(Integer.parseInt(tbTicket.getValueAt(y, 3).toString()));
                detalle_venta.setTotal(Double.parseDouble(tbTicket.getValueAt(y, 4).toString()));
                detalle_venta.setCliente(txtCliente.getText());
                listaReporte.add(detalle_venta);
                insertaDetalles = venController.insertarDetalleVenta(detalle_venta);

            }
            rpNota.setDetallesVenta(listaReporte);

            if (insertaRegistro > 0 && insertaDetalles > 0) {
                Icon icono = new ImageIcon(getClass().getResource("/Imagenes/applicated.gif"));
                JOptionPane.showMessageDialog(null, "VENTA EXITOSA" + "\n"
                        + "TIKCET:" + venta.getIdticket() + "\n"
                        + "TOTAL:" + txtTotalVenta.getText() + "\n"
                        + "FECHA:" + fecha(), "", JOptionPane.PLAIN_MESSAGE, icono);
                int opcionr = JOptionPane.showConfirmDialog(null, "¿IMPRIMIR NOTA?", "", JOptionPane.YES_NO_OPTION);
                if (opcionr == JOptionPane.YES_OPTION) {
                    if (cbTipoVenta.getSelectedItem().toString().contains("CLIE")) {
                        new PlantillaReporteNota(rpNota).generar_y_guardar(2);
                    } else {
                        new PlantillaReporteNota(rpNota).generar_y_guardar(1);
                    }

                }
            }
            vaciarTicketMethod();
        }
    }

    public String fecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String fecha = "";
        try {
            fecha = sdf.format(jDateChooser1.getDate());
        } catch (Exception e) {
            System.out.println("Error al parsear fecha:" + e.getMessage());
        }
        return fecha;
    }

    public void diseñarJDateChooser() {
        ((JTextField) this.jDateChooser1.getDateEditor()).setEditable(false);
        jDateChooser1.setDate(new Date());

    }

    public void vaciarTicketMethod() {
        for (int i = 0; i < tbTicket.getRowCount(); i++) {
            dtmTicket.removeRow(i);
        }
    }

    public void llenarNombreNegocio() {
        NegocioController negocio = new NegocioController();
        try {
            lblTituloNegocio.setText(negocio.busquedaDatos().getNombre());
        } catch (Exception e) {
            System.out.println("Error al obtener nombre neogcio:" + e.getMessage());
        }

    }

    public void btnCancelar() {
        cbTipoVenta.setSelectedIndex(0);
        cbTipoVenta.setEnabled(true);
        txtCantidad.setText("");
        txtCod.setText("");
        txtCliente.setText("XXXXXXXXXXXXXXXXXXXX");
        btnVender.setEnabled(false);
        btnAdd.setEnabled(false);
        btnVaciarTicket.setEnabled(false);
        for (int i = 0; i < dtmTicket.getRowCount(); i++) {
            dtmTicket.removeRow(i);
        }
        txtSubTotal.setText("0.0");
        txtTotalIva.setText("0.0");
        txtTotalVenta.setText("0.0");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnVaciarTicket;
    private javax.swing.JButton btnVender;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnaddProd;
    private javax.swing.JButton btnbprod;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbTipoVenta;
    private javax.swing.JComboBox<String> cbproveedor;
    private javax.swing.JComboBox<String> cbunidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogProducto;
    private javax.swing.JDialog jDialogProductosAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanelJDClientesTB;
    private javax.swing.JLabel lblTituloNegocio;
    private javax.swing.JPanel pcontenido;
    private javax.swing.JPanel plista;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDatos1;
    private javax.swing.JSpinner spcantidad;
    private javax.swing.JSpinner sppcliente;
    private javax.swing.JSpinner spppublico;
    private javax.swing.JTable tbClientes1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbTicket;
    public javax.swing.JTable tbproductosAdmin;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtProductoB;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JTextField txtTotalVenta;
    private javax.swing.JTextField txtclientesb;
    private javax.swing.JTextField txtcod;
    public javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

}
