/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.reportes;

import com.saivent.diseños.GestionCeldas;
import com.saivent.diseños.GestionEncabezadoTabla;
import com.saivent.diseños.ModeloTabla;
import com.saivent.principal.*;
import com.saivent.reportes.*;
import com.sistema.controller.NegocioController;
import com.sistema.controller.VentasController;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ReportesModelo;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Elliot
 */
public class FReporteVentas1 extends javax.swing.JFrame {

    VentasController controllerVentas = new VentasController();
    DefaultTableModel dtm;

    ModeloTabla modelo;
    private int filasTabla;
    private int columnasTabla;

    ArrayList<DetalleVentaDTO> listaDetallesVenta;

    /**
     * Creates new form FReporteVentas
     */
    public FReporteVentas1() {
        initComponents();

        diseñarJDateChooser(jDateChooser1);
        diseñarJDateChooser(jDateChooser2);
        llenarNombreNegocio(jLabel1);
        construirTabla();
        this.setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(85, 112, 148));

        jLabel1.setBackground(new java.awt.Color(85, 112, 148));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TU NEGOCIO");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Fecha Inicial");

        jDateChooser1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        jLabel3.setBackground(new java.awt.Color(253, 251, 251));
        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha Final");

        jDateChooser2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

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
        jButton4.setText("Filtrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
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
        //listarDetallesVenta();
        construirTabla();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PlantillaReporteVenta plantilla = new PlantillaReporteVenta(datosEnviarPDF());
        plantilla.generar_y_guardar(1);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        PlantillaReporteVenta plantilla = new PlantillaReporteVenta(datosEnviarPDF());
        plantilla.generar_y_guardar(2);
    }//GEN-LAST:event_btnPrintActionPerformed

    
    private void construirTabla() {
        listaDetallesVenta = consultarListaDetallesVenta();
        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("TICKET");
        titulosList.add("PRODUCTO");
        titulosList.add("TOTAL PRODUCTOS");
        titulosList.add("TOTAL VENTA");
        titulosList.add("CLIENTE");

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

    private ArrayList<DetalleVentaDTO> consultarListaDetallesVenta() {
        ArrayList<DetalleVentaDTO> lista = (ArrayList<DetalleVentaDTO>) controllerVentas.listaDetallesVenta(fechaParseada(jDateChooser1.getDate()), fechaParseada(jDateChooser2.getDate()));
        return lista;
    }

    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {
        String informacion[][] = new String[listaDetallesVenta.size()][titulosList.size()];
        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = listaDetallesVenta.get(x).getTicket() + "";
            informacion[x][1] = listaDetallesVenta.get(x).getProducto() + "";
            informacion[x][2] = listaDetallesVenta.get(x).getTotalProducto() + "";
            informacion[x][3] = listaDetallesVenta.get(x).getTotal() + "";
            informacion[x][4] = listaDetallesVenta.get(x).getCliente() + "";
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
        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 5; i++) {
            //se resta 5 porque las ultimas 7 columnas se definen arriba           
            jTable1.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setRowHeight(25);//tamaño de las celdas
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(175);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(380);
        //personaliza el encabezado
        JTableHeader jtableHeader = jTable1.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        jTable1.setTableHeader(jtableHeader);

    }
   
    
    public ReportesModelo datosEnviarPDF() {
        ReportesModelo dto = new ReportesModelo();
        try {
            dto.setFechaInicial(fechaParseada(jDateChooser1.getDate()));
            dto.setFechaFinal(fechaParseada(jDateChooser2.getDate()));
            dto.setNombreNegocio(jLabel1.getText());
            List<DetalleVentaDTO> lista = new ArrayList<>();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                DetalleVentaDTO dtoV = new DetalleVentaDTO();
                dtoV.setTicket(Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                dtoV.setProducto(jTable1.getValueAt(i, 1).toString());
                dtoV.setTotalProducto(Integer.parseInt(jTable1.getValueAt(i, 2).toString()));
                dtoV.setTotal(Double.parseDouble(jTable1.getValueAt(i, 3).toString()));
                dtoV.setCliente(jTable1.getValueAt(i, 4).toString());
                lista.add(dtoV);
            }
            dto.setDetallesVenta(lista);
        } catch (Exception e) {
            System.out.println("Error al llenar Datos reporte:" + e.getMessage());
        }
        return dto;
    }

    public void llenarNombreNegocio(JLabel label) {
        NegocioController negocio = new NegocioController();
        try {
            label.setText(negocio.busquedaDatos().getNombre().toLowerCase());
        } catch (Exception e) {
            System.out.println("Error al obtener nombre neogcio:" + e.getMessage());
        }

    }

    public String fechaParseada(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "";
        try {
            if (date != null) {
                fecha = sdf.format(date);
            }
        } catch (Exception e) {
            System.out.println("Error al parsear fecha:" + e.getMessage());
        }
        return fecha;
    }

    public void diseñarJDateChooser(JDateChooser jdc) {
        ((JTextField) jdc.getDateEditor()).setEditable(false);
        jdc.setDate(new Date());
    }

    public static void main(String[] args) {
        new FReporteVentas1().setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
