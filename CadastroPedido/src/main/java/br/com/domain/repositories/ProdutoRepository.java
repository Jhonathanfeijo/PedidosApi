package br.com.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.domain.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
