package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.example.demo.models.PdfFile;
import com.example.demo.service.PdfFileService;

import jakarta.annotation.Resource;

import org.springframework.core.io.FileSystemResource;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class DokumentiController {

	 private static final String UPLOAD_FOLDER = "C:\\Users\\Tina\\Desktop\\Tina\\PdfFiles";//pravi path
	
	  public DokumentiController(PdfFileService pdfFileService) {
	        this.pdfFileService = pdfFileService;
	    }
	 
    @Autowired
    private PdfFileService pdfFileService;

    @GetMapping("/dokumenti")
    public String showDokumentiPage(Model model) {
        List<PdfFile> pdfFiles = pdfFileService.getAllPdfFiles();
        model.addAttribute("pdfFiles", pdfFiles);
        return "dokumenti";
    }

    @PostMapping("/dokumenti/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/dokumenti";
        }

        try {
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            byte[] fileData = file.getBytes();
            PdfFile pdfFile = new PdfFile(originalFileName, fileData);
            pdfFileService.createPdfFile(pdfFile);
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully: " + originalFileName);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload the file.");
        }

        return "redirect:/dokumenti";
    }
    
    @GetMapping("/dokumenti/delete/{id}")
    public String deletePdfFile(@PathVariable("id") Long id) {
        pdfFileService.deletePdfFile(id);
        return "redirect:/dokumenti";
    }

        
  
    @GetMapping("/dokumenti/download/{filename}")
    public ResponseEntity<FileSystemResource> downloadPdfFile(@PathVariable("filename") String filename) {
        String filePath = UPLOAD_FOLDER + "\\" + filename;

        try {
        	FileSystemResource resource = new FileSystemResource(filePath);

            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", filename);

                return ResponseEntity.ok().headers(headers).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    
    

    
}

