package com.estudo.spring.Locadora.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudo.spring.Locadora.model.Carro;
import com.estudo.spring.Locadora.model.Cliente;
import com.estudo.spring.Locadora.repository.CarroRepository;
import com.estudo.spring.Locadora.repository.ClienteRepository;

@Controller
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value = "cadastroCarro", method = RequestMethod.GET)
	public String cadastrar() {
		return "carro/cadastro";
	}

	@RequestMapping(value = "cadastroCarro", method = RequestMethod.POST)
	public String cadastrar(Carro carro) {

		carroRepository.save(carro);

		return "redirect:cadastroCarro";
	}

	@RequestMapping("/")
	public ModelAndView obterTodosCarros() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("carros", carroRepository.findAll());
		return mav;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesCarro(@PathVariable("codigo") int codigo) {
		Carro carro = carroRepository.findById(codigo);
		Iterable<Cliente> clientes = clienteRepository.findByCarro(carro);
		ModelAndView mv = new ModelAndView("detalhesCarro");
		mv.addObject("carro", carro);
		mv.addObject("clientes", clientes);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String adicionarCliente(@PathVariable("codigo") int codigo, @Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		List<String> mensagens = new ArrayList<>();
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				mensagens.add(error.getDefaultMessage());
			}
		} else {
			Carro carro = carroRepository.findById(codigo);
			cliente.setCarro(carro);
			clienteRepository.save(cliente);
			mensagens.add("Cliente adicionado com sucesso!");
		}
		attributes.addFlashAttribute("mensagens", mensagens);
		return "redirect:/{codigo}";
	}

}
