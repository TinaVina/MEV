package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.export.*;
import com.example.demo.models.Clan;

import com.example.demo.service.ClanService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class PopisClanovaController {
	private final ClanService clanService;

    public PopisClanovaController(ClanService clanService) {
        this.clanService = clanService;
    }

    @GetMapping("/clanovi")
    public String showClanoviPage(Model model) {
        List<Clan> clanovi = clanService.getAllClanovi();
        model.addAttribute("clanovi", clanovi);
        return "lista";
    }
    
    
    @RequestMapping(value = "/clanovi/novi", method = RequestMethod.GET)
	public String getNoviClan(Model model) {
		
		Clan clan = new Clan();
		model.addAttribute("clan", clan);
		
		return "novi_Clan";
	}
    
    
	
	@RequestMapping(value = "/clanovi/novi", method = RequestMethod.POST)
		public String postNoviClan(@ModelAttribute("Clan") Clan Clan) {
		
		
		clanService.createClan(Clan);
			return "redirect:/clanovi";
	}
	
	
	
	
	@RequestMapping("/clanovi/uredi/{id}")
	public ModelAndView urediClan(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("uredi_Clan");
		Clan clan = clanService.getClan(id);
		mav.addObject("clan", clan);
		return mav;
	}

	
	@RequestMapping(value = "/clanovi/uredi", method = RequestMethod.POST)
	public String snimiClan(@ModelAttribute("Clan") Clan Clan) {
		clanService.updateClan(Clan);
		return "redirect:/clanovi";
	}
	
	
	
	
	@RequestMapping("/clanovi/brisi/{id}")
	public String brisanjeClan(@PathVariable(name = "id") int id) {
	clanService.deleteClan(id);
	return "redirect:/clanovi";
	}
	
	
	@GetMapping("/export-to-pdf")
	public void generatePdfFile(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
	    response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=clan_" + currentDateTime + ".pdf";
	    response.setHeader(headerKey, headerValue);

	    List<Clan> listaClanova = clanService.getAllClanovi();
	    PdfGenerator pdfGenerator = new PdfGenerator();
	    pdfGenerator.generate(listaClanova, response);
	}

	
	
}
