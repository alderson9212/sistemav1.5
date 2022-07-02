/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.reportes;

import com.sistema.controller.ProductoController;
import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.ReporteNotaVentaModelo;

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
import com.sistema.modelo.ProductoDTO;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;



/**
 *
 * @author Elliot
 */
public class PlantillaReporteNota {

    ReporteNotaVentaModelo dto = new ReporteNotaVentaModelo();

    public PlantillaReporteNota(ReporteNotaVentaModelo dto) {
        this.dto = dto;
    }

    public void generar_y_guardar(int opcion) {

        try {
            PDDocument document;
            try {
                String home = System.getProperty("user.home");
                String separator = System.getProperty("file.separator");
                String ruta = home + separator + "dinamico.pdf";
                gerarDoc(ruta, opcion);
                
                PrinterJob job = PrinterJob.getPrinterJob();
                document = PDDocument.load(new File(ruta));
               
                if (job.printDialog() == true) {
                    job.setPageable(new PDFPageable(document));
                    job.print();
                    File file = new File(ruta);
                    file.delete();
                }

            } catch (IOException ex) {
                System.out.println("Error encontrado:"+ex.getMessage());
                Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrinterException ex) {
                System.out.println("Error :"+ex.getMessage());
                Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR ARCHIVO:" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Document gerarDoc(String ruta, int op) {
        // Se crea el documento
        Document documento = new Document();
        try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            Paragraph TituloReporte = new Paragraph("NOTA VENTA", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
            Paragraph Empresa = new Paragraph(dto.getNombreNegocio(), FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
            Paragraph Fecha = new Paragraph(dto.getFecha(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK));

            //Creado encabezados
            Paragraph productop = new Paragraph("PRODUCTO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph cantidadp = new Paragraph("CANTIDAD", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph preciop = new Paragraph("PRECIO UNITARIO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph totalp = new Paragraph("$TOTAL", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph totalNota = new Paragraph("$TOTAL VENTA:" + dto.getTotalVenta(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK));
            Paragraph folio = new Paragraph("NO.FOLIO:" + dto.getDetallesVenta().get(0).getTicket(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK));

            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);

            // Se abre el documento.
            TituloReporte.setAlignment(1);
            Empresa.setAlignment(1);
            documento.open();
            documento.add(TituloReporte);
            documento.add(Empresa);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);

            folio.setAlignment(2);
            documento.add(folio);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);

            PdfPCell celdaProducto = new PdfPCell(productop);
            PdfPCell celdaCantidad = new PdfPCell(cantidadp);
            PdfPCell celdaPrecio = new PdfPCell(preciop);
            PdfPCell celdaTotal = new PdfPCell(totalp);

            //Diseñando encabezados
            celdaProducto.setBackgroundColor(BaseColor.BLACK);
            celdaProducto.setBorder(0);
            celdaProducto.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaCantidad.setBackgroundColor(BaseColor.BLACK);
            celdaCantidad.setBorder(0);
            celdaCantidad.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaPrecio.setBackgroundColor(BaseColor.BLACK);
            celdaPrecio.setBorder(0);
            celdaPrecio.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaTotal.setBackgroundColor(BaseColor.BLACK);
            celdaTotal.setBorder(0);
            celdaTotal.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            tabla.addCell(celdaProducto);
            tabla.addCell(celdaCantidad);
            tabla.addCell(celdaPrecio);
            tabla.addCell(celdaTotal);

            for (int i = 0; i < dto.getDetallesVenta().size(); i++) {
                DetalleVentaDTO ticket = dto.getDetallesVenta().get(i);
                PdfPCell celdaProductoContenido = new PdfPCell(new Paragraph(ticket.getProducto(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaProductoContenido.setBorder(0);
                celdaProductoContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                PdfPCell celdaCantidadContenido = new PdfPCell(new Paragraph(String.valueOf(ticket.getTotalProducto()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaCantidadContenido.setBorder(0);
                celdaCantidadContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                PdfPCell celdaPrecioContenido = new PdfPCell();
                ProductoDTO producto = new ProductoController().productoByNombre(ticket.getProducto());
                System.out.println("Producto:" + producto.getNombre());
                if (op == 1) {
                    celdaPrecioContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getPrecio()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                } else if (op == 2) {
                    celdaPrecioContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getPreciocliente()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                }

                celdaPrecioContenido.setBorder(0);
                celdaPrecioContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                PdfPCell celdaTotalContenido = new PdfPCell(new Paragraph(String.valueOf(ticket.getTotal()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaTotalContenido.setBorder(0);
                celdaTotalContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                tabla.addCell(celdaProductoContenido);
                tabla.addCell(celdaCantidadContenido);
                tabla.addCell(celdaPrecioContenido);
                tabla.addCell(celdaTotalContenido);
            }
            documento.add(tabla);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla2 = new PdfPTable(2);
            tabla2.setWidthPercentage(100);
            PdfPCell celdaFecha = new PdfPCell(Fecha);
            PdfPCell celdaTotalNota = new PdfPCell(totalNota);

            celdaTotalNota.setBorder(0);
            celdaTotalNota.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
            celdaFecha.setBorder(0);

            tabla2.addCell(celdaFecha);
            tabla2.addCell(celdaTotalNota);
            documento.add(tabla2);

            documento.close();
        } catch (Exception e) {
            System.out.println("Error al crear archivo:" + e.getMessage());
        }

        return documento;
    }
}
