package br.com.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.domain.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
