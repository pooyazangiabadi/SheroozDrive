package com.sheroozdrive.SheroozDrive.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ThumbnailGenerator {

    @Value("${setting.upload.thumbnail.dir}")
    private String uploadThumbDir;

    @Value("${setting.upload.thumbnail.size}")
    private int uploadThumbSize;

    private static final Logger logger = LoggerFactory.getLogger(ThumbnailGenerator.class);

    public void generateThumbnail(File inputFile) throws IOException {
        String fileName = inputFile.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
            generateThumbnailForImage(inputFile);
        } else if (fileExtension.equals("mp4") || fileExtension.equals("mov") || fileExtension.equals("avi")) {
            //generateThumbnailForVideo(inputFile, outputFile);
        } else if (fileExtension.equals("pdf")) {
            generateThumbnailForPDF(inputFile);
        } else {
            //throw new RuntimeException("File type not supported");
        }
    }

    private void generateThumbnailForImage(File inputFile) throws IOException {
        File thumbOutput = new File(System.getProperty("user.dir") + uploadThumbDir + FilenameUtils.removeExtension(inputFile.getName()) + ".jpg");
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        BufferedImage thumbnail = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, uploadThumbSize, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbnail, "jpg", thumbOutput);
    }


    private void generateThumbnailForPDF(File inputFile) throws IOException {
        File thumbOutput = new File(System.getProperty("user.dir") + uploadThumbDir + FilenameUtils.removeExtension(inputFile.getName()) + ".jpg");
        PDDocument document = PDDocument.load(inputFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);
        BufferedImage thumbnail = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, uploadThumbSize, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbnail, "jpg", thumbOutput);
        document.close();
    }
}