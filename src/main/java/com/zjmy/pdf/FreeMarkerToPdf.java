package com.zjmy.pdf;

import com.itextpdf.text.DocumentException;
import com.zjmy.consts.FileConsts;
import com.zjmy.util.FreeMarkerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerToPdf {

    public static void main(String[] args) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "飞雪");
        map.put("author", "郑板桥");

        //normalFreemarkerToPdf(map);
        CssFreemarkerToPdf(map);
    }

    /**
     * 普通freemarker生成pdf
     */
    public static void normalFreemarkerToPdf(Map<String, Object> map) throws IOException, DocumentException {
        String content = FreeMarkerUtil.loadFtlHtml(map, FileConsts.FREEMARKER_NAME);
        FreeMarkerUtil.createPdf(content);
    }

    /**
     * 含有CSS高级特性的freemarker生成pdf
     */
    public static void CssFreemarkerToPdf(Map<String, Object> map) throws IOException, com.lowagie.text.DocumentException {
        String content = FreeMarkerUtil.loadFtlHtml(map, FileConsts.FREEMARKER_CSS_NAME);
        FreeMarkerUtil.createCssPdf(content);
    }
}
