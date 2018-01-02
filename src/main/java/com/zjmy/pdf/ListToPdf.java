package com.zjmy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;
import com.zjmy.util.FilePath;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ListToPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FilePath.TEXT_PDF));
        document.open();

        //将第一个参数作为 true 传送，表明创建带编号的列表
        List list = new List(true, false, 10);
        list.add(new ListItem("this is the first listItem"));
        list.add(new ListItem("this is the second listItem"));
        document.add(list);

        document.close();
        pdfWriter.close();
    }
}
