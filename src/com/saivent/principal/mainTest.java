/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.principal;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author wilmer
 */
public class mainTest {

    static public void main(String[] args) {
       // mainTest printer = new mainTest();

        /*
        printer.listarImpresoras();

        try {
            ByteArrayOutputStream documentoBytes = printer.crearDocumentoiText();
            printer.imprimir(documentoBytes);
        } catch (IOException | PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error de impresion", "Error", JOptionPane.ERROR_MESSAGE);

        }*/
        PDDocument document;
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            
            document = PDDocument.load(new File("/home/wilmer/Escritorio/pdfs/1.pdf"));
             //LOGGER.log(Level.INFO, "Mostrando el dialogo de impresion");
        if (job.printDialog() == true) {
            job.setPageable(new PDFPageable(document));

            //LOGGER.log(Level.INFO, "Imprimiendo documento");
            job.print();
        }
        } catch (IOException ex) {
            Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrinterException ex) {
            Logger.getLogger(mainTest.class.getName()).log(Level.SEVERE, null, ex);
        }

      

       
    }

    /**
     * Envia a imprimir el ByteArrayOutoutStream creado de un documento iText
     *
     * @param documentoBytes
     * @throws IOException
     * @throws PrinterException
     */
    public void imprimir(ByteArrayOutputStream documentoBytes) throws IOException, PrinterException {

        // Aqui convertimos la el arreglo de salida a uno de entrada que podemos
        // mandar a la impresora
        try {
            File file = new File("/home/wilmer/Escritorio/pdfs/1.pdf");
            FileOutputStream fils = new FileOutputStream(file);
            ByteArrayInputStream bais = new ByteArrayInputStream(documentoBytes.toByteArray());

            //Creamos un PDDocument con el arreglo de entrada que creamos        
            PDDocument document = PDDocument.load(bais);
            PrintService myPrintService = this.findPrintService("CX-6545");
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            printerJob.setPageable(new PDFPageable(document));
            printerJob.setPrintService(myPrintService);

            printerJob.print();
        } catch (Exception e) {

        }

    }

    /**
     * Muestra en pantalla la lista de todas las impresoras disponibles en el
     * sistema
     */
    public void listarImpresoras() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Lista de impresoras disponibles");

        for (PrintService printService : printServices) {
            System.out.println("\t" + printService.getName());
        }
    }

    /**
     * Nos regresa el PrintService que representa la impresora con el nombre que
     * le indiquemos
     *
     * @param printerName nombre de la impresora que deseamos usar
     * @return PrintService que representa la impresora que deseamos usar
     */
    private PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());

            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    /**
     * Crea un documento via la libreria iText y lo almacena como un
     * ByteArrayOutputStream
     *
     * @return Documento iText en formato ByteArrayOutputStream
     */
    public ByteArrayOutputStream crearDocumentoiText() {
        // Es en este ByteArrayOutputStream donde se pone el documento una vez 
        // que se llama a documento.close()
        ByteArrayOutputStream documentoBytes = new ByteArrayOutputStream();

        PdfWriter pdfWriter = new PdfWriter(documentoBytes);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);

        Document documento = new Document(pdfDoc, PageSize.LETTER);
        
        documento.add(new Paragraph("Inicia el reporte"));
        documento.add(this.crearTabla());

        documento.close();

        return documentoBytes;
    }

    private Table crearTabla() {
        float[] anchos = {50F, 50F, 50F};
        Table tablaEncabezado = new Table(anchos);

        tablaEncabezado.setWidth(500F);

        tablaEncabezado.addCell("Hora Inicio");
        tablaEncabezado.addCell("Hora Fin");
        tablaEncabezado.addCell("");
        tablaEncabezado.addCell("Fecha Inicio");
        tablaEncabezado.addCell("Fecha Fin");
        tablaEncabezado.addCell("Fin de Turno");

        return tablaEncabezado;
    }

}
