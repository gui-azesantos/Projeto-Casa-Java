package com.gft.projetocasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.projetocasa.models.Evento;
import com.gft.projetocasa.models.Local;
import com.gft.projetocasa.services.EventoServices;
import com.gft.projetocasa.services.LocalServices;

@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private EventoServices services;
	
	@Autowired
	private LocalServices localservices;
	

	@RequestMapping("/Shows")
	public ModelAndView shows() {
		List<Evento> evento = services.listar();
		List<Local> local = localservices.listar();
		ModelAndView mv = new ModelAndView();
		mv.addObject("local", local);
		mv.addObject("evento", evento);
		return mv;
	}
	
	@RequestMapping("/Home")
	public ModelAndView home() {
		List<Evento> evento = services.listar();
		List<Local> local = localservices.listar();
		ModelAndView mv = new ModelAndView();
		mv.addObject("local", local);
		mv.addObject("evento", evento);
		return mv;
	}
	
}
