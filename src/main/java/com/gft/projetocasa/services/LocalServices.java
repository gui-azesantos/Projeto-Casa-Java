package com.gft.projetocasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projetocasa.models.Local;
import com.gft.projetocasa.repository.LocalRepository;

@Service
public class LocalServices {

	@Autowired
	private LocalRepository repository;

	// LISTAR
	public List<Local> listar() {
		return repository.findAll();
	}

	// SALVAR
	public void salvar(Local local) {
		repository.save(local);
		
	}

	// DELETAR
	public void deletar(Local local) {
		this.repository.delete(local);
	}
}
