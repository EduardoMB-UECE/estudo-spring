package com.estudo.spring.Locadora.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Carro implements Serializable {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 685916545858927193L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "O campo do modelo do carro não pode ser vazio.")
	private String modelo;
	@DecimalMin(value = "1910", message = "O valor do ano deve ser superior a 1910")
	@NotNull(message = "Campo ano não pode estar vazio")
	private Integer ano;
	@Positive(message = "O campo do preço do carro deve ser positivo.")
	@NotNull(message = "Campo preço não pode estar vazio")
	private Integer preco;

	@OneToMany
	private List<Cliente> clientes;

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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
