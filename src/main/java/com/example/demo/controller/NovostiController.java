package com.example.demo.controller;

import java.util.List;

import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.demo.models.Novost;
import com.example.demo.service.NovostService;

@Controller
public class NovostiController {
	private final NovostService novostService;

    public NovostiController(NovostService novostService) {
        this.novostService = novostService;
    }

    @GetMapping("/novosti")
    public String showNovostiPage(Model model) {
        List<Novost> novosti = novostService.getAllNovosti();
        model.addAttribute("novosti", novosti);
        return "novosti";
    }
    
    @GetMapping("/novosti/detalji/{id}")
    public String showNovostDetalji(@PathVariable("id") Long id, Model model) {
        Novost novost = novostService.getNovost(id);
        if (novost == null) {
            return "redirect:/novosti"; // Redirect to the list page if the novost is not found
        }
        model.addAttribute("novost", novost);
        return "novosti_detalji"; 
    }

    
    
    @RequestMapping(value = "/novosti/nova", method = RequestMethod.GET)
	public String getNovaNovost(Model model) {
		
		Novost novost = new Novost();
		model.addAttribute("novost", novost);
		
		
		return "nova_novost";
	}
    
    
    
    @RequestMapping(value = "/novosti/nova", method = RequestMethod.POST)
    public String postNovaNovost(@ModelAttribute("novost") Novost novost, Model model) {
        if (novost.getNaslov().isEmpty() && novost.getOpis().isEmpty()) {
            model.addAttribute("naslovError", "Ispunite ovo polje");
            model.addAttribute("opisError", "Ispunite ovo polje");
            return "nova_novost";
        }


        novostService.createNovost(novost);
        return "redirect:/novosti";
    }
	
    
    
   
	
	@RequestMapping("/novosti/uredi/{id}")
	public ModelAndView urediNovost(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("uredi_novost");
		Novost novost = novostService.getNovost(id);
		mav.addObject("novost", novost);
		return mav;
	}

	@RequestMapping(value = "/novosti/uredi", method = RequestMethod.POST)
	public String snimiNovost(@ModelAttribute("novost") Novost novost) {
		novostService.updateNovost(novost);
		return "redirect:/novosti";
	}
	
	@RequestMapping("/novosti/brisi/{id}")
	public String brisanjeNovost(@PathVariable(name = "id") long id) {
		novostService.deleteNovost(id);
		return "redirect:/novosti";
	}
	
	
	//NE RADI
	@GetMapping("/novosti/generate-pdf")
	public ResponseEntity<byte[]> generateNovostiPDF() {
	    List<Novost> novosti = novostService.getAllNovosti();

	    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
	         PDDocument document = new PDDocument()) {

	        PDPage page = new PDPage(PDRectangle.A4);
	        document.addPage(page);

	        PDPageContentStream contentStream = new PDPageContentStream(document, page);

	        // Set the font and font size for the text
	        PDFont font = PDType0Font.load(document, getClass().getResourceAsStream("/static/fonts/MICROSS.TTF"));
	        float fontSize = 12;

	        // Set the initial position for the text
	        float startX = 50;
	        float startY = page.getMediaBox().getHeight() - 50;

	        // Iterate over the novosti and add the naslov, opis, and date to the PDF document
	        for (Novost novost : novosti) {
	            contentStream.setFont(font, fontSize);
	            
	            contentStream.beginText();
	            contentStream.newLineAtOffset(startX, startY);
	            contentStream.showText("Naslov: " + novost.getNaslov());
	            contentStream.newLineAtOffset(200, 0);
	            contentStream.showText("Opis: " + novost.getOpis());
	            contentStream.newLineAtOffset(200, 0);
	            contentStream.showText("Datum: " + novost.getDatum().toString());
	            contentStream.newLineAtOffset(-400, -12);
	          
	           
	          
	            contentStream.endText();

	           
	        }

	        contentStream.close();

	        // Save the PDF document to a byte array
	        document.save(baos);
	        document.close();

	        // Set the response headers to indicate that a PDF file is being returned
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", "novosti.pdf");

	        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
	    } catch (IOException e) {
	        // Handle any exceptions that occur during PDF generation
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	






}
