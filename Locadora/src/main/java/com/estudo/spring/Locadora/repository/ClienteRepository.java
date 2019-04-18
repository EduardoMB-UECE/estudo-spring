package com.estudo.spring.Locadora.repository;

import org.springframework.data.repository.CrudRepository;

import com.estudo.spring.Locadora.model.Carro;
import com.estudo.spring.Locadora.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	Iterable<Cliente> findByCarro(Carro carro);
	
}
