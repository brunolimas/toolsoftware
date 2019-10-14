package br.com.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.core.entity.Cliente;
import br.com.core.interfaces.IClienteService;
import br.com.core.repositories.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente importar(Cliente cliente) {
		return repository.save(cliente);
	}

	public Long quantidadeClientes() {
		return repository.count();
	}
}
