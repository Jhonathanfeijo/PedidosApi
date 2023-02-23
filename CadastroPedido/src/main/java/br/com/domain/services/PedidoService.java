package br.com.domain.services;

import br.com.domain.DTO.InformacaoPedidoDTO;
import br.com.domain.DTO.PedidoDTO;  
import br.com.domain.entities.Pedido;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDTO);
	
	public Pedido buscarPedido(Integer id);

}
