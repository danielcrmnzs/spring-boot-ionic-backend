package com.devcoi.cursomc.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devcoi.cursomc.domain.Categoria;
import com.devcoi.cursomc.domain.Produto;
import com.devcoi.cursomc.repositories.CategoriaRepository;
import com.devcoi.cursomc.repositories.ProdutoRepository;
import com.devcoi.cursomc.security.UserSS;
import com.devcoi.cursomc.services.exceptions.AuthorizationException;
import com.devcoi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.produto}")
	private String prefix;

	@Value("${img.produto_small.size}")
	private Integer size_small;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " , Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
//		return repo.search(nome, categorias, pageRequest);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

	public URI uploadPicture(MultipartFile multipartFile, Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		BufferedImage jpgImageSmall = imageService.getJpgImageFromFile(multipartFile);
		jpgImageSmall = imageService.cropSquare(jpgImageSmall);
		jpgImageSmall = imageService.resize(jpgImageSmall, size_small);

		String fileName = prefix + id + ".jpg";
		String fileNameSmall = prefix + id + "-small.jpg";
		
		InputStream input = imageService.getInputStream(jpgImage, "jpg");
		InputStream inputSmall = imageService.getInputStream(jpgImageSmall, "jpg");

		
		s3Service.uploadFile(inputSmall, fileNameSmall, "image");
		return s3Service.uploadFile(input, fileName, "image");
	}

}
