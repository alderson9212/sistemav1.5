/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.reportes;

import com.saivent.diseños.GestionCeldas;
import com.saivent.diseños.GestionEncabezadoTabla;
import com.saivent.diseños.ModeloTabla;
import com.sistema.controller.CategoriaController;
import com.sistema.controller.NegocioController;
import com.sistema.controller.ProductoController;
import com.sistema.controller.ProveedorController;
import com.sistema.controller.UnidadesMedidaController;
import com.sistema.controller.VentasController;
import com.sistema.modelo.CategoriaDTO;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
import com.sistema.modelo.ReportesModelo;
import com.sistema.modelo.UnidadesMedidaDTO;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Elliot
 */
public class FReporteProductos extends javax.swing.JInternalFrame {

    ProductoController controllerProductos = new ProductoController();
    UnidadesMedidaController controllerUnidades = new UnidadesMedidaController();
    ProveedorController controllerProveedor = new ProveedorController();
    CategoriaController controllerCategoria = new CategoriaController();

    DefaultTableModel dtm;

    ModeloTabla modelo;
    private int filasTabla;
    private int columnasTabla;

    ArrayList<ProductoDTO> listaProductos;

    /**
     * Creates new form FReporteVentas
     */
    public FReporteProductos() {
        initComponents();

        llenarNombreNegocio();
        diseñoVentana();
        construirTabla("",0);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        jPanel1.setBackground(new java.awt.Color(0, 111, 111));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TU NEGOCIO");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/reporte - pdf.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/icons8-imprimir-30.png"))); // NOI18N
        btnPrint.setText("IMPRIMIR");
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        jButton3.setText("CERRAR");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton4.setText("VER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton1.setText("NOMBRE");

        jRadioButton2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton2.setText("PROVEEDOR");

        jRadioButton3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton3.setText("MEDIDA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jRadioButton1.isSelected()){
            construirTabla(jTextField1.getText(),1);
        }else if(jRadioButton2.isSelected()){
            ProveedorDTO proveedor = controllerProveedor.proveedorByNombre(jTextField1.getText().toUpperCase());            
            construirTabla(String.valueOf(proveedor.getIdproveedor()),2);
        }else if(jRadioButton3.isSelected()){
            UnidadesMedidaDTO unidad = controllerUnidades.unidadByNombre(jTextField1.getText().toUpperCase());            
            construirTabla(String.valueOf(unidad.getIdunidadm()),3);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PlantillaReporteProductos plantilla = new PlantillaReporteProductos(datosEnviarPDF());
        plantilla.generar_y_guardar(1);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        PlantillaReporteProductos plantilla = new PlantillaReporteProductos(datosEnviarPDF());
        plantilla.generar_y_guardar(2);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void construirTabla(String valor,int opcion) {
        listaProductos = consultarProductos(valor,opcion);
        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("CODIGO");
        titulosList.add("NOMBRE");
        titulosList.add("PRECIO");
        titulosList.add("PRECIO CLIENTE");
        titulosList.add("EXISTENCIAS");
        titulosList.add("PRECIO DE ADQUISICION");
        titulosList.add("MEDIDA");
        titulosList.add("PROVEEDOR");
        titulosList.add("CATEGORIA");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
		 * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTablaReal(titulos, data);

    }

    private ArrayList<ProductoDTO> consultarProductos(String valor,int opcion) {
        ArrayList<ProductoDTO> lista = (ArrayList<ProductoDTO>) controllerProductos.productosReporte(valor,opcion);
        return lista;
    }

    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {
        String informacion[][] = new String[listaProductos.size()][titulosList.size()];
        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = listaProductos.get(x).getIdproducto() + "";
            informacion[x][1] = listaProductos.get(x).getNombre() + "";
            informacion[x][2] = listaProductos.get(x).getPrecio() + "";
            informacion[x][3] = listaProductos.get(x).getPreciocliente() + "";
            informacion[x][4] = listaProductos.get(x).getStock() + "";
            informacion[x][5] = listaProductos.get(x).getPreciodeproveedor() + "";
            UnidadesMedidaDTO unidad = controllerUnidades.unidadById(listaProductos.get(x).getIdunidadm());
            informacion[x][6] = unidad.getDescripcion() + "";
            ProveedorDTO proveedor = controllerProveedor.proveedorById(listaProductos.get(x).getIdproveedor());
            informacion[x][7] = proveedor.getNombre() + "";
            CategoriaDTO categoria = controllerCategoria.categoriaById(listaProductos.get(x).getIdcategoria());
            informacion[x][8] = categoria.getDescripcion() + "";

        }

        return informacion;
    }

    private void construirTablaReal(String[] titulos, Object[][] data) {
        modelo = new ModeloTabla(data, titulos);
        jTable1.setModel(modelo);
        filasTabla = jTable1.getRowCount();
        columnasTabla = jTable1.getColumnCount();
        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(6).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(7).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(8).setCellRenderer(new GestionCeldas("numerico"));
        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 5; i++) {
            //se resta 5 porque las ultimas 7 columnas se definen arriba           
            jTable1.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setRowHeight(25);//tamaño de las celdas
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        //personaliza el encabezado
        JTableHeader jtableHeader = jTable1.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        jTable1.setTableHeader(jtableHeader);

    }

    public ReportesModelo datosEnviarPDF() {
        ReportesModelo dto = new ReportesModelo();
        try {
            
            
            dto.setNombreNegocio(jLabel1.getText());
            List<ProductoDTO> lista = new ArrayList<>();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                ProductoDTO dtoModelo = new ProductoDTO();
                dtoModelo.setIdproducto(Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                dtoModelo.setNombre(jTable1.getValueAt(i, 1).toString());
                dtoModelo.setPrecio(Double.parseDouble(jTable1.getValueAt(i,2).toString()));
                dtoModelo.setPreciocliente(Double.parseDouble(jTable1.getValueAt(i,3).toString()));
                dtoModelo.setStock(Integer.parseInt(jTable1.getValueAt(i,4).toString()));
                dtoModelo.setPreciodeproveedor(Double.parseDouble(jTable1.getValueAt(i,5).toString()));
                UnidadesMedidaDTO unidad = controllerUnidades.unidadByNombre(jTable1.getValueAt(i,6).toString());
                dtoModelo.setIdunidadm(unidad.getIdunidadm());
                ProveedorDTO proveedor = controllerProveedor.proveedorByNombre(jTable1.getValueAt(i,7).toString());
                dtoModelo.setIdproveedor(proveedor.getIdproveedor());
                CategoriaDTO categoria = controllerCategoria.categoriaByNombre(jTable1.getValueAt(i,8).toString());
                dtoModelo.setIdcategoria(categoria.getIdcategoria());
                lista.add(dtoModelo);
            }
            dto.setProductos(lista);
        } catch (Exception e) {
            System.out.println("Error al llenar Datos reporte:" + e.getMessage());
        }
        return dto;
    }

    public void llenarNombreNegocio() {
        NegocioController negocio = new NegocioController();
        try {
            jLabel1.setText(negocio.busquedaDatos().getNombre().toLowerCase());
        } catch (Exception e) {
            System.out.println("Error al obtener nombre neogcio:" + e.getMessage());
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
