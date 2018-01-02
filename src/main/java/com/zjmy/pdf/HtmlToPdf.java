package com.zjmy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zjmy.consts.FileConsts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class HtmlToPdf {

    /**
     *  1.html中必须使用标准的语法，标签一定需要闭合
     *  2.html中如果有中文，需要在样式中添加对应字体的样式
     */
    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FileConsts.FONT_HEI);
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(FileConsts.HTML),
                Charset.forName("UTF-8"), fontProvider);

        document.close();
    }
}
