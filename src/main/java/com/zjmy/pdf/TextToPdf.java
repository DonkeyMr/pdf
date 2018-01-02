package com.zjmy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.consts.FileConsts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TextToPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        //textToPdf();
        textToPdf2();
    }

    public static void textToPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();
        //任何文本都借助 com.itextpdf.text.Paragraph 来进行添加
        document.add(new Paragraph("hello world"));
        document.close();
        pdfWriter.close();
    }

    public static void textToPdf2() throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new CMYKColor(0, 255, 255, 17));
        Paragraph title = new Paragraph("chapter 1", titleFont);
        //章
        Chapter chapter1 = new Chapter(title, 1);

        //节
        titleFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255, 17));
        Paragraph sectionTitle = new Paragraph("This is Section 1 in Chapter 1", titleFont);
        Section section1 = chapter1.addSection(sectionTitle);
        //小节内容
        Paragraph sectionText = new Paragraph("some text in section 1");
        section1.add(sectionText);
        sectionText = new Paragraph("other text in section 1");
        section1.add(sectionText);

        document.add(chapter1);
        document.close();
        pdfWriter.close();
    }
}
