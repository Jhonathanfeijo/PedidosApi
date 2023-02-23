package br.com.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.domain.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	
	@Query("select p from Pedido p left join fetch p.itens where p.id =:id")
	Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);

}
