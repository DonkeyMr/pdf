package com.zjmy.util;

import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import org.jpedal.fonts.FontMappings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfUtil {

    /**
     * 根据pdf二进制文件 生成图片文件
     * Jpedal支持将指定页Pdf生成图片，pdfDecoder.scaling设置图片的分辨率(不同分辨率下文件大小不同),
     *   支持多种图片格式
     * @param bytes   pdf二进制
     * @param scaling 清晰度
     * @param pageNum 页数
     */
    public static ByteArrayOutputStream pdfToImg(byte[] bytes, float scaling, int pageNum,String formatName) {
        //推荐的方法打开PdfDecoder
        PdfDecoder pdfDecoder = new PdfDecoder(true);
        FontMappings.setFontReplacements();
        //修改图片的清晰度
        pdfDecoder.scaling = scaling;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //打开pdf文件，生成PdfDecoder对象
        try {
            pdfDecoder.openPdfArray(bytes);
            //获取第pageNum页的pdf
            BufferedImage img = pdfDecoder.getPageAsImage(pageNum);

            ImageIO.write(img, formatName, outputStream);
            return outputStream;
        } catch (PdfException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
