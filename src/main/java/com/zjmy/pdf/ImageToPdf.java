package com.zjmy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.consts.FileConsts;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        Image image = Image.getInstance("F:\\ideaProject\\pdf\\src\\main\\resource\\cherry.jpg");
        //图片大小
        image.scaleAbsolute(120f, 120f);
        document.add(image);

        document.close();
    }
}
