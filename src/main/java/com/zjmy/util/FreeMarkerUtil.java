package com.zjmy.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zjmy.consts.FileConsts;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

public class FreeMarkerUtil {

    public static String loadFtlHtml(Map<String, Object> data, String ftlName) {
        Writer  writer = new StringWriter();
        Configuration cfg = new Configuration();
        try {
            //freemarker的模板目录
            cfg.setDirectoryForTemplateLoading(new File(FileConsts.ROOT_DIR));
            // 获取模板,并设置编码方式
            Template template = cfg.getTemplate(ftlName);
            template.setEncoding("UTF-8");
            //合并数据模型与模板,并写入到流中，这里使用的字符流
            template.process(data, writer);
            writer.flush();
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void createPdf(String content) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FileConsts.TEXT_PDF));
        document.open();

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FileConsts.FONT_HEI);
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(content.getBytes()),
                Charset.forName("UTF-8"), fontProvider);

        document.close();
    }
}
