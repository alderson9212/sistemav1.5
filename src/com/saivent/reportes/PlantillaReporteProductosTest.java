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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;


public class PlantillaReporteProductosTest {
      
    public static void main(String[] args) {
         Document documento = new Document();
        try {
            String ruta="/home/wilmer/Escritorio/pdfs/docTest.pdf";
            documento = new Document(PageSize.A4.rotate());
            FileOutputStream archivo = new FileOutputStream(ruta);
            Paragraph TituloReporte = new Paragraph("REPORTE PRODUCTOS", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
            Paragraph Empresa = new Paragraph("NEGOCIO", FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
            

            //Creado encabezados
            Paragraph codigo = new Paragraph("CODIGO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph nombre = new Paragraph("NOMBRE", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph ppublico = new Paragraph("P.PUBLICO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph pcliente = new Paragraph("P.CLIENTE", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));

            HeaderFooterPageEvent footer = new HeaderFooterPageEvent();

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
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
            
           
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            PdfPCell celdaCodigo = new PdfPCell(codigo);
            PdfPCell celdaNombre = new PdfPCell(nombre);
            PdfPCell celdaPpublico = new PdfPCell(ppublico);
            PdfPCell celdapcliente = new PdfPCell(pcliente);
            celdaCodigo.setPadding(0);
            //Diseñando encabezados
            celdaCodigo.setBackgroundColor(BaseColor.BLACK);
            celdaCodigo.setBorder(0);
            celdaCodigo.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaNombre.setBackgroundColor(BaseColor.BLACK);
            celdaNombre.setBorder(0);
            celdaNombre.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaPpublico.setBackgroundColor(BaseColor.BLACK);
            celdaPpublico.setBorder(0);
            celdaPpublico.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdapcliente.setBackgroundColor(BaseColor.BLACK);
            celdapcliente.setBorder(0);
            celdapcliente.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
             tabla.setWidths(new float[] {2, 4, 10, 10});
           
            
            tabla.addCell(celdaCodigo);
            tabla.addCell(celdaNombre);
            tabla.addCell(celdaPpublico);
            tabla.addCell(celdapcliente);

            for (int i = 0; i < 3; i++) {               
                tabla.addCell("celda:"+i);
            }
            documento.add(tabla);
            writer.setPageEvent(footer);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error al crear archivo:" + e.getMessage());
        }

    
    }
}
