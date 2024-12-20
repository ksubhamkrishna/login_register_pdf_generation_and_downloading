package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PdfService;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    // Endpoint to generate the PDF
    @GetMapping("/generate")
    public ResponseEntity<String> generatePdf() {
        String filePath = "C:/Users/subham.krishna/Desktop/user_details.pdf"; // Ensure file ends with .pdf
        pdfService.generatePdf(filePath);
        return ResponseEntity.ok("PDF generated successfully at " + filePath);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadPdf() throws FileNotFoundException {
        String filePath = "C:/Users/subham.krishna/Desktop/user_details.pdf"; // Path to the PDF file
        File pdfFile = new File(filePath);

        // Log file information for debugging
        System.out.println("Looking for file at: " + filePath);
        System.out.println("File absolute path: " + pdfFile.getAbsolutePath());
        System.out.println("File exists: " + pdfFile.exists());

        if (!pdfFile.exists()) {
            throw new FileNotFoundException("The file at " + filePath + " was not found.");
        }

        // Prepare the resource and headers
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        // Build the response
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfFile.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


   
}
