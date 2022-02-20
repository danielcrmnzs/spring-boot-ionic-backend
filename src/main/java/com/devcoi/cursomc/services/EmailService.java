package com.devcoi.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.devcoi.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
