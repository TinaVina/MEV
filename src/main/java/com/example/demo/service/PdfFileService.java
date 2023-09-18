package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.PdfFileRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Novost;
import com.example.demo.models.PdfFile;

@Service
public class PdfFileService {
	
	
    public void deletePdfFile(long pdfId) {
        Optional<PdfFile> pdfDb = pdfFileRepository.findById(pdfId);
        if (pdfDb.isPresent()) {
            pdfFileRepository.delete(pdfDb.get());
        } else {
            throw new ResourceNotFoundException("Zapis nije pronađen.");
        }
    }




    @Autowired
    private PdfFileRepository pdfFileRepository;

    public List<PdfFile> getAllPdfFiles() {
        return pdfFileRepository.findAll();
    }

    public void createPdfFile(PdfFile pdfFile) {
        pdfFileRepository.save(pdfFile);
    }

 
}
