package com.zjmy.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zjmy.consts.FileConsts;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

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

    /**
     * freemarker生成pdf
     * @param content html字符流
     */
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

    /**
     * 含有CSS高级样式的freemarker生成pdf
     * @param content html字符流
     * 1.在某些场景下，html中的静态资源是在本地，我们可以使用render.getSharedContext().setBaseURL()加载文件资源,
     *   注意资源URL需要使用文件协议 “file:///”
     * 2.对于生成的pdf页面大小，可以用css的@page属性设置。
     */
    public static void createCssPdf(String content) throws IOException, com.lowagie.text.DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(FileConsts.FONT_HEI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        renderer.setDocumentFromString(content);
        //解决图片相对路径的问题
        renderer.getSharedContext().setBaseURL("file:///" + FileConsts.IMAGE);
        renderer.layout();
        renderer.createPDF(new FileOutputStream(FileConsts.TEXT_PDF));
    }

    /**
     * 根据freemarker模板生成pdf文件流
     */
    public static ByteArrayOutputStream getPdfOutputStream(String content) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
            fontResolver.addFont(FileConsts.FONT_HEI, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //解析html生成pdf
        renderer.setDocumentFromString(content);
        //解决图片相对路径问题
        renderer.getSharedContext().setBaseURL("file:///" + FileConsts.IMAGE);
        renderer.layout();
        try {
            renderer.createPDF(outputStream);
            return outputStream;
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
