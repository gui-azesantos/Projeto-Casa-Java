package com.gft.projetocasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.projetocasa.models.Local;
import com.gft.projetocasa.services.LocalServices;

@Controller
@RequestMapping("/Local")
public class LocalController {

	@Autowired
	private LocalServices services;

	// LISTAR
	@RequestMapping("/Index")
	public ModelAndView index() {
		List<Local> local = services.listar();
		ModelAndView mv = new ModelAndView();
		mv.addObject("local", local);
		return mv;
	}

	// CRIAR
	@RequestMapping("/Criar")
	public ModelAndView criar() {
		ModelAndView mv = new ModelAndView("Local/Criar");
		mv.addObject(new Local());
		return mv;
	}

	// SALVAR
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Local local, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "Local/Criar";
		}
		try {
			services.salvar(local);
			attributes.addFlashAttribute("menssage", "Local salvo com sucesso!");
			return "redirect:/Local/Index";
		} catch (IllegalArgumentException e) {
			return "Local/Criar";
		}
		
	}

	// EDITAR
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Local local) {
		ModelAndView mv = new ModelAndView("/Local/Criar");
		mv.addObject(local);
		return mv;
	}

	// DELETAR
	@RequestMapping("/Excluir/{id}")
	public String delete(@PathVariable("id") Long id, Local local) {
		local.setId(id);
		services.deletar(local);
		return "redirect:/Local/Index";
	}
}
