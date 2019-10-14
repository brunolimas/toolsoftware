package br.com.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.core.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Query( value = "SELECT V.ID_VENDA FROM VENDAS AS V WHERE V.VALOR_ITEM = (SELECT MAX(VALOR_ITEM) FROM VENDAS)", nativeQuery = true)
	String recuperarVendaValorMaior();
	
	@Query( value = "SELECT V.NOME_VENDEDOR FROM VENDAS V WHERE V.QTD_ITEM = (SELECT MIN(QTD_ITEM) FROM VENDAS)", nativeQuery = true)
	String recuperarVendedorMenorNumeroVenda();
}