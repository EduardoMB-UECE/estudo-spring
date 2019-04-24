package com.estudo.spring.Locadora.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {

	@Id
	@NotNull(message = "Campo de rg nao pode estar vazio.")
	private Integer rg;
	
	@Size(min = 2, max = 50, message = "Campo deve conter no minimo 2 caracteres e no maximo 50.")
	private String nome;

	@ManyToOne
	private Carro carro;

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}
