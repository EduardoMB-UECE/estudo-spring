package com.estudo.spring.Locadora.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro implements Serializable {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 685916545858927193L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String modelo;
	private String ano;
	private String preco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
