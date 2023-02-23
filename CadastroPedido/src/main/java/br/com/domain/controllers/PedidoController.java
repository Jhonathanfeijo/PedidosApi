package br.com.domain.controllers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.DTO.InformacaoItemPedidoDTO;
import br.com.domain.DTO.InformacaoPedidoDTO;
import br.com.domain.DTO.PedidoDTO;
import br.com.domain.entities.ItemPedido;
import br.com.domain.entities.Pedido;
import br.com.domain.services.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidos;
	
	// salva pedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido salvarPedido(@RequestBody @Valid PedidoDTO pedidoDTO) {
		Pedido pedido = pedidos.salvar(pedidoDTO);
		return pedido;
	}

	// busca pedido pelo id
	@GetMapping("/{id}")
	public InformacaoPedidoDTO buscarPedido(@PathVariable("id") Integer id) {
		Pedido pedido = pedidos.buscarPedido(id);
		return InformacaoPedidoDTO.builder()
		.codigo(id)
		.cpf(pedido.getCliente().getCpf())
		.nome(pedido.getCliente().getNome())
		.itens(converterItensEmDTO(pedido.getItens()))
		.valorTotal(pedido.getValorTotal())
		.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		.build();
	}

	//converte a lista de ItemPedido do pedido para a lista de InformacaoItemPedidoDTO
	public List<InformacaoItemPedidoDTO> converterItensEmDTO(List<ItemPedido> itens) {
		return itens.stream().map(item -> {
			return InformacaoItemPedidoDTO.builder()
					.descricaoProduto(item.getProduto().getDescricao())
					.quantidade(item.getQuantidade())
					.valorUnitario(item.getProduto().getValor()).build();

		}).collect(Collectors.toList());
	}

}
