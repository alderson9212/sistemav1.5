/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.reportes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.saivent.principal.mainTest;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ReportesModelo;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author Elliot
 */
public class PlantillaReporteVenta {

    ReportesModelo dto = new ReportesModelo();

    public PlantillaReporteVenta(ReportesModelo dto) {
        this.dto = dto;
    }

    public void generar_y_guardar(int opcion) {

        try {
            
            if (opcion == 1) {
                JFileChooser guardar = new JFileChooser();                
                guardar.setApproveButtonText("Guardar");
                guardar.showSaveDialog(null);
                String ruta = guardar.getSelectedFile().getPath();
                gerarDoc(ruta+".pdf");

            } else if(opcion == 2) {
                PDDocument document;
                try {
                    String home = System.getProperty("user.home");
                    String separator = System.getProperty("file.separator");
                    
                    String ruta = home + separator + "dinamico.pdf";
                    gerarDoc(ruta);
                                        
                    PrinterJob job = PrinterJob.getPrinterJob();
                    document = PDDocument.load(new File(ruta));

                    if (job.printDialog() == true) {
                        job.setPageable(new PDFPageable(document));
                        job.print();
                    }
                    
                   File file = new File(ruta);
                   if(file.exists()){
                       file.delete();
                   } 
                } catch (IOException ex) {
                    Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PrinterException ex) {
                    Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR ARCHIVO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Document gerarDoc(String ruta) {
        // Se crea el documento
        Document documento = new Document();
        try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            Paragraph TituloReporte = new Paragraph("REPORTE VENTAS", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
            Paragraph Empresa = new Paragraph(dto.getNombreNegocio(), FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
            Paragraph FechaReporte = new Paragraph("Reporte del : " + dto.getFechaInicial() + " al " + dto.getFechaFinal(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK));

            //Creado encabezados
            Paragraph Ticket = new Paragraph("TICKET", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph Producto = new Paragraph("PRODUCTO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph TotalProductos = new Paragraph("NO.PRODUCTOS", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph TotalVenta = new Paragraph("$TOTAL", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph Cliente = new Paragraph("CLIENTE", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));

            HeaderFooterPageEvent footer = new HeaderFooterPageEvent();

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);

            // Se abre el documento.
            TituloReporte.setAlignment(1);
            Empresa.setAlignment(1);
            FechaReporte.setAlignment(2);
            documento.open();
            documento.add(TituloReporte);
            documento.add(Empresa);
            documento.add(Chunk.NEWLINE);
            documento.add(FechaReporte);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            PdfPCell celdaTicket = new PdfPCell(Ticket);
            PdfPCell celdaProducto = new PdfPCell(Producto);
            PdfPCell celdaTotalProductos = new PdfPCell(TotalProductos);
            PdfPCell celdaTotalVenta = new PdfPCell(TotalVenta);
            PdfPCell celdaCliente = new PdfPCell(Cliente);

            //Diseñando encabezados
            celdaTicket.setBackgroundColor(BaseColor.BLACK);
            celdaTicket.setBorder(0);
            celdaTicket.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaProducto.setBackgroundColor(BaseColor.BLACK);
            celdaProducto.setBorder(0);
            celdaProducto.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaTotalProductos.setBackgroundColor(BaseColor.BLACK);
            celdaTotalProductos.setBorder(0);
            celdaTotalProductos.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaTotalVenta.setBackgroundColor(BaseColor.BLACK);
            celdaTotalVenta.setBorder(0);
            celdaTotalVenta.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaCliente.setBackgroundColor(BaseColor.BLACK);
            celdaCliente.setBorder(0);
            celdaCliente.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            tabla.addCell(celdaTicket);
            tabla.addCell(celdaProducto);
            tabla.addCell(celdaTotalProductos);
            tabla.addCell(celdaTotalVenta);
            tabla.addCell(celdaCliente);

            for (int i = 0; i < dto.getDetallesVenta().size(); i++) {
                DetalleVentaDTO venta = dto.getDetallesVenta().get(i);
                PdfPCell celdaTicketContenido = new PdfPCell(new Paragraph(String.valueOf(venta.getTicket()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaTicketContenido.setBorder(0);
                celdaTicketContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                PdfPCell celdaProductoContenido = new PdfPCell(new Paragraph(venta.getProducto(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaProductoContenido.setBorder(0);
                celdaProductoContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                PdfPCell celdaTotalProductosContenido = new PdfPCell(new Paragraph(String.valueOf(venta.getTotalProducto()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaTotalProductosContenido.setBorder(0);
                celdaTotalProductosContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                PdfPCell celdaTotalVentaContenido = new PdfPCell(new Paragraph(String.valueOf(venta.getTotal()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaTotalVentaContenido.setBorder(0);
                celdaTotalVentaContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                PdfPCell celdaClienteContenido = new PdfPCell(new Paragraph(venta.getCliente(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaClienteContenido.setBorder(0);
                celdaClienteContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                tabla.addCell(celdaTicketContenido);
                tabla.addCell(celdaProductoContenido);
                tabla.addCell(celdaTotalProductosContenido);
                tabla.addCell(celdaTotalVentaContenido);
                tabla.addCell(celdaClienteContenido);
            }
            documento.add(tabla);
            writer.setPageEvent(footer);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error al crear archivo:" + e.getMessage());
        }

        return documento;
    }
}
