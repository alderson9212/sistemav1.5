/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.principal;

import com.google.gson.Gson;
import com.saivent.reportes.FReporteProductos1;
import com.saivent.reportes.FReporteVentas1;
import com.saivent.reportes.PlantillaReporteNota;
import com.saivent.util.ImageTable;
import com.saivent.util.MetodosValidar;
import com.saivent.view.FCategorias1;
import com.saivent.view.FEstados1;
import com.saivent.view.FLocalidades1;
import com.saivent.view.FMunicipios1;
import com.saivent.view.FUnidadesM1;
import com.sistema.controller.CategoriaController;
import com.sistema.controller.ClientesController;
import com.sistema.controller.EstadosController;
import com.sistema.controller.LocalidadesController;
import com.sistema.controller.MunicipiosController;
import com.sistema.controller.NegocioController;
import com.sistema.controller.ProductoController;
import com.sistema.controller.ProveedorController;
import com.sistema.controller.UnidadesMedidaController;
import com.sistema.controller.UsuariosController;
import com.sistema.controller.VentasController;
import com.sistema.modelo.CategoriaDTO;
import com.sistema.modelo.ClienteDTO;
import com.sistema.modelo.ColoniaDTO;
import com.sistema.modelo.Config;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
import com.sistema.modelo.ReporteNotaVentaModelo;
import com.sistema.modelo.SaldoDTOLocal;
import com.sistema.modelo.TiposVentaDTO;
import com.sistema.modelo.UnidadesMedidaDTO;
import com.sistema.modelo.UsuarioDTO;
import com.sistema.modelo.VentaRealizadaDTO;
import com.taecel.conexionservicio.MyComboBoxUI;
import com.taecel.conexionservicio.conexionhttpLocal;
import com.taecel.conexionservicio.metodosHTTP;
import com.taecel.modelo.ProductsDTO;
import com.taecel.modelo.StatusDTO;
import com.taecel.modelo.TransaccionDTO;
import com.taecel.modelo.carriersModelo;
import com.taecel.modelo.productoModel;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author wilmer
 */
