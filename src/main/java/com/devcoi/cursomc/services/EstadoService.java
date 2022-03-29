package com.devcoi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcoi.cursomc.domain.Estado;
import com.devcoi.cursomc.repositories.EstadoRepository;
import com.devcoi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado find(Integer id) {
		Optional<Estado> optional = estadoRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException(
				"Estado não encontrado! Id:" + id + " , Tipo: " + Estado.class.getName()));
	}

	public List<Estado> findAll() {
		return estadoRepository.findAllByOrderByNome();
	}

}
