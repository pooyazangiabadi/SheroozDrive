package com.sheroozdrive.SheroozDrive.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ThumbnailGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ThumbnailGenerator.class);

    public static void generateThumbnail(File inputFile, File outputFile) throws IOException {
        String fileName = inputFile.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
            generateThumbnailForImage(inputFile, outputFile);
        } else if (fileExtension.equals("mp4") || fileExtension.equals("mov") || fileExtension.equals("avi")) {
            //generateThumbnailForVideo(inputFile, outputFile);
        } else if (fileExtension.equals("pdf")) {
            generateThumbnailForPDF(inputFile, outputFile);
        } else {
            throw new RuntimeException("File type not supported");
        }
    }

    private static void generateThumbnailForImage(File inputFile, File outputFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        BufferedImage thumbnail = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, 200, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbnail, "jpg", outputFile);
    }


    private static void generateThumbnailForPDF(File inputFile, File outputFile) throws IOException {
        PDDocument document = PDDocument.load(inputFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);
        BufferedImage thumbnail = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, 200, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbnail, "jpg", outputFile);
        document.close();
    }
}