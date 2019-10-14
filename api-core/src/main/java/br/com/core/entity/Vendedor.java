package br.com.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VENDEDORES")
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_VENDEDOR")
	private int id;

	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "SALARIO")
	private String salario;

}
