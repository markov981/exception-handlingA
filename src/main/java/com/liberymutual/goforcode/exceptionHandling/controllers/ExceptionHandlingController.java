package com.liberymutual.goforcode.exceptionHandling.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class ExceptionHandlingController {

    @GetMapping("/")
    public String showForm() { return "exceptionHandling/default"; }

    
    @PostMapping("/handleString")
    public ModelAndView handleString(String probablySomeText, Model model) {
        ModelAndView mv = new ModelAndView("exceptionHandling/default");      
        try 
	        { mv.addObject("stringResult", probablySomeText.substring(4)); }
        
        catch (java.lang.StringIndexOutOfBoundsException se) 
	        { model.addAttribute("stringResult", "String should be 4 characters. Java: " + se.getMessage());}
        
        return mv;
    }

    @PostMapping("/handleUrl")
    public ModelAndView handleUrl(String probablyAUrl, Model model) throws MalformedURLException {
        ModelAndView mv = new ModelAndView("exceptionHandling/default");
        try {mv.addObject("urlResult", new URL(probablyAUrl));}       
        catch (java.net.MalformedURLException se) 
        { 
        	model.addAttribute("urlResult", "");
        	model.addAttribute("urlFailure", "Your input is not a valid URL. You a communist or something?");}
           
        return mv;
    }
    

    @PostMapping("/handleInteger")
    public ModelAndView handleInteger(String probablyAnInteger, Model model) {
        ModelAndView mv = new ModelAndView("exceptionHandling/default");      
        try{ mv.addObject("integerResult", Integer.parseInt(probablyAnInteger)); }
        catch (java.lang.NumberFormatException se) 
        { model.addAttribute("integerResult", "Your input is not an integer."); }
        return mv;
    }

    @PostMapping("/handleDecimal")
    public ModelAndView handleDecimal(String probablyADecimal, Model model) {
        ModelAndView mv = new ModelAndView("exceptionHandling/default");
        
        try { mv.addObject("decimalResult", Double.parseDouble(probablyADecimal)); }
        catch (java.lang.NumberFormatException se) 
        	{ model.addAttribute("decimalResult", "Your input is not a double."); }
        return mv;
    }

    @PostMapping("/handleDate")
    public ModelAndView handleDate(String probablyADate, Model model) {
        ModelAndView mv = new ModelAndView("exceptionHandling/default");
        try {mv.addObject("dateResult", LocalDate.parse(probablyADate));}
        catch (java.time.format.DateTimeParseException se) 
    	{ model.addAttribute("dateResult", "Your input is not a date. This is almost fine..."); }
        return mv;
    }
}