package com.zjmy.pdf;

import com.itextpdf.text.DocumentException;
import com.zjmy.consts.FileConsts;
import com.zjmy.util.FreeMarkerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerToPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "飞雪");
        map.put("author", "郑板桥");
        String content = FreeMarkerUtil.loadFtlHtml(map, FileConsts.FREEMARKER_NAME);
        FreeMarkerUtil.createPdf(content);
    }
}
