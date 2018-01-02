package com.zjmy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.consts.FileConsts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ChineseToPdf {

    //iText默认是不支持中文展示的
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        Font font = FontFactory.getFont(FileConsts.FONT_SONG, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,
                16, Font.NORMAL, new CMYKColor(0, 255, 255, 17));
        Paragraph paragraph = new Paragraph("呵呵哒", font);
        document.add(paragraph);

        font = FontFactory.getFont(FileConsts.FONT_HEI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        paragraph = new Paragraph("呵呵哒", font);
        document.add(paragraph);

        document.close();
    }
}
