package com.zjmy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.zjmy.consts.FileConsts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        //createImage();
        createWatermarkedImage();
    }

    public static void createImage() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        Image image = Image.getInstance(FileConsts.IMAGE);
        //图片大小
        image.scaleAbsolute(120f, 120f);
        document.add(image);

        document.close();
    }

    public static void createWatermarkedImage() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        PdfContentByte cb = pdfWriter.getDirectContentUnder();
        document.add(getWatermarkedImage(cb, Image.getInstance(FileConsts.IMAGE), "nice"));
        //第二张图片
        document.add(new Paragraph());
        Image image = Image.getInstance(FileConsts.IMAGE);
        image.scaleToFit(400, 700);
        document.add(getWatermarkedImage(cb, image, "MrDonkey"));

        document.close();
    }

    public static Image getWatermarkedImage(PdfContentByte cb, Image img, String watermark) throws DocumentException {
        float width = img.getScaledWidth();
        float height = img.getScaledHeight();
        PdfTemplate template = cb.createTemplate(width, height);
        template.addImage(img, width, 0, 0, height, 0, 0);
        //Font FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.BLUE);
        /**
         * width：水印离图片左边距离
         * height：水印离图片下边距离
         * rotation：水印倾斜角度
         */
        ColumnText.showTextAligned(template, Element.ALIGN_CENTER,
                new Phrase(watermark, font), width / 1.2f, height / 4, 30);
        return Image.getInstance(template);
    }
}
