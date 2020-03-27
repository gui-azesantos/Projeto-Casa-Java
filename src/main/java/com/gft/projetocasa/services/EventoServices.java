package com.gft.projetocasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projetocasa.models.Evento;
import com.gft.projetocasa.repository.EventoRepository;

@Service
public class EventoServices {
	
	@Autowired
	private EventoRepository repository;

	// LISTAR
	public List<Evento> listar() {
		return repository.findAll();
	}

	// SALVAR
	public void salvar(Evento evento) {
		repository.save(evento);
		
	}

	// DELETAR
	public void deletar(Evento evento) {
		this.repository.delete(evento);
	}
}

