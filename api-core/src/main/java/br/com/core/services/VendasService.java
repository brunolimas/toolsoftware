package br.com.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.core.entity.Venda;
import br.com.core.interfaces.IVendasService;
import br.com.core.repositories.VendaRepository;

@Service
public class VendasService implements IVendasService {

	
	@Autowired
	private VendaRepository repository;
	
	@Override
	public Venda importar(Venda venda) {
		return repository.save(venda);
	}

	public String recuperarVendaValorMaior() {
		return repository.recuperarVendaValorMaior();
	}
	
	public String recuperarVendedorMenorNumeroVenda() {
		return repository.recuperarVendedorMenorNumeroVenda();
	}
}
