package br.com.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.core.entity.Vendedor;

@Repository
public interface VendedoresRepository extends JpaRepository<Vendedor, Integer> {

}
