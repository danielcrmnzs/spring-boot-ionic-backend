package com.devcoi.cursomc.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devcoi.cursomc.domain.Categoria;
import com.devcoi.cursomc.domain.Cidade;
import com.devcoi.cursomc.domain.Cliente;
import com.devcoi.cursomc.domain.Endereco;
import com.devcoi.cursomc.domain.Estado;
import com.devcoi.cursomc.domain.ItemPedido;
import com.devcoi.cursomc.domain.Pagamento;
import com.devcoi.cursomc.domain.PagamentoComBoleto;
import com.devcoi.cursomc.domain.PagamentoComCartao;
import com.devcoi.cursomc.domain.Pedido;
import com.devcoi.cursomc.domain.Produto;
import com.devcoi.cursomc.domain.enums.EstadoPagamento;
import com.devcoi.cursomc.domain.enums.Perfil;
import com.devcoi.cursomc.domain.enums.TipoCliente;
import com.devcoi.cursomc.repositories.CategoriaRepository;
import com.devcoi.cursomc.repositories.CidadeRepository;
import com.devcoi.cursomc.repositories.ClienteRepository;
import com.devcoi.cursomc.repositories.EnderecoRepository;
import com.devcoi.cursomc.repositories.EstadoRepository;
import com.devcoi.cursomc.repositories.ItemPedidoRepository;
import com.devcoi.cursomc.repositories.PagamentoRepository;
import com.devcoi.cursomc.repositories.PedidoRepository;
import com.devcoi.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
		Produto p4 = new Produto(null, "Mesa de escritório", new BigDecimal(300.00));
		Produto p5 = new Produto(null, "Toalha", new BigDecimal(50.00));
		Produto p6 = new Produto(null, "Colcha", new BigDecimal(200.00));
		Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800.00));
		Produto p9 = new Produto(null, "Abajour", new BigDecimal(100.00));
		Produto p10 = new Produto(null, "Pendente", new BigDecimal(180.00));
		Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90.00));

		Produto p12 = new Produto(null, "Produto 12", new BigDecimal(10.00));
		Produto p13 = new Produto(null, "Produto 13", new BigDecimal(10.00));
		Produto p14 = new Produto(null, "Produto 14", new BigDecimal(10.00));
		Produto p15 = new Produto(null, "Produto 15", new BigDecimal(10.00));
		Produto p16 = new Produto(null, "Produto 16", new BigDecimal(10.00));
		Produto p17 = new Produto(null, "Produto 17", new BigDecimal(10.00));
		Produto p18 = new Produto(null, "Produto 18", new BigDecimal(10.00));
		Produto p19 = new Produto(null, "Produto 19", new BigDecimal(10.00));
		Produto p20 = new Produto(null, "Produto 20", new BigDecimal(10.00));
		Produto p21 = new Produto(null, "Produto 21", new BigDecimal(10.00));
		Produto p22 = new Produto(null, "Produto 22", new BigDecimal(10.00));
		Produto p23 = new Produto(null, "Produto 23", new BigDecimal(10.00));
		Produto p24 = new Produto(null, "Produto 24", new BigDecimal(10.00));
		Produto p25 = new Produto(null, "Produto 25", new BigDecimal(10.00));
		Produto p26 = new Produto(null, "Produto 26", new BigDecimal(10.00));
		Produto p27 = new Produto(null, "Produto 27", new BigDecimal(10.00));
		Produto p28 = new Produto(null, "Produto 28", new BigDecimal(10.00));
		Produto p29 = new Produto(null, "Produto 29", new BigDecimal(10.00));
		Produto p30 = new Produto(null, "Produto 30", new BigDecimal(10.00));
		Produto p31 = new Produto(null, "Produto 31", new BigDecimal(10.00));
		Produto p32 = new Produto(null, "Produto 32", new BigDecimal(10.00));
		Produto p33 = new Produto(null, "Produto 33", new BigDecimal(10.00));
		Produto p34 = new Produto(null, "Produto 34", new BigDecimal(10.00));
		Produto p35 = new Produto(null, "Produto 35", new BigDecimal(10.00));
		Produto p36 = new Produto(null, "Produto 36", new BigDecimal(10.00));
		Produto p37 = new Produto(null, "Produto 37", new BigDecimal(10.00));
		Produto p38 = new Produto(null, "Produto 38", new BigDecimal(10.00));
		Produto p39 = new Produto(null, "Produto 39", new BigDecimal(10.00));
		Produto p40 = new Produto(null, "Produto 40", new BigDecimal(10.00));
		Produto p41 = new Produto(null, "Produto 41", new BigDecimal(10.00));
		Produto p42 = new Produto(null, "Produto 42", new BigDecimal(10.00));
		Produto p43 = new Produto(null, "Produto 43", new BigDecimal(10.00));
		Produto p44 = new Produto(null, "Produto 44", new BigDecimal(10.00));
		Produto p45 = new Produto(null, "Produto 45", new BigDecimal(10.00));
		Produto p46 = new Produto(null, "Produto 46", new BigDecimal(10.00));
		Produto p47 = new Produto(null, "Produto 47", new BigDecimal(10.00));
		Produto p48 = new Produto(null, "Produto 48", new BigDecimal(10.00));
		Produto p49 = new Produto(null, "Produto 49", new BigDecimal(10.00));
		Produto p50 = new Produto(null, "Produto 50", new BigDecimal(10.00));

		cat1.getProdutos()
				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));

		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26,
				p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
				p49, p50));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Daniel Coimbra", "coimbradcrm.dev@gmail.com", "33848965728",
				TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("978956412", "98657854"));

		Cliente cli2 = new Cliente(null, "Ana Costa", "coimbradcrm.dev@hotmail.com", "95633783000",
				TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("971236412", "98643254"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 201", "Jardim", "33654789", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "32685289", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "123", null, "Centro", "32685229", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, new BigDecimal(0.00), 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, new BigDecimal(0.00), 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, new BigDecimal(100.0), 1);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip1));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
