package com.devcoi.cursomc.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devcoi.cursomc.domain.Categoria;
import com.devcoi.cursomc.dto.CategoriaDTO;
import com.devcoi.cursomc.repositories.CategoriaRepository;
import com.devcoi.cursomc.security.UserSS;
import com.devcoi.cursomc.services.exceptions.AuthorizationException;
import com.devcoi.cursomc.services.exceptions.DataIntegrityException;
import com.devcoi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.categoria}")
	private String prefix;
	
	@Value("${img.categoria.size}")
	private Integer size;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! Id: " + id + " , Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public void delete(Integer id) {
		find(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	public URI uploadPicture(MultipartFile multipartFile, Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + id + ".jpg";

		InputStream input = imageService.getInputStream(jpgImage, "jpg");

		return s3Service.uploadFile(input, fileName, "image");
	}

}
