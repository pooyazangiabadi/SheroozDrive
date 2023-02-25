package com.sheroozdrive.SheroozDrive.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ThumbnailGenerator {

    @Value("${setting.upload.thumbnail.size}")
    private int uploadThumbSize;

    private static final Logger logger = LoggerFactory.getLogger(ThumbnailGenerator.class);

    public Binary generateThumbnail(File inputFile) throws IOException {
        String fileName = inputFile.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
            return generateThumbnailForImage(inputFile);
        } else if (fileExtension.equals("mp4") || fileExtension.equals("mov") || fileExtension.equals("avi")) {
            //generateThumbnailForVideo(inputFile, outputFile);
        } else if (fileExtension.equals("pdf")) {
            return generateThumbnailForPDF(inputFile);
        }
        return null;
    }

    private Binary generateThumbnailForImage(File inputFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        return getBinary(bufferedImage);
    }


    private Binary generateThumbnailForPDF(File inputFile) throws IOException {
        PDDocument document = PDDocument.load(inputFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);
        return getBinary(bufferedImage);
    }

    private Binary getBinary(BufferedImage bufferedImage) throws IOException {
        BufferedImage thumbnail = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, uploadThumbSize, Scalr.OP_ANTIALIAS);
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "jpg", thumbOutput);
        byte[] bytes = thumbOutput.toByteArray();
        return new Binary(BsonBinarySubType.BINARY, bytes);
    }
}