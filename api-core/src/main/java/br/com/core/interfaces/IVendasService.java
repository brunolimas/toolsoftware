package br.com.core.interfaces;

import br.com.core.entity.Venda;

public interface IVendasService {

	Venda importar(Venda venda);
	
	String recuperarVendaValorMaior();
	
	String recuperarVendedorMenorNumeroVenda();
	
}
