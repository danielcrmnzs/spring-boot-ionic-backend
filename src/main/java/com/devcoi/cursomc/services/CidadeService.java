package com.devcoi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcoi.cursomc.domain.Cidade;
import com.devcoi.cursomc.repositories.CidadeRepository;
import com.devcoi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

//	@Autowired
//	private EstadoRepository estadoRepository;

	public Cidade find(Integer id) {
		Optional<Cidade> optional = cidadeRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException(
				"Cidade não encontrada! Id:" + id + " , Tipo: " + Cidade.class.getName()));
	}

	public List<Cidade> findByEstado(Integer idEstado) {
//		Estado estado = estadoRepository.getById(idEstado);
//		return cidadeRepository.findByEstado(estado);
		return cidadeRepository.findCidades(idEstado);
		
	}

}
