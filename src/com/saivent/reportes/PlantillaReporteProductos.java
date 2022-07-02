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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.saivent.principal.mainTest;
import com.sistema.controller.CategoriaController;
import com.sistema.controller.ProveedorController;
import com.sistema.controller.UnidadesMedidaController;
import com.sistema.modelo.CategoriaDTO;
import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
import com.sistema.modelo.ReportesModelo;
import com.sistema.modelo.UnidadesMedidaDTO;
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
public class PlantillaReporteProductos {

    ReportesModelo dto = new ReportesModelo();

    public PlantillaReporteProductos(ReportesModelo dto) {
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
        
 
            documento = new Document(PageSize.A4.rotate());
            FileOutputStream archivo = new FileOutputStream(ruta);
            Paragraph TituloReporte = new Paragraph("REPORTE PRODUCTOS", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
            Paragraph Empresa = new Paragraph(dto.getNombreNegocio(), FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
         
            //Creado encabezados
            Paragraph codigo = new Paragraph("CODIGO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph nombre = new Paragraph("NOMBRE", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph ppublico = new Paragraph("P.PUBLICO", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph pcliente = new Paragraph("P.CLIENTE", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph stock = new Paragraph("EXISTENCIAS", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph pcompra= new Paragraph("PRECIO COMPRA", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph medida = new Paragraph("MEDIDA", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph proveedor = new Paragraph("PROVEEDOR", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));
            Paragraph categoria = new Paragraph("CATEGORIA", FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.WHITE));

            HeaderFooterPageEventRProductos footer = new HeaderFooterPageEventRProductos();

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
          
            PdfPTable tabla = new PdfPTable(9);
            tabla.setWidthPercentage(100);
            PdfPCell celdaCodigo = new PdfPCell(codigo);
            PdfPCell celdaNombre = new PdfPCell(nombre);
            PdfPCell celdaPpublico = new PdfPCell(ppublico);
            PdfPCell celdapcliente = new PdfPCell(pcliente);
            PdfPCell celdaStock = new PdfPCell(stock);
            PdfPCell celdaPcompra = new PdfPCell(pcompra);
            PdfPCell celdaMedida = new PdfPCell(medida);
            PdfPCell celdaProveedor = new PdfPCell(proveedor);
            PdfPCell celdaCategoria = new PdfPCell(categoria);
            
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
            
            celdaStock.setBackgroundColor(BaseColor.BLACK);
            celdaStock.setBorder(0);
            celdaStock.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaPcompra.setBackgroundColor(BaseColor.BLACK);
            celdaPcompra.setBorder(0);
            celdaPcompra.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            
            celdaMedida.setBackgroundColor(BaseColor.BLACK);
            celdaMedida.setBorder(0);
            celdaMedida.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            
            celdaProveedor.setBackgroundColor(BaseColor.BLACK);
            celdaProveedor.setBorder(0);
            celdaProveedor.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

            celdaCategoria.setBackgroundColor(BaseColor.BLACK);
            celdaCategoria.setBorder(0);
            celdaCategoria.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            
            tabla.addCell(celdaCodigo);
            tabla.addCell(celdaNombre);
            tabla.addCell(celdaPpublico);
            tabla.addCell(celdapcliente);
            tabla.addCell(celdaStock);
            tabla.addCell(celdaPcompra);
            tabla.addCell(celdaMedida);
            tabla.addCell(celdaProveedor);
            tabla.addCell(celdaCategoria);
            //Diseñamos los encabezados
            tabla.setWidths(new float[] {1.2f,0.8f,1,0.8f,0.9f,1.4f,0.8f,1,1});

            for (int i = 0; i < dto.getProductos().size(); i++) {
                
                ProductoDTO producto = dto.getProductos().get(i);
                PdfPCell celdaCodigoContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getIdproducto()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaCodigoContenido.setBorder(0);
                celdaCodigoContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                PdfPCell celdaNombreContenido = new PdfPCell(new Paragraph(producto.getNombre(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaNombreContenido.setBorder(0);
                celdaNombreContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                PdfPCell celdaPrecioContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getPrecio()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaPrecioContenido.setBorder(0);
                celdaPrecioContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                PdfPCell celdaPrecioClienteContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getPreciocliente()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaPrecioClienteContenido.setBorder(0);
                celdaPrecioClienteContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                PdfPCell celdaExistenciaContenido = new PdfPCell(new Paragraph(String.valueOf(producto.getStock()), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaExistenciaContenido.setBorder(0);
                celdaExistenciaContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                PdfPCell celdaPrecioCompraContenido = new PdfPCell(new Paragraph(producto.getPreciodeproveedor().toString(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaPrecioCompraContenido.setBorder(0);
                celdaPrecioCompraContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                UnidadesMedidaDTO unidad = new UnidadesMedidaController().unidadById(producto.getIdunidadm());                
                PdfPCell celdaMedidaContenido = new PdfPCell(new Paragraph(unidad.getDescripcion(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaMedidaContenido.setBorder(0);
                celdaMedidaContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                ProveedorDTO proveedorD = new ProveedorController().proveedorById(producto.getIdproveedor());                
                PdfPCell celdaProveedorContenido = new PdfPCell(new Paragraph(proveedorD.getNombre(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaProveedorContenido.setBorder(0);
                celdaProveedorContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
                
                CategoriaDTO categoriaD = new CategoriaController().categoriaById(producto.getIdcategoria());
                PdfPCell celdaCategoriaContenido = new PdfPCell(new Paragraph(categoriaD.getDescripcion(), FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK)));
                celdaCategoriaContenido.setBorder(0);
                celdaCategoriaContenido.setHorizontalAlignment(Paragraph.ALIGN_CENTER);

                tabla.addCell(celdaCodigoContenido);
                tabla.addCell(celdaNombreContenido);
                tabla.addCell(celdaPrecioContenido);
                tabla.addCell(celdaPrecioClienteContenido);
                tabla.addCell(celdaExistenciaContenido);
                tabla.addCell(celdaPrecioCompraContenido);
                tabla.addCell(celdaMedidaContenido);
                tabla.addCell(celdaProveedorContenido);
                tabla.addCell(celdaCategoriaContenido);
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