public class NewJFrame extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();

    VentasController venController = new VentasController();
    ProductoController productoController = new ProductoController();
    UnidadesMedidaController unidadesController = new UnidadesMedidaController();
    ProveedorController proveedoresController = new ProveedorController();
    CategoriaController categoriaController = new CategoriaController();
    ClientesController clientesController = new ClientesController();

    String codCliente = "0000000";//variable q acumula el codigo del cliente cuando se selecciona y misma que nos sirve para validar nombre al insertar venta
    DefaultComboBoxModel dfcb = new DefaultComboBoxModel();
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmTicket = new DefaultTableModel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Catalogos");
    JMenuItem estados = new JMenuItem("Estados");
    JMenuItem municipios = new JMenuItem("Municipios");
    JMenuItem colonias = new JMenuItem("Colonias");
    JMenuItem Unidades_medida = new JMenuItem("Unidades Medida");
    JMenuItem Categorias = new JMenuItem("Categorias");

    JDialog jd = new JDialog();

    /**
     * Creates new form NewJFrame
     */
    String user = "";

    public NewJFrame() {
        initComponents();
        jTabbedPane1.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setEnabledAt(1, false);
                jTabbedPane1.setEnabledAt(2, false);
                jTabbedPane1.setEnabledAt(3, false);
                jTabbedPane1.setEnabledAt(4, false);
                jTabbedPane1.setEnabledAt(5, false);
                jTabbedPane1.setEnabledAt(6, false);
            }
        });
        diseñarVentana();
        diseñarJDateChooser();
        llenarNombreNegocio(lblTitulo);
        this.setLocationRelativeTo(null);
        jComboBox2.setUI(new MyComboBoxUI());
        setMenuBar();
        accionItemEstados();
        accionItemMunicipios();
        accionItemLocalidades();
        accionItemCategorias();
        accionItemUnidadesM();
        jComboBox2.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) jComboBox2.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //((JLabel)jComboBox2.getRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
        // jComboBox2.getEditor().getEditorComponent().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        usuarioCaja.setText(user);
        limpiarPrincipal(true);
    }

    private void setMenuBar() {
        menuBar.setSize(panelImg.getWidth(), 30);
        panelImg.add(menuBar);
        Font f = new Font("Helvetica", Font.BOLD, 12);
        menu.setFont(f);
        menu.add(estados);
        menu.add(municipios);
        menu.add(colonias);
        menu.add(Unidades_medida);
        menu.add(Categorias);
        menuBar.add(menu);
        if (menuBar != null) {
            GridBagConstraints gBconstraints = new GridBagConstraints();
            gBconstraints.gridx = 0;
            gBconstraints.gridy = 0;
            gBconstraints.gridwidth = 1;
            gBconstraints.gridheight = 1;
            gBconstraints.fill = GridBagConstraints.HORIZONTAL;
            gBconstraints.weightx = 1;
            //adding this JMenuBar
            panelImg.add(menuBar, gBconstraints);
        }

    }

    //Accion al item estados
    public void accionItemEstados() {
        //Evento estado
        estados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FEstados1 rpv = new FEstados1();
                try {
                    JDialog dialogov = new JDialog(rpv, "ADMINISTRACION ESTADOS", true);
                    dialogov.add(rpv.getContentPane());
                    dialogov.setSize(430, 400);
                    dialogov.setLocationRelativeTo(null);
                    dialogov.setVisible(true);
                    dialogov.setModal(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void accionItemMunicipios() {
        //Evento estado
        municipios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FMunicipios1 rpv = new FMunicipios1();
                try {
                    JDialog dialogov = new JDialog(rpv, "ADMINISTRACION MUNICIPIOS", true);
                    dialogov.add(rpv.getContentPane());
                    dialogov.setSize(430, 400);
                    dialogov.setLocationRelativeTo(null);
                    dialogov.setVisible(true);
                    dialogov.setModal(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void accionItemLocalidades() {
        //Evento colonias
        colonias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FLocalidades1 rpv = new FLocalidades1();
                rpv.setSize(430, 400);
                try {
                    JDialog dialogov = new JDialog(rpv, "ADMINISTRACION COLONIAS", true);
                    dialogov.add(rpv.getContentPane());
                    dialogov.setSize(430, 400);
                    dialogov.setLocationRelativeTo(null);
                    dialogov.setVisible(true);
                    dialogov.setModal(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void accionItemCategorias() {
        //Evento colonias
        Categorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FCategorias1 rpv = new FCategorias1();
                rpv.setSize(430, 400);
                try {
                    JDialog dialogov = new JDialog(rpv, "ADMINISTRACION CATEGORIAS", true);
                    dialogov.add(rpv.getContentPane());
                    dialogov.setSize(430, 400);
                    dialogov.setLocationRelativeTo(null);
                    dialogov.setVisible(true);
                    dialogov.setUndecorated(true);
                    dialogov.setModal(true);
                } catch (Exception e) {
                    System.out.println("Error al cargar categorias:" + e.getMessage());
                }
            }
        });
    }

    public void accionItemUnidadesM() {
        //Evento colonias
        Unidades_medida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FUnidadesM1 rpv = new FUnidadesM1();
                try {
                    JDialog dialogov = new JDialog(rpv, "ADMINISTRACION UNIDADES MEDIDA", true);
                    dialogov.add(rpv.getContentPane());
                    dialogov.setSize(430, 400);
                    dialogov.setLocationRelativeTo(null);
                    dialogov.setVisible(true);
                    dialogov.setModal(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/carro_vacio.png")).getImage());
        //Txt cliente inicia con valor xxx por si es venta a publico el cliente se van con XXXXXXXXXXXXXXXXXXXX
        txtCliente.setText("XXXXXXXXXXXXXXXXXXXXXXXXX");
        //como mi ticket inicia vacio mi boton debe estar inabilitado
        btnVaciarTicket.setEnabled(false);
        //A mi objeto de calendar le paso la fecha actual del sistema
        setFechaTiempoReal();
        //Inicio botones desabilitados
        btnAdd.setEnabled(false);
        inicioFrame();
        //btnConfig.setEnabled(false);
        //btnUsuarios.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogClientes = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtclientesb = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jpanelJDClientesTB = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientes1 = new javax.swing.JTable();
        jDialogProducto = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtProductoB = new javax.swing.JTextField();
        btnsalir = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnaddProd = new javax.swing.JButton();
        jDialogProductosAdmin = new javax.swing.JDialog();
        plista = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbproductosAdmin = new javax.swing.JTable();
        pnlDatos1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        pcontenido = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        sppcliente = new javax.swing.JSpinner();
        cbunidad = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        spppublico = new javax.swing.JSpinner();
        spcantidad = new javax.swing.JSpinner();
        cbproveedor = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        loading = new javax.swing.JDialog();
        jProgressBar1 = new javax.swing.JProgressBar();
        porcentaje = new javax.swing.JLabel();
        jdVenderSaldo = new javax.swing.JDialog();
        jPanel31 = new javax.swing.JPanel();
        jlVenta = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cbMontoCompa = new javax.swing.JComboBox<>();
        pnlVentas = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtConfirmarNumero = new javax.swing.JTextField();
        lblCostoProducto = new javax.swing.JLabel();
        lblComisionServicio = new javax.swing.JLabel();
        lblVigencia = new javax.swing.JLabel();
        lblNota = new javax.swing.JLabel();
        jlabelCostoProducto = new javax.swing.JLabel();
        jlabelComisionServicio = new javax.swing.JLabel();
        jlabelVigencia = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnRecargar = new javax.swing.JButton();
        lblVigencia1 = new javax.swing.JLabel();
        jLabelCodProducto = new javax.swing.JLabel();
        JDBloquear = new javax.swing.JDialog();
        jPanel32 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        tipo = new javax.swing.JLabel();
        btnUsuarios = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        usuarioCaja = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        btnVender = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        txtDescripcionVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodVenta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cbTipoVenta = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Midate = new com.toedter.calendar.JDateChooser();
        jPanel23 = new javax.swing.JPanel();
        txtStockDisponible = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTicket = new javax.swing.JTable();
        txtTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGenerarVenta = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        txtCliente = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        dniCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnVaciarTicket = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblcodigo = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        txtcodigoClienteIndex = new javax.swing.JTextField();
        txtnombreClienteIndex = new javax.swing.JTextField();
        txttelefonoClienteIndex = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnaceptarClienteIndex = new javax.swing.JButton();
        btncancelarClienteIndex = new javax.swing.JButton();
        txtemailClienteIndex = new javax.swing.JTextField();
        lblgenero = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        cbcolClienteIndex = new javax.swing.JComboBox<>();
        cbmuniClienteIndex = new javax.swing.JComboBox<>();
        cbestadoClienteIndex = new javax.swing.JComboBox<>();
        cbgeneroClienteIndex = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbclientesIndex = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnnuevoClienteIndex = new javax.swing.JButton();
        btnmodificarClienteIndex = new javax.swing.JButton();
        btneliminarClienteIndex = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
        j1 = new javax.swing.JLabel();
        btnaceptarProveedorIndex = new javax.swing.JButton();
        txtCodigoProveedorIndex = new javax.swing.JTextField();
        btncancelarProveedorIndex = new javax.swing.JButton();
        txtemailProveedorIndex = new javax.swing.JTextField();
        j2 = new javax.swing.JLabel();
        txttelefonoProveedorIndex = new javax.swing.JTextField();
        j4 = new javax.swing.JLabel();
        txtEmpresaProveedorIndex = new javax.swing.JTextField();
        j5 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        pnlLista = new javax.swing.JPanel();
        btnnuevoProveedorIndex = new javax.swing.JButton();
        btneliminarProveedorIndex = new javax.swing.JButton();
        btnmodificarProveedorIndex = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtbuscarProveedorIndex = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbProveedorIndex = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        plista1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btnnuevoProductosIndex = new javax.swing.JButton();
        txtBuscarProductosIndex = new javax.swing.JTextField();
        btnmodificarProductosIndex = new javax.swing.JButton();
        btneliminarProductosIndex = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbProductosIndex = new javax.swing.JTable();
        pnlDatos2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        btnaceptarProductosIndex = new javax.swing.JButton();
        btncancelarProductosIndex = new javax.swing.JButton();
        pcontenido1 = new javax.swing.JPanel();
        txtnombreProductoIndex = new javax.swing.JTextField();
        sppclienteProductoIndex = new javax.swing.JSpinner();
        cbunidadProductoIndex = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtcodigoProductoIndex = new javax.swing.JTextField();
        spppublicoProductoIndex = new javax.swing.JSpinner();
        spcantidadProductosIndex = new javax.swing.JSpinner();
        cbproveedorProductosIndex = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabelCat = new javax.swing.JLabel();
        cbcategoriaProductosIndex = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        cbProductos = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        totalSaldoRecarga = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblIdUser = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableUsersIndex = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        j3 = new javax.swing.JLabel();
        j6 = new javax.swing.JLabel();
        j7 = new javax.swing.JLabel();
        j8 = new javax.swing.JLabel();
        txtCodigoUserIndex = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        txtNombreUserIndex = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        txtCorreoUserIndex = new javax.swing.JTextField();
        txtPassUserIndex = new javax.swing.JPasswordField();
        jTextField8 = new javax.swing.JTextField();
        j9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        txtConfirmPassUserIndex = new javax.swing.JPasswordField();
        cbRolUserIndex = new javax.swing.JComboBox<>();
        j10 = new javax.swing.JLabel();
        btnGuardarUserIndex = new javax.swing.JButton();
        btnEliminarUserIndex = new javax.swing.JButton();
        btnAddUserIndex = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtTelefonoConfig = new javax.swing.JTextField();
        txtDireccionConfig = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextField();
        btnActualizarConfig = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        txtRucConfig = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtNombreConfig = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        panelImg = new FondoPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevaVenta1 = new javax.swing.JButton();
        btnNuevaVenta2 = new javax.swing.JButton();
        bntBloquear = new javax.swing.JButton();
        sALIR = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setText("BUSCAR");

        txtclientesb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclientesbKeyReleased(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        jButton3.setText("SALIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(85, 112, 148));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SELECCION DE CLIENTES");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jpanelJDClientesTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelJDClientesTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogClientesLayout = new javax.swing.GroupLayout(jDialogClientes.getContentPane());
        jDialogClientes.getContentPane().setLayout(jDialogClientesLayout);
        jDialogClientesLayout.setHorizontalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogClientesLayout.setVerticalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel12.setText("BUSCAR");

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

        jPanel10.setBackground(new java.awt.Color(85, 112, 148));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SELECCION DE PRODUCTOS");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnsalir)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddProd)))))
                .addGap(19, 19, 19))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnaddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogProductoLayout = new javax.swing.GroupLayout(jDialogProducto.getContentPane());
        jDialogProducto.getContentPane().setLayout(jDialogProductoLayout);
        jDialogProductoLayout.setHorizontalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProductoLayout.setVerticalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        plista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        plista.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel14.setText("BUSCAR");

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
                        .addComponent(jLabel14)
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
                    .addComponent(jLabel14)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatos1.setBackground(new java.awt.Color(85, 112, 148));
        pnlDatos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel12.setBackground(new java.awt.Color(85, 112, 148));

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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btncancelar)
                .addContainerGap())
        );

        pcontenido.setBackground(new java.awt.Color(85, 112, 148));
        pcontenido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pcontenido.setForeground(new java.awt.Color(255, 255, 255));

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

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CODIGO");

        jLabel16.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("NOMBRE");

        jLabel17.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("UNIDAD MEDIDA");

        jLabel18.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("PRECIO CLIENTE");

        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodKeyReleased(evt);
            }
        });

        spppublico.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbproveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel19.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("PRECIO PUBLICO");

        jLabel20.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("CANTIDAD");

        jLabel21.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("PROVEEDOR");

        javax.swing.GroupLayout pcontenidoLayout = new javax.swing.GroupLayout(pcontenido);
        pcontenido.setLayout(pcontenidoLayout);
        pcontenidoLayout.setHorizontalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcontenidoLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel15))
                    .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(pcontenidoLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)))
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
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sppcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spppublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
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
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlDatos1Layout.setVerticalGroup(
            pnlDatos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatos1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel31.setBackground(new java.awt.Color(85, 112, 148));
        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlVenta.setBackground(new java.awt.Color(0, 111, 111));
        jlVenta.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jlVenta.setForeground(new java.awt.Color(255, 255, 255));
        jlVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Monto :");

        cbMontoCompa.setFont(new java.awt.Font("Noto Sans", 3, 12)); // NOI18N
        cbMontoCompa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        cbMontoCompa.setToolTipText("");
        cbMontoCompa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMontoCompaActionPerformed(evt);
            }
        });

        pnlVentas.setBackground(new java.awt.Color(85, 112, 148));
        pnlVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Numero:");

        txtNumero.setToolTipText("ingresa tu numero\n");
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Confirma:");

        txtConfirmarNumero.setToolTipText("confirmar tu numero\n");
        txtConfirmarNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarNumeroKeyReleased(evt);
            }
        });

        lblCostoProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoProducto.setText("Costo producto:");

        lblComisionServicio.setForeground(new java.awt.Color(255, 255, 255));
        lblComisionServicio.setText("Comision:");

        lblVigencia.setForeground(new java.awt.Color(255, 255, 255));
        lblVigencia.setText("Vigencia:");

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
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        btnRecargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/OK.png"))); // NOI18N
        btnRecargar.setText("RECARGAR");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });

        lblVigencia1.setForeground(new java.awt.Color(255, 255, 255));
        lblVigencia1.setText("Nombre producto :");

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
                        .addComponent(lblNota, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7))
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addGap(10, 10, 10)
                        .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmarNumero)
                            .addComponent(txtNumero)))))
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
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVentasLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel37))
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
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRecargar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMontoCompa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jlVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMontoCompa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdVenderSaldoLayout = new javax.swing.GroupLayout(jdVenderSaldo.getContentPane());
        jdVenderSaldo.getContentPane().setLayout(jdVenderSaldoLayout);
        jdVenderSaldoLayout.setHorizontalGroup(
            jdVenderSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdVenderSaldoLayout.setVerticalGroup(
            jdVenderSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel32.setBackground(new java.awt.Color(85, 112, 148));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Key.png"))); // NOI18N

        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JDBloquearLayout = new javax.swing.GroupLayout(JDBloquear.getContentPane());
        JDBloquear.getContentPane().setLayout(JDBloquearLayout);
        JDBloquearLayout.setHorizontalGroup(
            JDBloquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JDBloquearLayout.setVerticalGroup(
            JDBloquearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(85, 112, 148));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo_pdf.png"))); // NOI18N

        btnNuevaVenta.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaVenta.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.setFocusable(false);
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(0, 0, 0));
        btnClientes.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.setFocusable(false);
        btnClientes.setPreferredSize(new java.awt.Dimension(103, 37));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProveedor.setBackground(new java.awt.Color(0, 0, 0));
        btnProveedor.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedor.setText("Proveedor");
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedor.setFocusable(false);
        btnProveedor.setPreferredSize(new java.awt.Dimension(103, 37));
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnProductos.setBackground(new java.awt.Color(0, 0, 0));
        btnProductos.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.setFocusable(false);
        btnProductos.setPreferredSize(new java.awt.Dimension(103, 37));
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnConfig.setBackground(new java.awt.Color(0, 0, 0));
        btnConfig.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("Config");
        btnConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfig.setFocusable(false);
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        tipo.setForeground(new java.awt.Color(255, 255, 255));

        btnUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuarios.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar_user.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarios.setFocusable(false);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recargas", "Pago servicios" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        usuarioCaja.setForeground(new java.awt.Color(255, 255, 255));
        usuarioCaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
            .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(tipo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usuarioCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jComboBox2.getAccessibleContext().setAccessibleName("");

        btnVender.setBackground(new java.awt.Color(85, 112, 148));

        jPanel3.setBackground(new java.awt.Color(85, 112, 148));

        jPanel4.setBackground(new java.awt.Color(85, 112, 148));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtDescripcionVenta.setEditable(false);
        txtDescripcionVenta.setBackground(new java.awt.Color(85, 112, 148));
        txtDescripcionVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDescripcionVenta.setForeground(new java.awt.Color(255, 255, 0));
        txtDescripcionVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcionVenta.setBorder(null);
        txtDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionVentaKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Descripción");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Código");

        txtCodVenta.setBackground(new java.awt.Color(85, 112, 148));
        txtCodVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodVenta.setForeground(new java.awt.Color(255, 255, 0));
        txtCodVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodVenta.setBorder(null);
        txtCodVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodVentaKeyTyped(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR2.1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodVenta, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(cbTipoVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        jPanel5.setBackground(new java.awt.Color(85, 112, 148));

        txtCantidad.setBackground(new java.awt.Color(85, 112, 148));
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(255, 255, 0));
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setBorder(null);
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cant");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Precio");

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setBackground(new java.awt.Color(85, 112, 148));
        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(255, 255, 0));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVenta.setBorder(null);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(txtPrecioVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtCantidad)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(5, 5, 5)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        jPanel6.setBackground(new java.awt.Color(85, 112, 148));

        Midate.setBackground(new java.awt.Color(204, 204, 204));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        txtStockDisponible.setEditable(false);
        txtStockDisponible.setBackground(new java.awt.Color(85, 112, 148));
        txtStockDisponible.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStockDisponible.setForeground(new java.awt.Color(0, 255, 0));
        txtStockDisponible.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockDisponible.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Stock Disponible");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Midate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStockDisponible)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Midate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCIÓN", "CANTIDAD", "PRECIO U.", "PRECIO TOTAL"
            }
        ));
        tbTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTicketMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTicket);

        txtTotal.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(204, 204, 0));
        txtTotal.setText("0.0");

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel10.setText("Total a Pagar $:");

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        txtCliente.setEditable(false);
        txtCliente.setBackground(new java.awt.Color(85, 112, 148));
        txtCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCliente.setBorder(null);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        dniCliente.setBackground(new java.awt.Color(85, 112, 148));
        dniCliente.setForeground(new java.awt.Color(255, 255, 255));
        dniCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dniCliente.setBorder(null);
        dniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dniClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dniClienteKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dni");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nombre");

        btnVaciarTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cut.png"))); // NOI18N
        btnVaciarTicket.setText("Borrar registro");
        btnVaciarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnVenderLayout = new javax.swing.GroupLayout(btnVender);
        btnVender.setLayout(btnVenderLayout);
        btnVenderLayout.setHorizontalGroup(
            btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVenderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnVenderLayout.createSequentialGroup()
                        .addComponent(btnVaciarTicket)
                        .addGap(51, 51, 51)
                        .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnVenderLayout.createSequentialGroup()
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(btnVenderLayout.createSequentialGroup()
                                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dniCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGenerarVenta)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        btnVenderLayout.setVerticalGroup(
            btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnVenderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnVenderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(btnVenderLayout.createSequentialGroup()
                                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(btnVenderLayout.createSequentialGroup()
                                        .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(3, 3, 3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, btnVenderLayout.createSequentialGroup()
                                        .addComponent(btnVaciarTicket)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnGenerarVenta)))
                    .addGroup(btnVenderLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal))))
                .addGap(0, 0, 0)
                .addGroup(btnVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab("Ventas", btnVender);

        jPanel13.setBackground(new java.awt.Color(85, 112, 148));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblcodigo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblcodigo.setForeground(new java.awt.Color(254, 254, 254));
        lblcodigo.setText("       DNI :");

        lblnombre.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(254, 254, 254));
        lblnombre.setText("Nombre:");

        lbldireccion.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lbldireccion.setForeground(new java.awt.Color(254, 254, 254));
        lbldireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldireccion.setText("   Domicilio:");

        lbltelefono.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lbltelefono.setForeground(new java.awt.Color(254, 254, 254));
        lbltelefono.setText("Telefono:");

        txtcodigoClienteIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoClienteIndexKeyReleased(evt);
            }
        });

        txtnombreClienteIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreClienteIndexKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreClienteIndexKeyTyped(evt);
            }
        });

        txttelefonoClienteIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoClienteIndexKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoClienteIndexKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoClienteIndexKeyTyped(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(85, 112, 148));

        btnaceptarClienteIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptarClienteIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptarClienteIndex.setText("ACEPTAR");
        btnaceptarClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarClienteIndexActionPerformed(evt);
            }
        });

        btncancelarClienteIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btncancelarClienteIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelarClienteIndex.setText("CANCELAR");
        btncancelarClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarClienteIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnaceptarClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btncancelarClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptarClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btncancelarClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        txtemailClienteIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailClienteIndexKeyTyped(evt);
            }
        });

        lblgenero.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblgenero.setForeground(new java.awt.Color(254, 254, 254));
        lblgenero.setText("Genero:");

        lblemail.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblemail.setForeground(new java.awt.Color(254, 254, 254));
        lblemail.setText("Correo:");

        cbcolClienteIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbcolClienteIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcolClienteIndexMouseClicked(evt);
            }
        });
        cbcolClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcolClienteIndexActionPerformed(evt);
            }
        });

        cbmuniClienteIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbestadoClienteIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbgeneroClienteIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA", "HOMBRE", "MUJER" }));

        jLabel22.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(254, 254, 254));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CLIENTES");

        jTextField2.setMinimumSize(new java.awt.Dimension(6, 1));
        jTextField2.setPreferredSize(new java.awt.Dimension(1, 20));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblcodigo)
                            .addComponent(lblnombre)
                            .addComponent(lbltelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblgenero)
                            .addComponent(lbldireccion)
                            .addComponent(lblemail))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemailClienteIndex)
                            .addComponent(cbgeneroClienteIndex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(cbcolClienteIndex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbmuniClienteIndex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbestadoClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txttelefonoClienteIndex)
                            .addComponent(txtcodigoClienteIndex)
                            .addComponent(txtnombreClienteIndex)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lblcodigo))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcodigoClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombreClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblnombre))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbestadoClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmuniClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbcolClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbldireccion)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefonoClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltelefono))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbgeneroClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblgenero))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemailClienteIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblemail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(85, 112, 148));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tbclientesIndex = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbclientesIndex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbclientesIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesIndexMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbclientesIndex);

        jLabel23.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(253, 251, 251));
        jLabel23.setText("Buscar:");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        btnnuevoClienteIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevoClienteIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevoClienteIndex.setText("NUEVO");
        btnnuevoClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoClienteIndexActionPerformed(evt);
            }
        });

        btnmodificarClienteIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnmodificarClienteIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificarClienteIndex.setText("MODIFICAR");
        btnmodificarClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarClienteIndexActionPerformed(evt);
            }
        });

        btneliminarClienteIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btneliminarClienteIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminarClienteIndex.setText("ELIMINAR");
        btneliminarClienteIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarClienteIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(btnnuevoClienteIndex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnmodificarClienteIndex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btneliminarClienteIndex))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevoClienteIndex)
                    .addComponent(btnmodificarClienteIndex)
                    .addComponent(btneliminarClienteIndex))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cliente", jPanel2);

        pnlDatos.setBackground(new java.awt.Color(85, 112, 148));
        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        j1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j1.setForeground(new java.awt.Color(254, 254, 254));
        j1.setText("DNI :");

        btnaceptarProveedorIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptarProveedorIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptarProveedorIndex.setText("ACEPTAR");
        btnaceptarProveedorIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarProveedorIndexActionPerformed(evt);
            }
        });

        txtCodigoProveedorIndex.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtCodigoProveedorIndex.setMinimumSize(new java.awt.Dimension(8, 24));
        txtCodigoProveedorIndex.setPreferredSize(new java.awt.Dimension(8, 24));
        txtCodigoProveedorIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProveedorIndexKeyReleased(evt);
            }
        });

        btncancelarProveedorIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btncancelarProveedorIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelarProveedorIndex.setText("CANCELAR");
        btncancelarProveedorIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarProveedorIndexActionPerformed(evt);
            }
        });

        txtemailProveedorIndex.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtemailProveedorIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailProveedorIndexKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailProveedorIndexKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailProveedorIndexKeyTyped(evt);
            }
        });

        j2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j2.setForeground(new java.awt.Color(254, 254, 254));
        j2.setText("Nombre :");

        txttelefonoProveedorIndex.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txttelefonoProveedorIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoProveedorIndexKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoProveedorIndexKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoProveedorIndexKeyTyped(evt);
            }
        });

        j4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j4.setForeground(new java.awt.Color(254, 254, 254));
        j4.setText("Telefono :");

        txtEmpresaProveedorIndex.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtEmpresaProveedorIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpresaProveedorIndexKeyReleased(evt);
            }
        });

        j5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j5.setForeground(new java.awt.Color(254, 254, 254));
        j5.setText("Correo :");

        jLabel24.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(254, 254, 254));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("PROVEEDORES");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(j1)
                            .addComponent(j2)
                            .addComponent(j4)
                            .addComponent(j5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnaceptarProveedorIndex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 579, Short.MAX_VALUE)
                                .addComponent(btncancelarProveedorIndex))
                            .addComponent(txttelefonoProveedorIndex)
                            .addComponent(txtCodigoProveedorIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtemailProveedorIndex, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmpresaProveedorIndex))))
                .addGap(15, 15, 15))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j1))
                .addGap(4, 4, 4)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresaProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j2))
                .addGap(4, 4, 4)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefonoProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j4))
                .addGap(4, 4, 4)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemailProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j5))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnaceptarProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelarProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlLista.setBackground(new java.awt.Color(85, 112, 148));
        pnlLista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnnuevoProveedorIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevoProveedorIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevoProveedorIndex.setText("NUEVO");
        btnnuevoProveedorIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoProveedorIndexActionPerformed(evt);
            }
        });

        btneliminarProveedorIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btneliminarProveedorIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminarProveedorIndex.setText("ELIMINAR");
        btneliminarProveedorIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarProveedorIndexActionPerformed(evt);
            }
        });

        btnmodificarProveedorIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnmodificarProveedorIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificarProveedorIndex.setText("MODIFICAR");
        btnmodificarProveedorIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarProveedorIndexActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(254, 254, 254));
        jLabel25.setText("BUSCAR");

        txtbuscarProveedorIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarProveedorIndexKeyReleased(evt);
            }
        });

        tbProveedorIndex = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbProveedorIndex.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProveedorIndex.setEditingColumn(0);
        tbProveedorIndex.setEditingRow(0);
        tbProveedorIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProveedorIndexMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbProveedorIndex);

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(btnnuevoProveedorIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificarProveedorIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminarProveedorIndex)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscarProveedorIndex)))
                .addContainerGap())
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarProveedorIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btneliminarProveedorIndex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnmodificarProveedorIndex)
                        .addComponent(btnnuevoProveedorIndex)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Proveedores", jPanel16);

        plista1.setBackground(new java.awt.Color(85, 112, 148));
        plista1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        plista1.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(254, 254, 254));
        jLabel26.setText("Producto :");

        btnnuevoProductosIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnnuevoProductosIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevoProductosIndex.setText("NUEVO");
        btnnuevoProductosIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoProductosIndexActionPerformed(evt);
            }
        });

        txtBuscarProductosIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosIndexKeyReleased(evt);
            }
        });

        btnmodificarProductosIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnmodificarProductosIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificarProductosIndex.setText("MODIFICAR");
        btnmodificarProductosIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarProductosIndexActionPerformed(evt);
            }
        });

        btneliminarProductosIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btneliminarProductosIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminarProductosIndex.setText("ELIMINAR");
        btneliminarProductosIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneliminarProductosIndexMouseClicked(evt);
            }
        });
        btneliminarProductosIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarProductosIndexActionPerformed(evt);
            }
        });

        tbProductosIndex.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductosIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosIndexMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbProductosIndex);

        javax.swing.GroupLayout plista1Layout = new javax.swing.GroupLayout(plista1);
        plista1.setLayout(plista1Layout);
        plista1Layout.setHorizontalGroup(
            plista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plista1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addGroup(plista1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarProductosIndex))
                    .addGroup(plista1Layout.createSequentialGroup()
                        .addComponent(btnnuevoProductosIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificarProductosIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminarProductosIndex)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        plista1Layout.setVerticalGroup(
            plista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plista1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtBuscarProductosIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(plista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneliminarProductosIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodificarProductosIndex)
                    .addComponent(btnnuevoProductosIndex))
                .addContainerGap())
        );

        pnlDatos2.setBackground(new java.awt.Color(85, 112, 148));
        pnlDatos2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel18.setBackground(new java.awt.Color(85, 112, 148));

        btnaceptarProductosIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnaceptarProductosIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptarProductosIndex.setText("ACEPTAR");
        btnaceptarProductosIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarProductosIndexActionPerformed(evt);
            }
        });

        btncancelarProductosIndex.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btncancelarProductosIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelarProductosIndex.setText("CANCELAR");
        btncancelarProductosIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarProductosIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelarProductosIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaceptarProductosIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptarProductosIndex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btncancelarProductosIndex)
                .addContainerGap())
        );

        pcontenido1.setBackground(new java.awt.Color(85, 112, 148));
        pcontenido1.setForeground(new java.awt.Color(102, 255, 102));

        txtnombreProductoIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreProductoIndexKeyReleased(evt);
            }
        });

        sppclienteProductoIndex.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        sppclienteProductoIndex.setBorder(null);

        cbunidadProductoIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbunidadProductoIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbunidadProductoIndexActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(254, 254, 254));
        jLabel27.setText("Codigo :");

        jLabel28.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(254, 254, 254));
        jLabel28.setText("Nombre :");

        jLabel29.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(254, 254, 254));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Unidad De Medida :");

        jLabel30.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(254, 254, 254));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Precio a cliente :");

        txtcodigoProductoIndex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoProductoIndexKeyReleased(evt);
            }
        });

        spppublicoProductoIndex.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        spppublicoProductoIndex.setBorder(null);

        spcantidadProductosIndex.setBorder(null);

        cbproveedorProductosIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel31.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(254, 254, 254));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Precio a publico :");

        jLabel32.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(254, 254, 254));
        jLabel32.setText("Cantidad :");

        jLabel33.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(254, 254, 254));
        jLabel33.setText("Proveedor :");

        jLabelCat.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelCat.setForeground(new java.awt.Color(254, 254, 254));
        jLabelCat.setText("Categoria :");

        cbcategoriaProductosIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        javax.swing.GroupLayout pcontenido1Layout = new javax.swing.GroupLayout(pcontenido1);
        pcontenido1.setLayout(pcontenido1Layout);
        pcontenido1Layout.setHorizontalGroup(
            pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenido1Layout.createSequentialGroup()
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pcontenido1Layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jLabel28))
                        .addGroup(pcontenido1Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel32)
                                .addComponent(jLabel33)
                                .addGroup(pcontenido1Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addGap(3, 3, 3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenido1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCat, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(5, 5, 5)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnombreProductoIndex, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcodigoProductoIndex)
                    .addComponent(spcantidadProductosIndex, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbproveedorProductosIndex, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbcategoriaProductosIndex, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbunidadProductoIndex, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sppclienteProductoIndex, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spppublicoProductoIndex, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21))
        );
        pcontenido1Layout.setVerticalGroup(
            pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenido1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigoProductoIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombreProductoIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbunidadProductoIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sppclienteProductoIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spppublicoProductoIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spcantidadProductosIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbproveedorProductosIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCat)
                    .addComponent(cbcategoriaProductosIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel34.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(254, 254, 254));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("PRODUCTOS");

        javax.swing.GroupLayout pnlDatos2Layout = new javax.swing.GroupLayout(pnlDatos2);
        pnlDatos2.setLayout(pnlDatos2Layout);
        pnlDatos2Layout.setHorizontalGroup(
            pnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pcontenido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDatos2Layout.setVerticalGroup(
            pnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatos2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDatos2Layout.createSequentialGroup()
                        .addComponent(pcontenido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos", jPanel17);

        jPanel30.setBackground(new java.awt.Color(85, 112, 148));

        jLabel38.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(254, 254, 254));
        jLabel38.setText("Seleccione :");

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
        jScrollPane10.setViewportView(jTable1);

        jLabel39.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(254, 254, 254));
        jLabel39.setText("Bolsa Tiempo Aire : $");

        totalSaldoRecarga.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        totalSaldoRecarga.setForeground(new java.awt.Color(254, 254, 254));
        totalSaldoRecarga.setText("0");

        jLabel40.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(254, 254, 254));
        jLabel40.setText("Id Usuario: ");

        lblIdUser.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblIdUser.setForeground(new java.awt.Color(254, 254, 254));
        lblIdUser.setText("00001");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalSaldoRecarga)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdUser)
                .addContainerGap(269, Short.MAX_VALUE))
            .addComponent(jScrollPane10)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalSaldoRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("VentaRecargas", jPanel26);

        jPanel27.setBackground(new java.awt.Color(85, 112, 148));
        jPanel27.setForeground(new java.awt.Color(255, 255, 255));

        jTableUsersIndex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CONTRASEÑA", "CORREO", "ROL"
            }
        ));
        jTableUsersIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsersIndexMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableUsersIndex);

        jLabel42.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("USUARIOS");

        j3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j3.setForeground(new java.awt.Color(255, 255, 255));
        j3.setText("DNI");

        j6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j6.setForeground(new java.awt.Color(255, 255, 255));
        j6.setText("Nombre");

        j7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j7.setForeground(new java.awt.Color(255, 255, 255));
        j7.setText("Correo");

        j8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j8.setForeground(new java.awt.Color(255, 255, 255));
        j8.setText("Contraseña");

        txtCodigoUserIndex.setEditable(false);
        txtCodigoUserIndex.setBackground(new java.awt.Color(85, 112, 148));
        txtCodigoUserIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCodigoUserIndex.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigoUserIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoUserIndex.setText("00000");
        txtCodigoUserIndex.setBorder(null);

        jTextField4.setText("jTextField3");
        jTextField4.setMinimumSize(new java.awt.Dimension(6, 4));
        jTextField4.setPreferredSize(new java.awt.Dimension(5, 20));

        txtNombreUserIndex.setBackground(new java.awt.Color(85, 112, 148));
        txtNombreUserIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNombreUserIndex.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreUserIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreUserIndex.setBorder(null);

        jTextField6.setText("jTextField3");
        jTextField6.setMinimumSize(new java.awt.Dimension(6, 4));
        jTextField6.setPreferredSize(new java.awt.Dimension(5, 20));

        txtCorreoUserIndex.setBackground(new java.awt.Color(85, 112, 148));
        txtCorreoUserIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCorreoUserIndex.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreoUserIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreoUserIndex.setBorder(null);

        txtPassUserIndex.setBackground(new java.awt.Color(85, 112, 148));
        txtPassUserIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPassUserIndex.setForeground(new java.awt.Color(255, 255, 255));
        txtPassUserIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassUserIndex.setBorder(null);

        jTextField8.setText("jTextField8");

        j9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j9.setForeground(new java.awt.Color(255, 255, 255));
        j9.setText("Confirma contraseña");

        jTextField9.setText("jTextField8");

        txtConfirmPassUserIndex.setBackground(new java.awt.Color(85, 112, 148));
        txtConfirmPassUserIndex.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtConfirmPassUserIndex.setForeground(new java.awt.Color(255, 255, 255));
        txtConfirmPassUserIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConfirmPassUserIndex.setBorder(null);

        cbRolUserIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));

        j10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        j10.setForeground(new java.awt.Color(255, 255, 255));
        j10.setText("Rol ");

        btnGuardarUserIndex.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarUserIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnGuardarUserIndex.setText("Guardar");
        btnGuardarUserIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarUserIndex.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuardarUserIndex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarUserIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUserIndexActionPerformed(evt);
            }
        });

        btnEliminarUserIndex.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarUserIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btnEliminarUserIndex.setText("Eliminar");
        btnEliminarUserIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarUserIndex.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEliminarUserIndex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminarUserIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserIndexActionPerformed(evt);
            }
        });

        btnAddUserIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Create.png"))); // NOI18N
        btnAddUserIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreUserIndex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorreoUserIndex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtConfirmPassUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(j3)
                                .addComponent(j6)
                                .addComponent(j7)
                                .addComponent(j8)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(j9)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel27Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(j10))))
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(cbRolUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtCodigoUserIndex)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardarUserIndex)
                        .addGap(104, 104, 104)
                        .addComponent(btnEliminarUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(j3)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddUserIndex))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j6)
                        .addGap(0, 0, 0)
                        .addComponent(txtNombreUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j7)
                        .addGap(0, 0, 0)
                        .addComponent(txtCorreoUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j8)
                        .addGap(0, 0, 0)
                        .addComponent(txtPassUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j9)
                        .addGap(0, 0, 0)
                        .addComponent(txtConfirmPassUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(j10)
                        .addGap(3, 3, 3)
                        .addComponent(cbRolUserIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEliminarUserIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarUserIndex)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
                .addGap(118, 118, 118))
        );

        jTabbedPane1.addTab("ReporteVentas", jPanel27);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("DATOS DE LA EMPRESA");

        jPanel33.setBackground(new java.awt.Color(204, 204, 204));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Dirección");
        jPanel33.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 150, -1));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Teléfono");
        jPanel33.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 220, -1));

        txtTelefonoConfig.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoConfig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefonoConfig.setBorder(null);
        jPanel33.add(txtTelefonoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 218, 30));

        txtDireccionConfig.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccionConfig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccionConfig.setBorder(null);
        jPanel33.add(txtDireccionConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 147, 30));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Mensaje");
        jPanel33.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 400, -1));

        txtMensaje.setBackground(new java.awt.Color(204, 204, 204));
        txtMensaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMensaje.setBorder(null);
        jPanel33.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 400, 30));

        btnActualizarConfig.setBackground(new java.awt.Color(0, 110, 255));
        btnActualizarConfig.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnActualizarConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnActualizarConfig.setText("GUARDAR DATOS");
        btnActualizarConfig.setBorder(null);
        btnActualizarConfig.setFocusable(false);
        btnActualizarConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarConfigActionPerformed(evt);
            }
        });
        jPanel33.add(btnActualizarConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 180, 35));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Ruc");
        jPanel33.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, -1));

        txtRucConfig.setBackground(new java.awt.Color(204, 204, 204));
        txtRucConfig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRucConfig.setBorder(null);
        jPanel33.add(txtRucConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 147, 30));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Nombre");
        jPanel33.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 220, -1));

        txtNombreConfig.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreConfig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreConfig.setBorder(null);
        jPanel33.add(txtNombreConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 220, 30));

        jPanel41.setBackground(new java.awt.Color(0, 110, 255));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel33.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 147, 2));

        jPanel42.setBackground(new java.awt.Color(0, 110, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel33.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 147, 2));

        jPanel43.setBackground(new java.awt.Color(0, 110, 255));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel33.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 400, 2));

        jPanel44.setBackground(new java.awt.Color(0, 110, 255));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel33.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 220, 2));

        jPanel45.setBackground(new java.awt.Color(0, 110, 255));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel33.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 220, 2));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/empresa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel43)
                .addGap(32, 32, 32)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VentaServicios", jPanel29);

        lblTitulo.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("jLabel2");

        btnNuevaVenta1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnNuevaVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/reporte-de-negocios.png"))); // NOI18N
        btnNuevaVenta1.setText("Reporte productos");
        btnNuevaVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta1.setFocusable(false);
        btnNuevaVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVenta1ActionPerformed(evt);
            }
        });

        btnNuevaVenta2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnNuevaVenta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VENTASS (1).png"))); // NOI18N
        btnNuevaVenta2.setText("Reporte Ventas");
        btnNuevaVenta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta2.setFocusable(false);
        btnNuevaVenta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVenta2ActionPerformed(evt);
            }
        });

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

        sALIR.setFont(new java.awt.Font("Noto Sans", 1, 10)); // NOI18N
        sALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Exit1.png"))); // NOI18N
        sALIR.setText("Salir");
        sALIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sALIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sALIRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevaVenta2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevaVenta1)
                .addGap(18, 18, 18)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntBloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelImgLayout.setVerticalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImgLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bntBloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE)
                    .addGroup(panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevaVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevaVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sALIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                    .addComponent(panelImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTabbedPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        limpiarPrincipal(true);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        ListarClientes("");
        llenarCombosClientes();
        desabilitarControlerCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        carga_informacion_Prov("");
        DesabilitarControlesProveedor();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked

    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        desabPanContenidoProductos();
        llenarUnidades(cbunidadProductoIndex);
        llenarProveedores(cbproveedorProductosIndex);
        llenarCategoria();
        llenarTbProductos("", 2);
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        // TODO add your handling code here:
        leerDatosNegocio();
        jTabbedPane1.setSelectedIndex(6);

    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        cargarUsuarios("");
        desabilitarControlesUsuarios();
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void txtclientesbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclientesbKeyReleased
        this.txtclientesb.setText(this.txtclientesb.getText().toUpperCase());
        cargarTBC(txtclientesb.getText());
    }//GEN-LAST:event_txtclientesbKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jDialogClientes.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientes1MouseClicked
        int fs = tbClientes1.getSelectedRow();
        if (fs >= 0) {
            txtCliente.setText(tbClientes1.getValueAt(fs, 1).toString());
            codCliente = tbClientes1.getValueAt(fs, 0).toString();
            dniCliente.setText(codCliente);
            cbTipoVenta.setEnabled(false);
            jDialogClientes.setVisible(false);
        }
    }//GEN-LAST:event_tbClientes1MouseClicked

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
            txtCodVenta.setText(tbProductos.getValueAt(fs, 0).toString());
            txtDescripcionVenta.setText(tbProductos.getValueAt(fs, 1).toString());
            txtStockDisponible.setText(tbProductos.getValueAt(fs, 5).toString());
            if (cbTipoVenta.getSelectedItem().toString().toUpperCase().contains("CLIENT")) {
                txtPrecioVenta.setText(tbProductos.getValueAt(fs, 3).toString());
            } else {
                txtPrecioVenta.setText(tbProductos.getValueAt(fs, 2).toString());
            }
            if (Integer.parseInt(tbProductos.getValueAt(fs, 5).toString()) <= 0) {
                JOptionPane.showMessageDialog(null, "¡¡¡VERIFIQUE SU EXISTENCIA!!!", "", JOptionPane.WARNING_MESSAGE);
            } else {
                jDialogProducto.setVisible(false);
                btnAdd.setEnabled(true);
                txtCantidad.setEditable(true);
            }

        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btnaddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddProdActionPerformed
        desabComponentesInit();
        productosView();
    }//GEN-LAST:event_btnaddProdActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarTbProductos(txtBuscar.getText(),3);
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
                llenarTbProductos("", 2);
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

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        System.out.println("se minimizo");
    }//GEN-LAST:event_formWindowIconified

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        setMenuBar();
    }//GEN-LAST:event_formComponentResized

    ActionListener ac;
    ProductsDTO productos = null;//new ProductsDTO();

    String comision = "", vigencia = "", nota = "", compania = "";
    metodosHTTP metodos = new metodosHTTP();
    Gson gson = new Gson();
    Timer time;
    int x = 0;
    JDialog dialogov;
    conexionhttpLocal locaService = new conexionhttpLocal();
    private void cbMontoCompaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMontoCompaActionPerformed
        if (cbMontoCompa.getSelectedIndex() > 0) {
            btnRecargar.setEnabled(true);
            try {
                String[] cadenaServ = jlVenta.getText().split(",");
                setDetallesRecargas(cadenaServ[1].toUpperCase(), cadenaServ[0].toUpperCase());
            } catch (Exception e) {
                System.out.println("Error al obtener desc:" + e.getMessage());
            }
        } else {
            btnRecargar.setEnabled(false);
        }

    }//GEN-LAST:event_cbMontoCompaActionPerformed

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        new MetodosValidar().soloNumeros(txtNumero, 10);
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void txtConfirmarNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarNumeroKeyReleased
        new MetodosValidar().soloNumeros(txtConfirmarNumero, 10);
    }//GEN-LAST:event_txtConfirmarNumeroKeyReleased

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
                                leerSaldoRecargas();
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

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox2.getSelectedItem().toString().toUpperCase().contains("RECARGA")) {
            // if (leerSaldoRecargas()) {
            btnRecargar.setEnabled(false);
            jTabbedPane1.setSelectedIndex(4);
            //}
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void btnNuevaVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVenta1ActionPerformed
        FReporteProductos1 rpv = new FReporteProductos1();
        rpv.setSize(1000, 600);
        try {
            JDialog dialogov = new JDialog(rpv, "REPORTES PRODUCTOS", true);
            dialogov.add(rpv.getContentPane());
            dialogov.setSize(1000, 600);
            dialogov.setLocationRelativeTo(null);
            dialogov.setVisible(true);
            dialogov.setModal(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL VISUALIZAR REPORTE VENTAS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnNuevaVenta1ActionPerformed

    private void btnNuevaVenta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVenta2ActionPerformed
        FReporteVentas1 rpv = new FReporteVentas1();
        rpv.setSize(900, 600);
        try {
            JDialog dialogo = new JDialog(rpv, "REPORTES VENTAS", false);
            dialogo.add(rpv.getContentPane());
            dialogo.setModal(true);
            dialogo.setSize(900, 600);
            dialogo.setLocationRelativeTo(null);
            dialogo.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL VISUALIZAR REPORTE VENTAS", "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnNuevaVenta2ActionPerformed

    private void bntBloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBloquearActionPerformed
        bloquear();
    }//GEN-LAST:event_bntBloquearActionPerformed

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        try {
            llenarComboProductosRecargas(jTable1.getValueAt(fila, 0).toString(), cbProductos.getSelectedItem().toString().toUpperCase());
            jdVenderSaldo.setTitle("DETALLES RECARGA");
            jdVenderSaldo.setSize(350, 428);
            jdVenderSaldo.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Message.png")).getImage());
            jdVenderSaldo.setLocationRelativeTo(null);
            ///Border bordejpanel = new TitledBorder(new EtchedBorder(), jTable1.getValueAt(fila, 0).toString());
            //pnlVentas.setBorder(bordejpanel);
            jlVenta.setText(cbProductos.getSelectedItem() + "," + jTable1.getValueAt(fila, 0).toString());
            jdVenderSaldo.setVisible(true);
            jLabelCodProducto.setText(cbMontoCompa.getSelectedItem().toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductosActionPerformed
        jTable1.removeAll();
        if (cbProductos.getSelectedIndex() == 0) {
            loading.setSize(430, 90);
            loading.setLocationRelativeTo(null);
            loading.setVisible(true);
            loading.setModal(true);
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
                    jTable1.setDefaultRenderer(Object.class,
                            new ImageTable());
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
            loading.setModal(true);
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
                    jTable1
                            .setDefaultRenderer(Object.class,
                                    new ImageTable());
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
        //leerSaldoRecargas();
    }//GEN-LAST:event_cbProductosActionPerformed

    private void txtcodigoProductoIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoProductoIndexKeyReleased

    }//GEN-LAST:event_txtcodigoProductoIndexKeyReleased

    private void cbunidadProductoIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbunidadProductoIndexActionPerformed

    }//GEN-LAST:event_cbunidadProductoIndexActionPerformed

    private void txtnombreProductoIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreProductoIndexKeyReleased
        this.txtnombreProductoIndex.setText(this.txtnombreProductoIndex.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreProductoIndexKeyReleased

    private void btncancelarProductosIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarProductosIndexActionPerformed
        desabPanContenidoProductos();
        limpiarControlesProducto();
        this.btnnuevoClienteIndex.setEnabled(true);
        this.btnmodificarClienteIndex.setEnabled(false);
        this.btneliminarClienteIndex.setEnabled(false);
    }//GEN-LAST:event_btncancelarProductosIndexActionPerformed

    private void btnaceptarProductosIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarProductosIndexActionPerformed
        try {
            ProductoDTO pr = new ProductoDTO();
            double pad = 0;
            if (valEntradasProductos() == true) {
                int confirmar2 = JOptionPane.showConfirmDialog(null, "¿INGRESAR PRECIO DE AQUISICION?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar2 == JOptionPane.YES_OPTION) {
                    pad = Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
                }
                int confirmar = JOptionPane.showConfirmDialog(null, "¿SUS DATOS SON CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    pr.setIdproducto(Integer.parseInt(txtcodigoProductoIndex.getText()));
                    pr.setNombre(txtnombreProductoIndex.getText());
                    pr.setPrecio(Double.parseDouble(spppublicoProductoIndex.getValue().toString()));
                    if (Double.parseDouble(sppclienteProductoIndex.getValue().toString()) == 0.0) {
                        pr.setPreciocliente(0.0);
                        pr.setActivarpreciocliente(false);
                    } else {
                        pr.setPreciocliente(Double.parseDouble(sppclienteProductoIndex.getValue().toString()));
                        pr.setActivarpreciocliente(true);
                    }
                    pr.setStock(Integer.parseInt(spcantidadProductosIndex.getValue().toString()));
                    pr.setPreciodeproveedor(pad);

                    CategoriaDTO cat = categoriaController.categoriaByNombre(cbcategoriaProductosIndex.getSelectedItem().toString());
                    pr.setIdcategoria(cat.getIdcategoria());
                    ProveedorDTO prove = proveedoresController.proveedorByNombre(cbproveedorProductosIndex.getSelectedItem().toString());
                    pr.setIdproveedor(prove.getIdproveedor());
                    UnidadesMedidaDTO uni = unidadesController.unidadByNombre(cbunidadProductoIndex.getSelectedItem().toString());
                    pr.setIdunidadm(uni.getIdunidadm());
                    boolean bandera = productoController.save(pr);
                    if (bandera) {
                        new MetodosValidar().ok();
                        limpiarControlesProducto();
                        llenarTbProductos("", 2);
                        desabPanContenidoProductos();

                    } else {
                        new MetodosValidar().error();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AL GUARDAR PRODUCTO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnaceptarProductosIndexActionPerformed

    private void tbProductosIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosIndexMouseClicked
        EventoTbProductMC();
    }//GEN-LAST:event_tbProductosIndexMouseClicked

    private void btneliminarProductosIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarProductosIndexActionPerformed
        try {
            boolean ba = productoController.detelete(Integer.parseInt(txtcodigoProductoIndex.getText()));
            int conf = JOptionPane.showConfirmDialog(null, "¿ELIMINAR REGISTROS?", "", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                if (ba) {
                    new MetodosValidar().ok_eliminar();
                    desabPanContenidoProductos();
                    limpiarControlesProducto();
                    llenarTbProductos("", 2);
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar productos:" + e.getMessage());
        }
    }//GEN-LAST:event_btneliminarProductosIndexActionPerformed

    private void btneliminarProductosIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminarProductosIndexMouseClicked

    }//GEN-LAST:event_btneliminarProductosIndexMouseClicked

    private void btnmodificarProductosIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarProductosIndexActionPerformed
        try {
            ProductoDTO pr = new ProductoDTO();
            double pad = 0;
            if (valEntradasProductos() == true) {
                int confirmar2 = JOptionPane.showConfirmDialog(null, "¿INGRESAR PRECIO DE AQUISICION?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar2 == JOptionPane.YES_OPTION) {
                    pad = Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
                }
                int confirmar = JOptionPane.showConfirmDialog(null, "¿MDIFICAR?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    pr.setIdproducto(Integer.parseInt(txtcodigoProductoIndex.getText()));
                    pr.setNombre(txtnombreProductoIndex.getText());
                    pr.setPrecio(Double.parseDouble(spppublicoProductoIndex.getValue().toString()));
                    if (Double.parseDouble(sppclienteProductoIndex.getValue().toString()) == 0.0) {
                        pr.setPreciocliente(0.0);
                        pr.setActivarpreciocliente(false);
                    } else {
                        pr.setPreciocliente(Double.parseDouble(sppclienteProductoIndex.getValue().toString()));
                        pr.setActivarpreciocliente(true);
                    }
                    pr.setStock(Integer.parseInt(spcantidadProductosIndex.getValue().toString()));
                    pr.setPreciodeproveedor(pad);

                    CategoriaDTO cat = categoriaController.categoriaByNombre(cbcategoriaProductosIndex.getSelectedItem().toString());
                    pr.setIdcategoria(cat.getIdcategoria());
                    ProveedorDTO prove = proveedoresController.proveedorByNombre(cbproveedorProductosIndex.getSelectedItem().toString());
                    pr.setIdproveedor(prove.getIdproveedor());
                    UnidadesMedidaDTO uni = unidadesController.unidadByNombre(cbunidadProductoIndex.getSelectedItem().toString());
                    pr.setIdunidadm(uni.getIdunidadm());
                    boolean bandera = productoController.update(pr);
                    if (bandera) {
                        new MetodosValidar().ok_modificar();
                        limpiarControlesProducto();
                        llenarTbProductos("", 2);
                        desabPanContenidoProductos();
                    } else {
                        new MetodosValidar().error_modificar();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AL GUARDAR PRODUCTO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnmodificarProductosIndexActionPerformed

    private void txtBuscarProductosIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosIndexKeyReleased
        llenarTbProductos(txtBuscarProductosIndex.getText(), 2);
        txtBuscarProductosIndex.setText(this.txtBuscarProductosIndex.getText().toUpperCase());

    }//GEN-LAST:event_txtBuscarProductosIndexKeyReleased

    private void btnnuevoProductosIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoProductosIndexActionPerformed
        habPanContenidoProductos();
        generarId(3);
    }//GEN-LAST:event_btnnuevoProductosIndexActionPerformed

    private void tbProveedorIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProveedorIndexMouseClicked
        int filaS = tbProveedorIndex.getSelectedRow();
        if (filaS >= 0) {
            habFieldComponentesProveedor();
            btneliminarProveedorIndex.setEnabled(true);
            btnmodificarProveedorIndex.setEnabled(true);
            btnnuevoProveedorIndex.setEnabled(false);
            btnaceptarProveedorIndex.setEnabled(false);
            txtCodigoProveedorIndex.setText(tbProveedorIndex.getValueAt(filaS, 0).toString());
            txtEmpresaProveedorIndex.setText(tbProveedorIndex.getValueAt(filaS, 1).toString());
            txttelefonoProveedorIndex.setText(tbProveedorIndex.getValueAt(filaS, 3).toString());
            txtemailProveedorIndex.setText(tbProveedorIndex.getValueAt(filaS, 2).toString());

        }
    }//GEN-LAST:event_tbProveedorIndexMouseClicked

    private void txtbuscarProveedorIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarProveedorIndexKeyReleased
        carga_informacion_Prov(txtbuscar.getText());
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtbuscarProveedorIndexKeyReleased

    private void btnmodificarProveedorIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarProveedorIndexActionPerformed
        ProveedorDTO prov = new ProveedorDTO();
        if (valEntradasProveedor() == true) {
            try {
                prov.setIdproveedor(Integer.parseInt(txtCodigoProveedorIndex.getText()));
                prov.setNombre(txtEmpresaProveedorIndex.getText());
                prov.setTelefono(txttelefonoProveedorIndex.getText());
                prov.setMail(txtemailProveedorIndex.getText());
                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    boolean bandera = proveedoresController.update(prov);
                    if (bandera) {
                        new MetodosValidar().ok_modificar();
                        carga_informacion_Prov("");
                        limpiarControlesProveedor();
                        DesabilitarControlesProveedor();
                    } else {
                        new MetodosValidar().error_modificar();
                    }

                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_btnmodificarProveedorIndexActionPerformed

    private void btneliminarProveedorIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarProveedorIndexActionPerformed

        try {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿ELIMINAR DATOS?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_NO_OPTION) {
                boolean b = proveedoresController.delete(Integer.parseInt(txtCodigoProveedorIndex.getText()));
                if (b) {
                    DesabilitarControlesProveedor();
                    limpiarControlesProveedor();
                    btnmodificarProveedorIndex.setEnabled(false);
                    btneliminarProveedorIndex.setEnabled(false);
                    carga_informacion_Prov("");
                    new MetodosValidar().ok_eliminar();
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al eliminar:" + ex.getMessage());
        }
    }//GEN-LAST:event_btneliminarProveedorIndexActionPerformed

    private void btnnuevoProveedorIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoProveedorIndexActionPerformed
        limpiarControlesProveedor();
        habFieldComponentesProveedor();
        this.btneliminarProveedorIndex.setEnabled(false);
        this.btnmodificarProveedorIndex.setEnabled(false);
        this.btnnuevoProveedorIndex.setEnabled(false);

        generarId(2);
    }//GEN-LAST:event_btnnuevoProveedorIndexActionPerformed

    private void txtEmpresaProveedorIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaProveedorIndexKeyReleased
        txtEmpresaProveedorIndex.setText(this.txtEmpresaProveedorIndex.getText().toUpperCase());
    }//GEN-LAST:event_txtEmpresaProveedorIndexKeyReleased

    private void txttelefonoProveedorIndexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoProveedorIndexKeyTyped

    }//GEN-LAST:event_txttelefonoProveedorIndexKeyTyped

    private void txttelefonoProveedorIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoProveedorIndexKeyReleased
        new MetodosValidar().soloNumeros(txttelefonoClienteIndex, 13);
    }//GEN-LAST:event_txttelefonoProveedorIndexKeyReleased

    private void txttelefonoProveedorIndexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoProveedorIndexKeyPressed
        new MetodosValidar().soloNumeros(txttelefonoProveedorIndex, 10);
    }//GEN-LAST:event_txttelefonoProveedorIndexKeyPressed

    private void txtemailProveedorIndexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailProveedorIndexKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'A' || car > 'Z') && (car < 'a' || car > 'z') && (car < '@' || car > '@') && (car < '_' || car > '_') && (car < '.' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtemailProveedorIndexKeyTyped

    private void txtemailProveedorIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailProveedorIndexKeyReleased
        this.txtemailClienteIndex.setText(txtemailClienteIndex.getText().toLowerCase());       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailProveedorIndexKeyReleased

    private void txtemailProveedorIndexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailProveedorIndexKeyPressed
        //        MetodosValidar.soloNumeros(txttelefono, 14);
    }//GEN-LAST:event_txtemailProveedorIndexKeyPressed

    private void btncancelarProveedorIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarProveedorIndexActionPerformed
        DesabilitarControlesProveedor();
        limpiarControlesProveedor();
    }//GEN-LAST:event_btncancelarProveedorIndexActionPerformed

    private void txtCodigoProveedorIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProveedorIndexKeyReleased
        //MetodosValidar.soloNumeros(txtcodigo, 6);
    }//GEN-LAST:event_txtCodigoProveedorIndexKeyReleased

    private void btnaceptarProveedorIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarProveedorIndexActionPerformed
        ProveedorDTO prov = new ProveedorDTO();
        if (valEntradasProveedor() == true) {
            try {
                prov.setIdproveedor(Integer.parseInt(txtCodigoProveedorIndex.getText()));
                prov.setNombre(txtEmpresaProveedorIndex.getText());
                prov.setMail(txtemailProveedorIndex.getText());
                prov.setTelefono(txttelefonoProveedorIndex.getText());

                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    boolean guardar = proveedoresController.save(prov);
                    if (guardar) {
                        new MetodosValidar().ok();
                        carga_informacion_Prov("");
                        limpiarControlesProveedor();
                        DesabilitarControlesProveedor();
                    } else {
                        new MetodosValidar().error();
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al registrar proveedor:" + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnaceptarProveedorIndexActionPerformed

    private void btneliminarClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarClienteIndexActionPerformed
        eliminar();
    }//GEN-LAST:event_btneliminarClienteIndexActionPerformed

    private void btnmodificarClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarClienteIndexActionPerformed
        if (valEntradasCliente()) {
            modificar();
        }
    }//GEN-LAST:event_btnmodificarClienteIndexActionPerformed

    private void btnnuevoClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoClienteIndexActionPerformed
        this.btnnuevoClienteIndex.setEnabled(false);
        habilitarControlesCliente();
        generarId(1);
    }//GEN-LAST:event_btnnuevoClienteIndexActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
        ListarClientes(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void tbclientesIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesIndexMouseClicked
        EventMouseClick();
        btncancelarClienteIndex.setEnabled(true);
        btnaceptarClienteIndex.setEnabled(false);
    }//GEN-LAST:event_tbclientesIndexMouseClicked

    private void cbcolClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcolClienteIndexActionPerformed

    }//GEN-LAST:event_cbcolClienteIndexActionPerformed

    private void cbcolClienteIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcolClienteIndexMouseClicked

    }//GEN-LAST:event_cbcolClienteIndexMouseClicked

    private void txtemailClienteIndexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailClienteIndexKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < 'A' || car > 'Z') && (car < 'a' || car > 'z') && (car < '@' || car > '@') && (car < '_' || car > '_') && (car < '.' || car > '.')) {
            evt.consume();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailClienteIndexKeyTyped

    private void btncancelarClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarClienteIndexActionPerformed
        desabilitarControlerCliente();
        limpiarControlesCliente();
        this.btnnuevoClienteIndex.setEnabled(true);
    }//GEN-LAST:event_btncancelarClienteIndexActionPerformed

    private void btnaceptarClienteIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarClienteIndexActionPerformed
        if (valEntradasCliente() == true) {
            guardarClientes();
        }
    }//GEN-LAST:event_btnaceptarClienteIndexActionPerformed

    private void txttelefonoClienteIndexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoClienteIndexKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            if (txttelefonoClienteIndex.getText().length() < 10) {
                evt.consume();
            }
        }       // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoClienteIndexKeyTyped

    private void txttelefonoClienteIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoClienteIndexKeyReleased
        //     MetodosValidar.soloNumeros(txttelefono,13);
    }//GEN-LAST:event_txttelefonoClienteIndexKeyReleased

    private void txttelefonoClienteIndexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoClienteIndexKeyPressed
        new MetodosValidar().soloNumeros(txttelefonoClienteIndex, 10);
    }//GEN-LAST:event_txttelefonoClienteIndexKeyPressed

    private void txtnombreClienteIndexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreClienteIndexKeyTyped
        // MetodosValidar.soloLetrasNumeros(txtnombre, 100);
    }//GEN-LAST:event_txtnombreClienteIndexKeyTyped

    private void txtnombreClienteIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreClienteIndexKeyReleased
        this.txtnombreClienteIndex.setText(txtnombreClienteIndex.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreClienteIndexKeyReleased

    private void txtcodigoClienteIndexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoClienteIndexKeyReleased
        //  MetodosValidar.soloNumeros(txtcodigo,6);
    }//GEN-LAST:event_txtcodigoClienteIndexKeyReleased

    private void dniClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dniClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dniClienteKeyTyped

    private void dniClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dniClienteKeyPressed

    }//GEN-LAST:event_dniClienteKeyPressed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        generarVenta();

    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!txtCantidad.getText().equals("") && Integer.parseInt(txtCantidad.getText().trim()) > 0) {
            ProductoDTO prod = productoController.productoById(Integer.parseInt(txtCodVenta.getText()));
            if (prod.getStock() >= Integer.parseInt(txtCantidad.getText())) {

                int totalTicket = totalEnTicket(Integer.parseInt(txtCodVenta.getText()));
                if ((totalTicket + Integer.parseInt(txtCantidad.getText())) <= prod.getStock()) {
                    insertarDatosTicket();
                    btnVender.setEnabled(true);
                    txtCantidad.setText("");
                    limpiarPrincipal(false);
                    btnGenerarVenta.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "TOTAL DE EXISTENCIAS PARA " + prod.getNombre() + " es:" + prod.getStock(), "", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                new MetodosValidar().advertencia("VERIFIQUE EXISTENCIAS");
            }
        } else {
            JOptionPane.showMessageDialog(null, "INGRESE CANTIDAD CORRECTA", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            if (!txtCantidad.getText().trim().equals("") && Integer.parseInt(txtCantidad.getText().trim()) > 0) {
                ProductoDTO prod = productoController.productoById(Integer.parseInt(txtCodVenta.getText()));
                if (prod.getStock() >= Integer.parseInt(txtCantidad.getText())) {
                    int totalTicket = totalEnTicket(Integer.parseInt(txtCodVenta.getText()));
                    if ((totalTicket + Integer.parseInt(txtCantidad.getText())) <= prod.getStock()) {
                        insertarDatosTicket();
                        txtCantidad.setText("");
                        limpiarPrincipal(false);
                        btnGenerarVenta.setEnabled(true);
                        txtCantidad.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "TOTAL DE EXISTENCIAS PARA " + prod.getNombre() + " es:" + prod.getStock(), "", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    new MetodosValidar().advertencia("VERIFIQUE EXISTENCIAS");

                }
            } else {
                JOptionPane.showMessageDialog(null, "INGRESE CANTIDAD CORRECTA", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        new MetodosValidar().soloNumeros(txtCantidad, 3);
    }//GEN-LAST:event_txtCantidadKeyPressed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarTBP("");
        try {
            jDialogProducto.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Shopping_cart.png")).getImage());
            jDialogProducto.setModal(true);
            jDialogProducto.setTitle("LISTA PRODUCTOS");
            jDialogProducto.setSize(580, 420);
            jDialogProducto.setLocationRelativeTo(null);
            jDialogProducto.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
        txtCantidad.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCodVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVentaKeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER && !txtCodVenta.getText().equals("")) {
            ProductoDTO prod = productoController.productoById(Integer.parseInt(txtCodVenta.getText()));
            if (prod.getIdproducto() != null) {
                txtDescripcionVenta.setText(prod.getNombre());
                if (cbTipoVenta.getSelectedItem().toString().toUpperCase().contains("CLIEN")) {
                    txtPrecioVenta.setText(String.valueOf(prod.getPreciocliente()));
                } else {
                    txtPrecioVenta.setText(String.valueOf(prod.getPrecio()));
                }
                txtStockDisponible.setText(String.valueOf(prod.getStock()));
                txtCantidad.requestFocus();
                btnAdd.setEnabled(true);
                if (prod.getStock() > 0) {
                    txtCantidad.setText("0");
                    txtCantidad.setEditable(true);
                    txtCantidad.setEditable(true);
                    //limpiarPrincipal(false);
                } else {
                    new MetodosValidar().advertencia("VERIFIQUE EXISTENCIAS");
                }
            } else {
                JOptionPane.showMessageDialog(null, "PRODUCTO NO EXISTE", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCodVentaKeyTyped

    private void txtCodVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVentaKeyPressed
        new MetodosValidar().soloNumeros(txtCodVenta, 10);
    }//GEN-LAST:event_txtCodVentaKeyPressed

    private void txtDescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionVentaKeyTyped

    }//GEN-LAST:event_txtDescripcionVentaKeyTyped

    private void tbTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTicketMouseClicked
        btnVaciarTicket.setEnabled(true);
    }//GEN-LAST:event_tbTicketMouseClicked

    private void btnVaciarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarTicketActionPerformed
        int fila = tbTicket.getSelectedRow();
        if (fila >= 0) {
            dtmTicket.removeRow(fila);
            JOptionPane.showMessageDialog(null, "ELIMINADO DEL CARRITO COMPRAS");
            calcTot();
            btnVaciarTicket.setEnabled(false);
        }
    }//GEN-LAST:event_btnVaciarTicketActionPerformed

    private void sALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sALIRActionPerformed
        System.exit(0);
    }//GEN-LAST:event_sALIRActionPerformed

    private void btnEliminarUserIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserIndexActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Eliminar Registros?", "", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            boolean bandera = new UsuariosController().detelete(Integer.parseInt(txtCodigoUserIndex.getText()));
            if (bandera) {
                new MetodosValidar().ok_eliminar();
                cargarUsuarios("");
                desabilitarControlesUsuarios();
                limipiarControlesUsuario();
            }
        }
    }//GEN-LAST:event_btnEliminarUserIndexActionPerformed

    private void jTableUsersIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersIndexMouseClicked
        int f = jTableUsersIndex.getSelectedRow();
        try {
            txtCodigoUserIndex.setText(jTableUsersIndex.getValueAt(f, 0).toString());
            txtNombreUserIndex.setText(jTableUsersIndex.getValueAt(f, 1).toString());
            txtCorreoUserIndex.setText(jTableUsersIndex.getValueAt(f, 3).toString());
            txtPassUserIndex.setText(jTableUsersIndex.getValueAt(f, 2).toString());
            txtConfirmPassUserIndex.setText(jTableUsersIndex.getValueAt(f, 2).toString());
            cbRolUserIndex.setSelectedItem(jTableUsersIndex.getValueAt(f, 4).toString());
            habilitarControlesUsuarios();
        } catch (Exception e) {
            System.out.println("Error al recabar datos:" + e.getMessage());
        }

    }//GEN-LAST:event_jTableUsersIndexMouseClicked

    private void btnAddUserIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserIndexActionPerformed
        habilitarControlesUsuarios();
        habilitarBotonesUsuario();
    }//GEN-LAST:event_btnAddUserIndexActionPerformed

    private void btnGuardarUserIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUserIndexActionPerformed
        UsuarioDTO user = new UsuarioDTO();
        user.setNombre(txtNombreUserIndex.getText());
        user.setCorreo(txtCorreoUserIndex.getText());
        user.setPassword(getMD5(txtPassUserIndex.getText()));
        user.setRol(cbRolUserIndex.getSelectedItem().toString());
        if (valEntradasUsuario()) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Guardar Registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {

                boolean bandera = new UsuariosController().save(user);
                if (bandera) {
                    new MetodosValidar().ok();
                    cargarUsuarios("");
                    limipiarControlesUsuario();
                    desabilitarControlesUsuarios();
                }
            }

        }
    }//GEN-LAST:event_btnGuardarUserIndexActionPerformed

    private void btnActualizarConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarConfigActionPerformed
        Config conf = new Config();
        if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText()) || !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText())) {
            conf.setRuc(txtRucConfig.getText());
            conf.setNombre(txtNombreConfig.getText());
            conf.setTelefono(txtTelefonoConfig.getText());
            conf.setDireccion(txtDireccionConfig.getText());
            conf.setMensaje(txtMensaje.getText());
            if (new NegocioController().busquedaDatos() != null) {
                new NegocioController().delete();
                new NegocioController().save(conf);
                leerDatosNegocio();
            } else {
                new NegocioController().save(conf);
                leerDatosNegocio();
            }
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnActualizarConfigActionPerformed

    public void leerDatosNegocio() {
        try {
            Config conf = new NegocioController().busquedaDatos();
            txtRucConfig.setText(conf.getRuc());
            txtNombreConfig.setText(conf.getNombre());
            txtTelefonoConfig.setText(conf.getTelefono());
            txtDireccionConfig.setText(conf.getDireccion());
            txtMensaje.setText(conf.getMensaje());
        } catch (Exception e) {
            System.out.println("Error al leer datos de la empresa");
        }
    }

    public void inicioFrame() {
        //txtSubTotal.setEditable(false);
        //txtTotalIva.setEditable(false);
        txtCantidad.setEditable(false);
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
        //txtTotalIva.setText("0.0");//String.valueOf(IVA));
        //txtSubTotal.setText(String.valueOf(b));
        //double total_venta = Double.parseDouble(txtSubTotal.getText()) + Double.parseDouble(txtTotalIva.getText());
        //txtTotalVenta.setText(String.valueOf(total_venta));
        txtTotal.setText(String.valueOf(b));
    }

    public void productosView() {
        try {
            llenarTbProductos("", 1);
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
        llenarProveedores(cbproveedorProductosIndex);
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

    public void llenarUnidades(JComboBox combo) {
        List<UnidadesMedidaDTO> lista = unidadesController.unidadesAll("");
        for (int x = 0; x < lista.size(); x++) {
            UnidadesMedidaDTO unidad = lista.get(x);
            combo.addItem(unidad.getDescripcion());
        }
    }

    public void llenarProveedores(JComboBox combo) {
        List<ProveedorDTO> lista = proveedoresController.proveedoresAll("");
        for (int x = 0; x < lista.size(); x++) {
            ProveedorDTO provedor = lista.get(x);
            combo.addItem(provedor.getNombre());
        }
    }

    public void llenarTbProductos(String nombre, int op) {
        try {
            DefaultTableModel dtmProductos = new DefaultTableModel();
            dtmProductos.addColumn("CODIGO");
            dtmProductos.addColumn("NOMBRE");
            dtmProductos.addColumn("P.PUBLICO");
            dtmProductos.addColumn("P.CLIENTE");
            dtmProductos.addColumn("STOCK");
            dtmProductos.addColumn("ACTIVA P.CLIENTE");
            dtmProductos.addColumn("PRECIO DE COMPRA");
            dtmProductos.addColumn("CATEGORIA");
            dtmProductos.addColumn("PROVEEDOR");
            dtmProductos.addColumn("U.MEDIDA");
            if (op == 1) {
                tbproductosAdmin.setModel(dtmProductos);
            } else if (op == 2) {
                tbProductosIndex.setModel(dtmProductos);
            }else if(op == 3){
                tbproductosAdmin.setModel(dtmProductos);
            }

            List<ProductoDTO> listaProductos = null;
            if (nombre.trim().equals("")) {
                listaProductos = productoController.productosAll("");
            } else {
                listaProductos = productoController.productosAll(nombre);
            }
            System.out.println("Tamaño de la lista:" + listaProductos.size());
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
                dtmProductos.addRow(fila);

            }
            if (op == 1) {
                tbproductosAdmin.setModel(dtmProductos);
                JTableHeader header = tbproductosAdmin.getTableHeader();
                header.setOpaque(false);
                header.setBackground(new Color(51, 51, 51));
                header.setForeground(Color.white);
            } else if (op == 2) {
                tbProductosIndex.setModel(dtmProductos);
                JTableHeader header = tbProductosIndex.getTableHeader();
                header.setOpaque(false);
                header.setBackground(new Color(51, 51, 51));
                header.setForeground(Color.white);
            }else  if (op == 3) {
                tbproductosAdmin.setModel(dtmProductos);
                JTableHeader header = tbproductosAdmin.getTableHeader();
                header.setOpaque(false);
                header.setBackground(new Color(51, 51, 51));
                header.setForeground(Color.white);
            }
        } catch (Exception ex) {
            System.out.println("Error al llenar tabla de productos:" + ex.getMessage());
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
            ProductoDTO producto = productoController.productoById(Integer.parseInt(txtCodVenta.getText()));
            double precio = producto.getPrecio();
            for (int i = 0; i < tbTicket.getRowCount(); i++) {
                lista_id.add(tbTicket.getValueAt(i, 0).toString());
            }

            String id = txtCodVenta.getText();
            Object[] fila = new Object[5];

            if (lista_id.size() > 0) {
                for (int i = 0; i < lista_id.size(); i++) {
                    int idd = Integer.parseInt(lista_id.get(i));
                    if (idd == Integer.parseInt(txtCodVenta.getText())) {
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
            //txtSubTotal.setText(tbproductosAdmin.getValueAt(x, 4).toString());
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
            JTableHeader header = tbProductos.getTableHeader();
            header.setOpaque(false);
            header.setBackground(new Color(51, 51, 51));
            header.setForeground(Color.white);

        } catch (Exception e) {
            System.out.println("Error al llenar catalogo de productos:" + e.getMessage());
        }

    }

    public void generarVenta() {

        Random r = new Random();
        int random = r.nextInt(90000) + 10000;
        int opcion = JOptionPane.showConfirmDialog(null, "¿CONFIRMAR VENTA?", "", JOptionPane.YES_NO_OPTION);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String jd = sdf.format(Midate.getDate());
        String hoy = sdf.format(new Date());
        int insertaDetalles = 0;
        ReporteNotaVentaModelo rpNota = new ReporteNotaVentaModelo();
        rpNota.setFecha(fecha());
        rpNota.setNombreNegocio(lblTitulo.getText());
        rpNota.setTotalVenta(Double.parseDouble(txtTotal.getText()));

        List<DetalleVentaDTO> listaReporte = new ArrayList<>();
        if (opcion == JOptionPane.YES_OPTION) {
            //primero descontamos de la base de datos el total de productos
            //Insertamos la venta
            VentaRealizadaDTO venta = new VentaRealizadaDTO();
            venta.setIdticket(random);
            Timestamp timestamp = null;
            if (!jd.equals(hoy)) {
                timestamp = new java.sql.Timestamp(Midate.getDate().getTime());
            } else {
                timestamp = new java.sql.Timestamp(new Date().getTime());

            }
            venta.setFecha(timestamp);
            venta.setTipoventa(cbTipoVenta.getSelectedItem().toString());
            venta.setTotalventa(Double.parseDouble(txtTotal.getText()));
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
                        + "TOTAL:" + txtTotal.getText() + "\n"
                        + "FECHA:" + fecha(), "", JOptionPane.PLAIN_MESSAGE, icono);
                limpiarPrincipal(true);
                int opcionr = JOptionPane.showConfirmDialog(null, "¿IMPRIMIR NOTA?", "", JOptionPane.YES_NO_OPTION);
                if (opcionr == JOptionPane.YES_OPTION) {
                    if (cbTipoVenta.getSelectedItem().toString().contains("CLIE")) {
                        new PlantillaReporteNota(rpNota).generar_y_guardar(2);
                    } else {
                        new PlantillaReporteNota(rpNota).generar_y_guardar(1);
                    }

                }
            }
        }

    }

    public String fecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String fecha = "";
        try {
            fecha = sdf.format(Midate.getDate());
        } catch (Exception e) {
            System.out.println("Error al parsear fecha:" + e.getMessage());
        }
        return fecha;
    }

    public void diseñarJDateChooser() {
        ((JTextField) this.Midate.getDateEditor()).setEditable(false);
        Midate.setDate(new Date());

    }

    public void vaciarTicketMethod() {
        System.out.println("contador:" + dtmTicket.getRowCount());
        for (int x = 0; x < tbTicket.getRowCount(); x++) {
            System.out.println("si:" + x);
            dtmTicket.removeRow(x);
            x -= 1;
        }
    }

    public void llenarNombreNegocio(JLabel label) {
        NegocioController negocio = new NegocioController();
        try {
            label.setText(negocio.busquedaDatos().getNombre().toUpperCase());
        } catch (Exception e) {
            System.out.println("Error al obtener nombre negocio:" + e.getMessage());
        }

    }

    public void btnCancelar() {
        cbTipoVenta.setSelectedIndex(0);
        cbTipoVenta.setEnabled(true);
        txtCantidad.setText("");
        txtCodVenta.setText("");
        txtCliente.setText("XXXXXXXXXXXXXXXXXXXX");
        btnVender.setEnabled(false);
        btnAdd.setEnabled(false);
        btnVaciarTicket.setEnabled(false);
        for (int i = 0; i < dtmTicket.getRowCount(); i++) {
            dtmTicket.removeRow(i);
        }
        // txtSubTotal.setText("0.0");
        // txtTotalIva.setText("0.0");
        txtTotal.setText("0.0");
    }

    /*================================================================================*/
    private void ListarClientes(String nombre) {
        String[] titulos = {"CODIGO", "NOMBRE COMPLETO", "DIRECCION", "CORREO", "TELEFONO", "GENERO"};
        DefaultTableModel dtmClienteIndex = new DefaultTableModel(null, titulos);
        try {
            Object o[] = null;
            List<ClienteDTO> listC = clientesController.findAll(nombre);
            for (int i = 0; i < listC.size(); i++) {
                dtmClienteIndex.addRow(o);
                dtmClienteIndex.setValueAt(listC.get(i).getIdcliente(), i, 0);
                dtmClienteIndex.setValueAt(listC.get(i).getNombre(), i, 1);
                dtmClienteIndex.setValueAt(listC.get(i).getDomicilio(), i, 2);
                dtmClienteIndex.setValueAt(listC.get(i).getMail(), i, 3);
                dtmClienteIndex.setValueAt(listC.get(i).getTelefono(), i, 4);
                dtmClienteIndex.setValueAt(listC.get(i).getGenero(), i, 5);

            }
            tbclientesIndex.setModel(dtmClienteIndex);
            JTableHeader header = tbclientesIndex.getTableHeader();
            header.setOpaque(false);
            header.setBackground(new Color(51, 51, 51));
            header.setForeground(Color.white);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void guardarClientes() {
        ClienteDTO cliente = new ClienteDTO();
        try {
            cliente.setIdcliente(Integer.parseInt(txtcodigoClienteIndex.getText()));
            cliente.setNombre(txtnombreClienteIndex.getText());
            cliente.setDomicilio(cbcolClienteIndex.getSelectedItem().toString() + "," + cbmuniClienteIndex.getSelectedItem().toString() + "," + cbestadoClienteIndex.getSelectedItem().toString());
            cliente.setMail(txtemailClienteIndex.getText());
            if (!txttelefonoClienteIndex.getText().equals("")) {
                cliente.setTelefono(txttelefonoClienteIndex.getText());
            } else {
                cliente.setTelefono("0");
            }
            cliente.setGenero(cbgeneroClienteIndex.getSelectedItem().toString());
            ColoniaDTO colonia = new LocalidadesController().coloniaByNombre(cbcolClienteIndex.getSelectedItem().toString());
            cliente.setIdcolonia(colonia.getIdcolonia());
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Guardar registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                boolean registro = clientesController.save(cliente);
                if (registro) {
                    new MetodosValidar().ok();
                    limpiarControlesCliente();
                    desabilitarControlerCliente();
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
            cliente.setIdcliente(Integer.parseInt(txtcodigoClienteIndex.getText()));
            cliente.setNombre(txtnombreClienteIndex.getText());
            cliente.setDomicilio(cbcolClienteIndex.getSelectedItem().toString() + "," + cbmuniClienteIndex.getSelectedItem().toString() + "," + cbestadoClienteIndex.getSelectedItem().toString());
            cliente.setMail(txtemailClienteIndex.getText());
            if (!txttelefonoClienteIndex.getText().equals("")) {
                cliente.setTelefono(txttelefonoClienteIndex.getText());
            } else {
                cliente.setTelefono("0");
            }
            cliente.setGenero(cbgeneroClienteIndex.getSelectedItem().toString());
            ColoniaDTO colonia = new LocalidadesController().coloniaByNombre(cbcolClienteIndex.getSelectedItem().toString());
            cliente.setIdcolonia(colonia.getIdcolonia());
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Modificar registros?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                boolean registro = clientesController.update(cliente);
                if (registro) {
                    new MetodosValidar().ok_modificar();
                    limpiarControlesCliente();
                    desabilitarControlerCliente();
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
                boolean registro = clientesController.delete(Integer.parseInt(txtcodigoClienteIndex.getText()));
                if (registro) {
                    new MetodosValidar().ok_eliminar();
                    limpiarControlesCliente();
                    desabilitarControlerCliente();
                    ListarClientes("");
                } else {
                    new MetodosValidar().error_eliminar();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar registros");
        }
    }

    public void llenarCombosClientes() {
        cbcolClienteIndex.removeAllItems();
        cbcolClienteIndex.addItem("---");
        cbmuniClienteIndex.removeAllItems();
        cbmuniClienteIndex.addItem("---");
        cbestadoClienteIndex.removeAllItems();
        cbestadoClienteIndex.addItem("---");
        for (int i = 0; i < new LocalidadesController().coloniasAll("").size(); i++) {
            cbcolClienteIndex.addItem(new LocalidadesController().coloniasAll("").get(i).getNombre());
        }

        for (int i = 0; i < new MunicipiosController().municipiosAll("").size(); i++) {
            cbmuniClienteIndex.addItem(new MunicipiosController().municipiosAll("").get(i).getNombre());
        }

        for (int i = 0; i < new EstadosController().estadosAll("").size(); i++) {
            cbestadoClienteIndex.addItem(new EstadosController().estadosAll("").get(i).getNombre());
        }

    }

    public void particionarDom(String cadena) {
        String c, m, e;
        String[] Part = cadena.split(",");
        for (int i = 0; i < Part.length; i++) {
            c = Part[0];
            m = Part[1];
            e = Part[2];
            cbcolClienteIndex.setSelectedItem(c);
            cbmuniClienteIndex.setSelectedItem(m);
            cbestadoClienteIndex.setSelectedItem(e);
        }
    }

    public void EventMouseClick() {
        int filas = tbclientesIndex.getSelectedRow();
        if (filas >= 0) {
            this.habilitarControlesCliente();
            this.btnaceptar.setEnabled(false);
            this.btnmodificarClienteIndex.setEnabled(true);
            this.btneliminarClienteIndex.setEnabled(true);
            this.btnnuevoClienteIndex.setEnabled(false);
            txtcodigoClienteIndex.setText(tbclientesIndex.getValueAt(filas, 0).toString());
            txtnombreClienteIndex.setText(tbclientesIndex.getValueAt(filas, 1).toString());
            particionarDom(tbclientesIndex.getValueAt(filas, 2).toString());
            txttelefonoClienteIndex.setText(tbclientesIndex.getValueAt(filas, 4).toString());
            if (tbclientesIndex.getValueAt(filas, 5) != null) {
                cbgeneroClienteIndex.setSelectedItem(tbclientesIndex.getValueAt(filas, 5).toString());
            } else {
                cbgeneroClienteIndex.setSelectedIndex(0);
            }
            if (!tbclientesIndex.getValueAt(filas, 3).toString().equals("")) {
                txtemailClienteIndex.setText(tbclientesIndex.getValueAt(filas, 3).toString());
            } else {
                txtemailClienteIndex.setText("");
            }

        }
    }//EventoDeMouseClicked

    public boolean valEntradasCliente() {
        String mensaje = "";
        boolean estado = true;
        if (txtcodigoClienteIndex.getText().isEmpty()) {
            mensaje += "ID NO DEBE ESTAR VACIO \n";
            estado = false;
        }
        if (txtnombreClienteIndex.getText().isEmpty()) {
            mensaje += "NO SE INSERTO UN NOMBRE VALIDO \n";
            estado = false;
        }

        if (cbgeneroClienteIndex.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO GENERO \n";
            estado = false;
        }
        if (txtemailClienteIndex.getText().isEmpty() == false && valcorreo(txtemailClienteIndex.getText()) == false) {
            mensaje += "VERIFICA DIRECCION DE CORREO @";
            estado = false;
        }
        if (mensaje.length() >= 6) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }

        return estado;
    }

    public boolean valcorreo(String email) {
        boolean correo = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                correo = true;
            }
        }
        return correo;
    }

    public void desabilitarControlerCliente() {
        //this.lblcodigo.setEnabled(false);
        //this.lblnombre.setEnabled(false);
        //this.lbldireccion.setEnabled(false);
        //this.lbltelefono.setEnabled(false);
        //lblgenero.setEnabled(false);
        //lblemail.setEnabled(false);
        this.btnaceptarClienteIndex.setEnabled(false);
        this.btncancelarClienteIndex.setEnabled(false);
        this.btnmodificarClienteIndex.setEnabled(false);
        this.btneliminarClienteIndex.setEnabled(false);
        this.txtcodigoClienteIndex.setEnabled(false);
        this.txtnombreClienteIndex.setEnabled(false);
        this.txttelefonoClienteIndex.setEnabled(false);
        cbgeneroClienteIndex.setEnabled(false);
        txtemailClienteIndex.setEnabled(false);
        cbcolClienteIndex.setEnabled(false);
        cbmuniClienteIndex.setEnabled(false);
        cbestadoClienteIndex.setEnabled(false);
        btnnuevoClienteIndex.setEnabled(true);
    }

    public void habilitarControlesCliente() {
        this.lblcodigo.setEnabled(true);
        this.lblnombre.setEnabled(true);
        this.lbldireccion.setEnabled(true);
        this.lbltelefono.setEnabled(true);
        lblgenero.setEnabled(true);
        lblemail.setEnabled(true);
        this.btnaceptarClienteIndex.setEnabled(true);
        this.btncancelarClienteIndex.setEnabled(true);
        this.btnmodificarClienteIndex.setEnabled(false);
        this.btneliminarClienteIndex.setEnabled(false);
        this.txtcodigoClienteIndex.setEnabled(true);
        this.txtnombreClienteIndex.setEnabled(true);
        this.txttelefonoClienteIndex.setEnabled(true);
        cbgeneroClienteIndex.setEnabled(true);
        txtemailClienteIndex.setEnabled(true);
        cbcolClienteIndex.setEnabled(true);
        cbmuniClienteIndex.setEnabled(true);
        cbestadoClienteIndex.setEnabled(true);
        txttelefonoClienteIndex.setText("0");

    }

    public void limpiarControlesCliente() {
//        this.Generarnumeracion();
        this.txtnombreClienteIndex.setText("");
        this.txttelefonoClienteIndex.setText("");
        txtcodigoClienteIndex.setText("");
        cbgeneroClienteIndex.setSelectedIndex(0);
        txtemailClienteIndex.setText("");
        cbcolClienteIndex.setSelectedIndex(0);
        cbmuniClienteIndex.setSelectedIndex(0);
        cbestadoClienteIndex.setSelectedIndex(0);
    }

    public void generarId(int op) {
        if (op == 1) {
            txtcodigoClienteIndex.setText(String.valueOf(clientesController.generarSecuenciaId()));
        } else if (op == 2) {
            txtCodigoProveedorIndex.setText(String.valueOf(proveedoresController.generarSecuenciaId()));
        } else if (op == 3) {
            txtcodigoProductoIndex.setText(String.valueOf(productoController.generarSecuenciaId()));
        }

    }

    /*=======================FINISH CLIENTE=====================*/
 /*======================INICIA PROVEEDOR=======================*/
    private void carga_informacion_Prov(String nombre) {
        List<ProveedorDTO> listaProv = null;
        String[] titulos = {"CODIGO", "NOMBRE", "EMAIL", "TELEFONO"};
        DefaultTableModel dtm = new DefaultTableModel(null, titulos);
        try {
            Object o[] = null;
            listaProv = proveedoresController.proveedoresAll(nombre);
            for (int i = 0; i < listaProv.size(); i++) {
                dtm.addRow(o);
                dtm.setValueAt(listaProv.get(i).getIdproveedor(), i, 0);
                dtm.setValueAt(listaProv.get(i).getNombre(), i, 1);
                dtm.setValueAt(listaProv.get(i).getMail(), i, 2);
                dtm.setValueAt(listaProv.get(i).getTelefono(), i, 3);
            }
            tbProveedorIndex.setModel(dtm);
            JTableHeader header = tbProveedorIndex.getTableHeader();
            header.setOpaque(false);
            header.setBackground(new Color(51, 51, 51));
            header.setForeground(Color.white);
        } catch (Exception e) {
            System.out.println("Error al listar proveedores:" + e.getMessage());
        }
    }

    public void DesabilitarControlesProveedor() {
        txtCodigoProveedorIndex.setEnabled(false);
        txttelefonoProveedorIndex.setEnabled(false);
        txtemailProveedorIndex.setEnabled(false);
        txtEmpresaProveedorIndex.setEnabled(false);
        btnaceptarProveedorIndex.setEnabled(false);
        btncancelarProveedorIndex.setEnabled(false);
        btneliminarProveedorIndex.setEnabled(false);
        btnmodificarProveedorIndex.setEnabled(false);
        btnnuevoProveedorIndex.setEnabled(true);

    }

    public void habFieldComponentesProveedor() {
        txtCodigoProveedorIndex.setEnabled(true);
        txttelefonoProveedorIndex.setEnabled(true);
        txtemailProveedorIndex.setEnabled(true);
        txtEmpresaProveedorIndex.setEnabled(true);
        btnaceptarProveedorIndex.setEnabled(true);
        btncancelarProveedorIndex.setEnabled(true);

    }

    public void limpiarControlesProveedor() {
        txttelefonoProveedorIndex.setText("");
        txtemailProveedorIndex.setText("");
        txtEmpresaProveedorIndex.setText("");
        txtCodigoProveedorIndex.setText("");
    }

    public boolean valEntradasProveedor() {
        String msj = "";
        boolean es = true;
        if (this.txtCodigoProveedorIndex.getText().isEmpty() == true) {
            msj += "NO SE INSERTO CODIGO \n";
            es = false;
        }
        if (txtEmpresaProveedorIndex.getText().isEmpty() == true) {
            msj += "NO SE INSERTO NOMBRE \n";
            es = false;
        }
        if (txtemailProveedorIndex.getText().isEmpty() == false && valcorreo(txtemailProveedorIndex.getText()) == false) {
            msj += "VERIFICA DIRECCION DE CORREO @";
            es = false;
        }

        if (msj.length() >= 6) {
            JOptionPane.showMessageDialog(null, msj, "", JOptionPane.WARNING_MESSAGE);

        }

        return es;
    }

    /*========================TERMINA PROVEEDORES===========================*/
 /*=========================INICIA PRODUCTOS=============================================*/
    public void desabPanContenidoProductos() {
        txtcodigoProductoIndex.setEnabled(false);
        txtnombreProductoIndex.setEnabled(false);
        cbunidadProductoIndex.setEnabled(false);
        sppclienteProductoIndex.setEnabled(false);
        spppublicoProductoIndex.setEnabled(false);
        spcantidadProductosIndex.setEnabled(false);
        cbproveedorProductosIndex.setEnabled(false);
        btnaceptarProductosIndex.setEnabled(false);
        btncancelarProductosIndex.setEnabled(false);
        cbcategoriaProductosIndex.setEnabled(false);
        btnmodificarProductosIndex.setEnabled(false);
        btneliminarProductosIndex.setEnabled(false);
        btnnuevoProductosIndex.setEnabled(true);
    }

    public void habPanContenidoProductos() {
        jLabel1.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel9.setEnabled(true);
        jLabel10.setEnabled(true);
        jLabel11.setEnabled(true);
        txtcodigoProductoIndex.setEnabled(true);
        txtnombreProductoIndex.setEnabled(true);
        cbunidadProductoIndex.setEnabled(true);
        sppclienteProductoIndex.setEnabled(true);
        spppublicoProductoIndex.setEnabled(true);
        spcantidadProductosIndex.setEnabled(true);
        cbproveedorProductosIndex.setEnabled(true);
        btnaceptarProductosIndex.setEnabled(true);
        btncancelarProductosIndex.setEnabled(true);
        cbcategoriaProductosIndex.setEnabled(true);
        btnnuevoProductosIndex.setEnabled(false);
        jLabelCat.setEnabled(true);
    }

    public boolean valEntradasProductos() {
        String mensaje = "";
        boolean estado = true;

        Double ppublico = Double.parseDouble(spppublicoProductoIndex.getValue().toString());
        Double cantidad = Double.parseDouble(spcantidadProductosIndex.getValue().toString());
        if (this.txtcodigoProductoIndex.getText().isEmpty() == true) {
            mensaje += "NO SE GENERO CODIGO \n";
            estado = false;
        }
        if (txtnombreProductoIndex.getText().isEmpty() == true) {
            mensaje += "NO SE INSERTO NOMBRE \n";
            estado = false;
        }
        if (cbunidadProductoIndex.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO UNIDAD DE MEDIDA \n";
            estado = false;
        }
        /* if (pcliente <= 0) {
            mensaje += "PRECIO CLIENTE DEBE SER MAYOR A CERO \n";
            sppcliente.setValue(0);
            estado = false;
        }*/
        if (ppublico <= 0.00001) {
            mensaje += "PRECIO PUBLICO DEBE SER MAYOR A CERO \n";
            spppublicoProductoIndex.setValue(0);
            estado = false;
        }
        if (cantidad <= 0) {
            mensaje += "CANTIDAD DEBE SER MAYOR A CERO \n";
            spcantidadProductosIndex.setValue(0);
            estado = false;
        }
        if (cbproveedorProductosIndex.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO PROVEEDOR \n";
            estado = false;
        }
        if (cbcategoriaProductosIndex.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO CATEGORIA \n";
            estado = false;
        }

        if (mensaje.length() >= 4) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }
        return estado;

    }//MetodoParaValidarCajasDeTexto

    public boolean valEntradasUsuario() {
        String mensaje = "";
        boolean estado = true;

        if (this.txtNombreUserIndex.getText().isEmpty() == true) {
            mensaje += "NO SE INGRRESO NOMBRE \n";
            estado = false;
        }

        if (txtCorreoUserIndex.getText().isEmpty() == true) {
            mensaje += "NO SE INGRESO CORREO \n";
            estado = false;
        } else {
            if (valcorreo(txtCorreoUserIndex.getText()) == false) {
                mensaje += "VERIFICA DIRECCION DE CORREO @ \n";
                estado = false;
            }
        }

        if (txtPassUserIndex.getText().isEmpty() == true) {
            mensaje += "NO SE INGRESO CONTREASEÑA \n";
            estado = false;
        }

        if (!txtConfirmPassUserIndex.getText().equals(txtPassUserIndex.getText())) {
            mensaje += "LAS CONTRASEÑAS NO COINCIDEN \n";
            estado = false;
        } else {
            if (txtConfirmPassUserIndex.getText().length() < 6) {
                mensaje += "CONTRASEÑA NO CUMPLE CON LONGITUD MINIMA 6 \n";
                estado = false;
            }
        }
        if (mensaje.length() > 0) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);
        }
        return estado;

    }//MetodoParaValidarCajasDeTexto

    public void limpiarControlesProducto() {//MetodoLimpiarCajaasDeTexto
        txtnombreProductoIndex.setText("");
        cbunidadProductoIndex.setSelectedIndex(0);
        sppclienteProductoIndex.setValue(0.0);
        spppublicoProductoIndex.setValue(0.0);
        spcantidadProductosIndex.setValue(0);
        cbproveedorProductosIndex.setSelectedIndex(0);
        cbcategoriaProductosIndex.setSelectedIndex(0);
        this.btnmodificarProductosIndex.setEnabled(false);
        this.btneliminarProductosIndex.setEnabled(false);
    }

    public void EventoTbProductMC() {
        int fseleccionada = tbProductosIndex.getSelectedRow();
        if (fseleccionada >= 0) {
            habPanContenidoProductos();
            this.btnmodificarProductosIndex.setEnabled(true);
            this.btneliminarProductosIndex.setEnabled(true);
            txtcodigoProductoIndex.setText(tbProductosIndex.getValueAt(fseleccionada, 0).toString());
            txtnombreProductoIndex.setText(tbProductosIndex.getValueAt(fseleccionada, 1).toString());
            cbunidadProductoIndex.setSelectedItem(tbProductosIndex.getValueAt(fseleccionada, 9).toString());
            if (tbProductosIndex.getValueAt(fseleccionada, 3).toString().toUpperCase().contains("SIN")) {
                sppclienteProductoIndex.setValue(0.0);
            } else {
                sppclienteProductoIndex.setValue(Double.parseDouble(tbProductosIndex.getValueAt(fseleccionada, 3).toString()));
            }
            spppublicoProductoIndex.setValue(Double.parseDouble(tbProductosIndex.getValueAt(fseleccionada, 2).toString()));
            spcantidadProductosIndex.setValue(Integer.parseInt(tbProductosIndex.getValueAt(fseleccionada, 4).toString()));
            cbproveedorProductosIndex.setSelectedItem(tbProductosIndex.getValueAt(fseleccionada, 8).toString());
            cbcategoriaProductosIndex.setSelectedItem(tbProductosIndex.getValueAt(fseleccionada, 7).toString());
            // padquisicion.setText(tbproductos.getValueAt(fseleccionada, 7).toString());
            btnaceptarProductosIndex.setEnabled(false);
            btnnuevoProductosIndex.setEnabled(false);
        }

    }

    public void llenarCategoria() {
        CategoriaController controllerCat = new CategoriaController();
        for (int i = 0; i < controllerCat.categoriasAll("").size(); i++) {
            cbcategoriaProductosIndex.addItem(controllerCat.categoriasAll("").get(i).getDescripcion());
        }
    }

    public boolean leerSaldoRecargas() {
        boolean bandera = false;
        try {
            SaldoDTOLocal modeloSaldo = null;
            modeloSaldo = locaService.consultarSaldo(Integer.parseInt(lblIdUser.getText().trim()));
            totalSaldoRecarga.setText(String.valueOf(modeloSaldo.getTotal()));
            System.out.println("modelo saldo:" + modeloSaldo);
            if (modeloSaldo.getId() != null) {
                bandera = true;
            }
        } catch (Exception e) {
            System.out.println("Error al conectarse sevicio de recargas");
        }
        return true;//bandera;
    }

    public void llenarComboProductosRecargas(String compa, String serv) {
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

    public void limpiarControlesRecargar() {
        jlabelCostoProducto.setText("");
        jlabelComisionServicio.setText("");
        jlabelVigencia.setText("");
        jTextArea1.setText("");
        jLabelCodProducto.setText("");
    }

    public void setDetallesRecargas(String compa, String tipa) {
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

    public int totalEnTicket(int codigoBusqueda) {
        int to = 0;
        if (tbTicket.getRowCount() > 0) {
            for (int i = 0; i < tbTicket.getRowCount(); i++) {
                if (Integer.parseInt(tbTicket.getValueAt(i, 0).toString()) == codigoBusqueda) {
                    to = Integer.parseInt(tbTicket.getValueAt(i, 3).toString());
                }
            }
        }
        return to;
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

    public void bloquear() {
        jd = new JDialog(this, "DESBLOQUEAR", false);
        jd.add(JDBloquear.getContentPane());
        jd.setVisible(true);
        JDBloquear.setTitle("INGRESA CONTRASEÑA");
        jd.setSize(380, 85);
        jd.setLocationRelativeTo(null);
        jd.setDefaultCloseOperation(0);

        /*jMenuBar1.setEnabled(false);
        jmenuOperaciones.setEnabled(false);
        jmenuMantenimiento.setEnabled(false);
        jmenuReporte.setEnabled(false);
        btnSalir.setEnabled(false);*/
        bntBloquear.setEnabled(false);

        this.setEnabled(false);
    }

    public boolean validarDesbloquear() {
        boolean bandera = false;
        try {
            List<UsuarioDTO> user = new UsuariosController().usuariosAll(usuarioCaja.getText().trim());
            if (user != null) {
                if (user.get(0).getPassword().equals(getMD5(jPasswordField1.getText().trim()))) {
                    bandera = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el usuario:" + e.getMessage());
        }
        return bandera;
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

    public void desbloquear() {
        /*jMenuBar1.setEnabled(true);
        jmenuOperaciones.setEnabled(true);
        jmenuMantenimiento.setEnabled(true);
        jmenuReporte.setEnabled(true);
        btnSalir.setEnabled(true);*/
        bntBloquear.setEnabled(true);
        this.setEnabled(true);
    }

    public void limpiarPrincipal(boolean bandera) {
        txtCodVenta.setText("");
        txtDescripcionVenta.setText("");
        txtPrecioVenta.setText("");
        txtStockDisponible.setText("");
        btnAdd.setEnabled(false);

        btnGenerarVenta.setEnabled(false);
        txtnombre.setText("");
        dniCliente.setText("");
        btnVaciarTicket.setEnabled(false);
        if (bandera) {
            vaciarTicketMethod();
            txtTotal.setText("0.0");
        }

    }

    public void cargarUsuarios(String nombre) {
        DefaultTableModel dtmUsers = new DefaultTableModel();
        String[] titulos = {"DNI", "NOMBRE", "CONTRASEÑA", "CORREO", "ROL"};
        dtmUsers.setColumnIdentifiers(titulos);
        try {
            List<UsuarioDTO> users = new UsuariosController().usuariosAll(nombre);
            Object[] ob = new Object[6];
            for (int i = 0; i < users.size(); i++) {
                ob[0] = users.get(i).getId();
                ob[1] = users.get(i).getNombre();
                ob[2] = users.get(i).getPassword();
                ob[3] = users.get(i).getCorreo();
                ob[4] = users.get(i).getRol();
                dtmUsers.addRow(ob);
            }
            jTableUsersIndex.setModel(dtmUsers);
            JTableHeader header = jTableUsersIndex.getTableHeader();
            header.setOpaque(false);
            header.setBackground(new Color(51, 51, 51));
            header.setForeground(Color.white);
        } catch (Exception e) {
            System.out.println("Error al llenar usuarios:" + e.getMessage());
        }

    }

    public void desabilitarControlesUsuarios() {
        txtCodigoUserIndex.setEnabled(false);
        txtNombreUserIndex.setEnabled(false);
        txtCorreoUserIndex.setEnabled(false);
        txtPassUserIndex.setEnabled(false);
        txtConfirmPassUserIndex.setEnabled(false);
        cbRolUserIndex.setEnabled(false);
    }

    public void habilitarControlesUsuarios() {
        txtCodigoUserIndex.setEnabled(true);
        txtNombreUserIndex.setEnabled(true);
        txtCorreoUserIndex.setEnabled(true);
        txtPassUserIndex.setEnabled(true);
        txtConfirmPassUserIndex.setEnabled(true);
        cbRolUserIndex.setEnabled(true);
    }

    public void habilitarBotonesUsuario() {
        btnAddUserIndex.setEnabled(false);
        btnEliminarUserIndex.setEnabled(false);
    }

    public void limipiarControlesUsuario() {
        txtCodigoUserIndex.setText("000");
        txtNombreUserIndex.setText("");
        txtCorreoUserIndex.setText("");
        txtPassUserIndex.setText("");
        txtConfirmPassUserIndex.setText("");
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
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JDBloquear;
    private com.toedter.calendar.JDateChooser Midate;
    private javax.swing.JButton bntBloquear;
    private javax.swing.JButton btnActualizarConfig;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddUserIndex;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEliminarUserIndex;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGuardarUserIndex;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevaVenta1;
    private javax.swing.JButton btnNuevaVenta2;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVaciarTicket;
    private javax.swing.JPanel btnVender;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnaceptarClienteIndex;
    private javax.swing.JButton btnaceptarProductosIndex;
    private javax.swing.JButton btnaceptarProveedorIndex;
    private javax.swing.JButton btnaddProd;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btncancelarClienteIndex;
    private javax.swing.JButton btncancelarProductosIndex;
    private javax.swing.JButton btncancelarProveedorIndex;
    private javax.swing.JButton btneliminarClienteIndex;
    private javax.swing.JButton btneliminarProductosIndex;
    private javax.swing.JButton btneliminarProveedorIndex;
    private javax.swing.JButton btnmodificarClienteIndex;
    private javax.swing.JButton btnmodificarProductosIndex;
    private javax.swing.JButton btnmodificarProveedorIndex;
    private javax.swing.JButton btnnuevoClienteIndex;
    private javax.swing.JButton btnnuevoProductosIndex;
    private javax.swing.JButton btnnuevoProveedorIndex;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbMontoCompa;
    public javax.swing.JComboBox<String> cbProductos;
    private javax.swing.JComboBox<String> cbRolUserIndex;
    private javax.swing.JComboBox<String> cbTipoVenta;
    private javax.swing.JComboBox<String> cbcategoriaProductosIndex;
    private javax.swing.JComboBox<String> cbcolClienteIndex;
    private javax.swing.JComboBox<String> cbestadoClienteIndex;
    private javax.swing.JComboBox<String> cbgeneroClienteIndex;
    private javax.swing.JComboBox<String> cbmuniClienteIndex;
    private javax.swing.JComboBox<String> cbproveedor;
    private javax.swing.JComboBox<String> cbproveedorProductosIndex;
    private javax.swing.JComboBox<String> cbunidad;
    private javax.swing.JComboBox<String> cbunidadProductoIndex;
    private javax.swing.JTextField dniCliente;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j10;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j3;
    private javax.swing.JLabel j4;
    private javax.swing.JLabel j5;
    private javax.swing.JLabel j6;
    private javax.swing.JLabel j7;
    private javax.swing.JLabel j8;
    private javax.swing.JLabel j9;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCat;
    private javax.swing.JLabel jLabelCodProducto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTable jTableUsersIndex;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JDialog jdVenderSaldo;
    private javax.swing.JLabel jlVenta;
    private javax.swing.JLabel jlabelComisionServicio;
    private javax.swing.JLabel jlabelCostoProducto;
    private javax.swing.JLabel jlabelVigencia;
    private javax.swing.JPanel jpanelJDClientesTB;
    private javax.swing.JLabel lblComisionServicio;
    private javax.swing.JLabel lblCostoProducto;
    private javax.swing.JLabel lblIdUser;
    private javax.swing.JLabel lblNota;
    public javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVigencia;
    private javax.swing.JLabel lblVigencia1;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblgenero;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JDialog loading;
    private javax.swing.JPanel panelImg;
    private javax.swing.JPanel pcontenido;
    private javax.swing.JPanel pcontenido1;
    private javax.swing.JPanel plista;
    private javax.swing.JPanel plista1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDatos1;
    private javax.swing.JPanel pnlDatos2;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlVentas;
    private javax.swing.JLabel porcentaje;
    private javax.swing.JButton sALIR;
    private javax.swing.JSpinner spcantidad;
    private javax.swing.JSpinner spcantidadProductosIndex;
    private javax.swing.JSpinner sppcliente;
    private javax.swing.JSpinner sppclienteProductoIndex;
    private javax.swing.JSpinner spppublico;
    private javax.swing.JSpinner spppublicoProductoIndex;
    private javax.swing.JTable tbClientes1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbProductosIndex;
    private javax.swing.JTable tbProveedorIndex;
    private javax.swing.JTable tbTicket;
    private javax.swing.JTable tbclientesIndex;
    public javax.swing.JTable tbproductosAdmin;
    private javax.swing.JLabel tipo;
    private javax.swing.JLabel totalSaldoRecarga;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarProductosIndex;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodVenta;
    private javax.swing.JTextField txtCodigoProveedorIndex;
    private javax.swing.JTextField txtCodigoUserIndex;
    private javax.swing.JPasswordField txtConfirmPassUserIndex;
    private javax.swing.JTextField txtConfirmarNumero;
    private javax.swing.JTextField txtCorreoUserIndex;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionConfig;
    private javax.swing.JTextField txtEmpresaProveedorIndex;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextField txtNombreConfig;
    private javax.swing.JTextField txtNombreUserIndex;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JPasswordField txtPassUserIndex;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtProductoB;
    private javax.swing.JTextField txtRucConfig;
    private javax.swing.JTextField txtStockDisponible;
    private javax.swing.JTextField txtTelefonoConfig;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtbuscarProveedorIndex;
    private javax.swing.JTextField txtclientesb;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtcodigoClienteIndex;
    private javax.swing.JTextField txtcodigoProductoIndex;
    private javax.swing.JTextField txtemailClienteIndex;
    private javax.swing.JTextField txtemailProveedorIndex;
    public javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombreClienteIndex;
    public javax.swing.JTextField txtnombreProductoIndex;
    private javax.swing.JTextField txttelefonoClienteIndex;
    private javax.swing.JTextField txttelefonoProveedorIndex;
    public javax.swing.JLabel usuarioCaja;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image image;

        @Override
        public void paint(Graphics g) {
            image = new ImageIcon(getClass().getResource("/Imagenes/banner.png")).getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);

        }

    }
}
