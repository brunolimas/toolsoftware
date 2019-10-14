package br.com.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VENDAS")
public class Venda {

	@Id
	@Column(name = "ID_VENDA")
	private String idVenda;
	
	@Column(name = "ID_ITEM")
	private String idItem;
	
	@Column(name = "QTD_ITEM")
	private String qtdItem;
	
	@Column(name = "VALOR_ITEM")
	private String valorItem;
	
	@Column(name = "NOME_VENDEDOR")
	private String nomeVendedor;
	
}
