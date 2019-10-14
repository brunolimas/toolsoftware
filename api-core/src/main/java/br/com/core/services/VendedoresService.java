package br.com.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.core.entity.Vendedor;
import br.com.core.interfaces.IVendedoresService;
import br.com.core.repositories.VendedoresRepository;

@Service
public class VendedoresService implements IVendedoresService {

	@Autowired
	private VendedoresRepository repository;
	
	@Override
	public Vendedor importar(Vendedor vendedor) {
		return repository.save(vendedor);
	}

	public Long quantidadeVendedores() {
		return repository.count();
	}
}
