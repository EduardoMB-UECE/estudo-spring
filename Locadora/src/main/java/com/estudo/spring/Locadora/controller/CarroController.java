package com.estudo.spring.Locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.estudo.spring.Locadora.model.Carro;
import com.estudo.spring.Locadora.repository.CarroRepository;

@Controller
public class CarroController {
	
	@Autowired
	private CarroRepository carroRepository;
	
	@RequestMapping(value="cadastroCarro", method=RequestMethod.GET)
	public String cadastrar() {
		return "carro/cadastro";
	}
	
	@RequestMapping(value="cadastroCarro", method=RequestMethod.POST)
	public String cadastrar(Carro carro) {
		
		carroRepository.save(carro);
		
		return "redirect:cadastroCarro";
	}
}
