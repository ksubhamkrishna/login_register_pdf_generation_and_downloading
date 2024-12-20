package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    @Autowired
	private UserService userService;

    public void generatePdf(String filePath) {
        try {
            // Fetch data from the database
            List<UserDto> users = userService.fetchAllUsers();

            // Create PDF writer
            PdfWriter writer = new PdfWriter(new FileOutputStream("C:/Users/subham.krishna/Desktop/user_details.pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add a title
            document.add(new Paragraph("User Details").setBold().setFontSize(18));

            // Create a table
            Table table = new Table(new float[]{1, 2, 2});
            table.addCell("ID");
            table.addCell("Username");
            table.addCell("Email");

            // Populate the table with data
            for (UserDto user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getUsername());
                table.addCell(user.getEmail());
            }

            // Add table to the document
            document.add(table);

            // Close document
            document.close();

            System.out.println("PDF created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
