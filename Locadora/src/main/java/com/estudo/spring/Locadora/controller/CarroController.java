package com.estudo.spring.Locadora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarroController {
	
	@RequestMapping("cadastroCarro")
	public String cadastrar() {
		return "carro/cadastro";
	}
}
