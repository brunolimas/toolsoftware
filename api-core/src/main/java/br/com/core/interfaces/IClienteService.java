package br.com.core.interfaces;

import br.com.core.entity.Cliente;

public interface IClienteService {

	Cliente importar(Cliente cliente);
	
	Long quantidadeClientes();

}
