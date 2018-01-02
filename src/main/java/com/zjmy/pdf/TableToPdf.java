package com.zjmy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.consts.FileConsts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TableToPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        PdfPTable table = new PdfPTable(3);
        //table.setSpacingBefore(20);
        //table.setSpacingAfter(20);
        PdfPCell cell = new PdfPCell(new Paragraph("name"));
        table.addCell(cell);
        table.addCell("sex");
        table.addCell("age");
        table.addCell("zhangsan");
        table.addCell("man");
        table.addCell("18");

        document.add(table);
        document.close();
    }
}
