package com.zjmy.pdf;

import com.zjmy.consts.FileConsts;
import com.zjmy.util.FreeMarkerUtil;
import com.zjmy.util.PdfUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfToimage {

    public static void main(String[] args) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "飞雪");
        map.put("author", "郑板桥");
        String content = FreeMarkerUtil.loadFtlHtml(map, FileConsts.FREEMARKER_CSS_NAME);
        ByteArrayOutputStream pdfOutputStream = FreeMarkerUtil.getPdfOutputStream(content);
        ByteArrayOutputStream imgOutputStream = PdfUtil.pdfToImg(pdfOutputStream.toByteArray(), 2,
                1, FileConsts.IMAGE_FORMAT);

        FileOutputStream fileOutputStream = new FileOutputStream(FileConsts.IMAGE_SAVE);
        fileOutputStream.write(imgOutputStream.toByteArray());
        fileOutputStream.close();
    }
}
