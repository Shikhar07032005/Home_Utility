package Home_Utility_Tracker.service;

import Home_Utility_Tracker.model.UtilityBill;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateUtilityPDF(List<UtilityBill> bills, String userName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        doc.add(new Paragraph(userName).setBold().setFontSize(16));
        doc.add(new Paragraph("Utility Report").setBold().setFontSize(16));
        doc.add(new Paragraph("\n"));

        for (UtilityBill bill : bills) {
            doc.add(new Paragraph(
                    "Type: " + bill.getUtilityType().getName() + "\n" +
                            "Amount: ₹" + bill.getAmount() + "\n" +
                            "Bill Date: " + bill.getBillDate() + "\n" +
                            "Due Date: " + bill.getDueDate() + "\n" +
                            "Paid: " + (bill.isPaid() ? "Yes" : "No") + "\n" +
                            "---------------------------"
            ));
        }

        doc.close();
        return new ByteArrayInputStream(out.toByteArray());
    }


//    public ByteArrayInputStream generateUtilityPDF(List<UtilityBill> bills) {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfWriter writer = new PdfWriter(out);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document doc = new Document(pdf);
//
//        doc.add(new Paragraph("Utility Bill Report").setBold().setFontSize(18));
//
//        for (UtilityBill bill : bills) {
//            doc.add(new Paragraph(
//                    "Type: " + bill.getUtilityType().getName() + "\n" +
//                            "Amount: ₹" + bill.getAmount() + "\n" +
//                            "Bill Date: " + bill.getBillDate() + "\n" +
//                            "Due Date: " + bill.getDueDate() + "\n" +
//                            "Paid: " + (bill.isPaid() ? "Yes" : "No") + "\n" +
//                            "---------------------------"
//            ));
//        }
//
//        doc.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
}
