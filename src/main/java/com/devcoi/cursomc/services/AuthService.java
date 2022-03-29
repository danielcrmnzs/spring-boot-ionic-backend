package com.devcoi.cursomc.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devcoi.cursomc.domain.Cliente;
import com.devcoi.cursomc.repositories.ClienteRepository;
import com.devcoi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		
		Optional<Cliente> obj = clienteRepository.findByEmail(email);
		Cliente cliente = obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente com email: " + email + " não encontrado"));

		String newPassword = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {
			// gerar um digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			// gerar uma letra maiúscula
			return (char) (random.nextInt(26) + 65);
		} else {
			// gerar uma letra minúscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}
