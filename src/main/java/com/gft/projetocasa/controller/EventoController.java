package com.gft.projetocasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.projetocasa.models.Evento;
import com.gft.projetocasa.models.Local;
import com.gft.projetocasa.repository.LocalRepository;
import com.gft.projetocasa.services.EventoServices;
import com.gft.projetocasa.services.LocalServices;

@Controller
@RequestMapping("/Evento")
public class EventoController {

	@Autowired
	private EventoServices services;
	
	@Autowired
	private LocalServices localservices;
	
	@Autowired
	private LocalRepository localrepository;
		
	
	// LISTAR
	@RequestMapping("/Index")
	public ModelAndView index() {
		List<Evento> evento = services.listar();
		List<Local> local = localservices.listar();
		ModelAndView mv = new ModelAndView();
		mv.addObject("local", local);
		mv.addObject("evento", evento);
		return mv;
	}

	// CRIAR
	@RequestMapping("/Criar")
	public ModelAndView criar() {
		List<Local> local = localservices.listar();
		ModelAndView mv = new ModelAndView("Evento/Criar");
		mv.addObject("local", local);
		mv.addObject(new Evento());
		return mv;
	}

	// SALVAR
	@RequestMapping(method = RequestMethod.POST)
	public String save( @Validated Evento evento, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "Evento/Criar";
		}
		try {
			services.salvar(evento);
			attributes.addFlashAttribute("menssage", "Evento salvo com sucesso!");
			return "redirect:/Evento/Index";
		} catch (IllegalArgumentException e) {
				return "Evento/Criar";
		}

	}

	// EDITAR
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Evento evento) {
		List<Local> local = localservices.listar();
		ModelAndView mv = new ModelAndView("/Evento/Criar");
		mv.addObject("local", local);
		mv.addObject(evento);
		return mv;
	}

	// DELETAR
	@RequestMapping("/Excluir/{id}")
	public String delete(@PathVariable("id") Long id, Evento evento) {
		evento.setId(id);
		services.deletar(evento);
		return "redirect:/Evento/Index";
	}
	
    @ModelAttribute("todosLocais")
    public List<Local> todosLocais(){
    	return localrepository.findAll();
    }

}
