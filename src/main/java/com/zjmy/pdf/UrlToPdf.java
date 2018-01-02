package com.zjmy.pdf;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.util.FilePath;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UrlToPdf {

    /**
     *  链接分为两种，外部链接和内部链接
     *  外部链接：网站链接(url)
     *  内部链接：pdf上下文
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FilePath.TEXT_PDF));
        document.open();

        //外部链接
        Anchor outside = new Anchor("search now");
        outside.setReference("www.baidu.com");
        document.add(outside);
        document.add(new Paragraph(""));

        //内部链接
        Anchor anchorTarget = new Anchor("What color is the sun ?");
        anchorTarget.setName("question");
        document.add(anchorTarget);
        for (int i = 0; i < 30; i++) {
            document.add(new Paragraph("I wanna know"));
        }
        Anchor inside = new Anchor("what is the question ? ");
        inside.setReference("#question");
        document.add(inside);

        document.close();
        pdfWriter.close();
    }
}
