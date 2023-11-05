package com.cafemanagement.freshcafe.util;

import com.cafemanagement.freshcafe.model.BillProduct;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class PDFReceiptGenarator {

    public static String save(Set<BillProduct> products, String filename){
        File file = new File(filename);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            DecimalFormat format = new DecimalFormat("0.##");
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(200, 700);
            contentStream.showText("Fresh Cafe");
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(10, -15);
            contentStream.showText("receipt");
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-40, -15);
            contentStream.showText(dtf.format(now));
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-40, -40);
            contentStream.showText("---------------------------------------------------");

            double subtotal = 0;

            for (BillProduct p : products) {
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(p.getAmount() + " x " + p.getName());
                contentStream.newLineAtOffset(160, 0);
                contentStream.showText(format.format(p.getPrice()*p.getAmount()) + " Baht");
                contentStream.newLineAtOffset(-160, 0);
                subtotal += p.getPrice()*p.getAmount();
            }

            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("---------------------------------------------------");
            contentStream.newLineAtOffset(90, -15);
            contentStream.showText("Subtotal     " + format.format(subtotal) + " Baht");
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Vat 7%      " + format.format(subtotal*0.07) + " Baht");
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Total        " + format.format(subtotal + subtotal*0.07) + " Baht");

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(-20, -40);
            contentStream.showText("Thank you");

            contentStream.endText();
            contentStream.close();

            document.save(filename);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
