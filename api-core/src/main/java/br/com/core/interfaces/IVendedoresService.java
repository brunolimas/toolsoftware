package br.com.core.interfaces;

import br.com.core.entity.Vendedor;

public interface IVendedoresService {

	Vendedor importar(Vendedor vendedor);
	
	Long quantidadeVendedores();
}
