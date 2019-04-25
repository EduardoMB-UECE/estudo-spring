package com.estudo.spring.Locadora.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	private static final String MSG_INFO_CARRO_DELETADO_COM_SUCESSO = "Carro deletado com sucesso.";
	private static final String MSG_ERRO_NAO_E_POSSIVEL_DELETAR_O_CARRO_POSSUI_CLIENTES_VINCULADOS = "Nao e possivel deletar o carro, pois ele já possui clientes vinculados.";
	private static final String MSG_INFO_CLIENTE_DELETADO_COM_SUCESSO = "Cliente deletado com sucesso.";

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

	@RequestMapping("/deletarCarro")
	public String deletarCarro(Integer codigo, RedirectAttributes attributes) {
		Carro carro = carroRepository.findById(codigo);
		List<String> mensagens = new ArrayList<>();
		try {
			carroRepository.delete(carro);
			mensagens.add(MSG_INFO_CARRO_DELETADO_COM_SUCESSO);
		} catch (DataIntegrityViolationException e) {
			mensagens.add(MSG_ERRO_NAO_E_POSSIVEL_DELETAR_O_CARRO_POSSUI_CLIENTES_VINCULADOS);
		}
		attributes.addFlashAttribute("mensagens", mensagens);
		return "redirect:/";
	}

	@RequestMapping("/deletarCliente")
	public String deletarCliente(Integer rg, RedirectAttributes attributes) {
		Cliente cliente = clienteRepository.findByRg(rg);
		List<String> mensagens = new ArrayList<>();
		clienteRepository.delete(cliente);
		
		mensagens.add(MSG_INFO_CLIENTE_DELETADO_COM_SUCESSO);
		attributes.addFlashAttribute("mensagens", mensagens);
		return "redirect:/" + cliente.getCarro().getId();
	}

}
