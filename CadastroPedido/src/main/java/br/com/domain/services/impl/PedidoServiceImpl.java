package br.com.domain.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.DTO.ItemPedidoDTO;
import br.com.domain.DTO.PedidoDTO;
import br.com.domain.entities.Cliente;
import br.com.domain.entities.ItemPedido;
import br.com.domain.entities.Pedido;
import br.com.domain.entities.Produto;
import br.com.domain.exceptions.ClienteNaoEncontradoException;
import br.com.domain.exceptions.PedidoNaoEncontradoException;
import br.com.domain.exceptions.ProdutoNaoEncontradoException;
import br.com.domain.repositories.ClienteRepository;
import br.com.domain.repositories.ItemPedidoRepository;
import br.com.domain.repositories.PedidoRepository;
import br.com.domain.repositories.ProdutoRepository;
import br.com.domain.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private ClienteRepository clientes;
	@Autowired
	private ProdutoRepository produtos;
	@Autowired
	private ItemPedidoRepository itens;
	@Autowired
	private PedidoRepository pedidos;

	@Transactional
	@Override
	public Pedido salvar(PedidoDTO pedidoDTO) {

		Cliente cliente = clientes.findById(pedidoDTO.getCliente())
				.orElseThrow(() -> new ClienteNaoEncontradoException());
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());
		pedidos.save(pedido);
		List<ItemPedido> itensPedido = getItemPedidoList(pedidoDTO.getItens(), pedido);
		itens.saveAll(itensPedido);
		pedido.setItens(itensPedido);
		pedido.setValorTotal(calcularValorPedido(pedido.getItens()));
		pedidos.save(pedido);
		return pedido;
	}

	// converte lista de ItemPedidoDTO para lista de ItemPedido
	public List<ItemPedido> getItemPedidoList(List<ItemPedidoDTO> itensDTO, Pedido pedido) {

		return itensDTO.stream().map(item -> {
			ItemPedido itemPedido = new ItemPedido();
			Produto produto = produtos.findById(item.getProduto())
					.orElseThrow(() -> new ProdutoNaoEncontradoException());
			itemPedido.setProduto(produto);
			itemPedido.setPedido(pedido);
			itemPedido.setQuantidade(item.getQuantidade());
			return itemPedido;
		}).collect(Collectors.toList());
	}

	// calcula o valor total do pedido
	public BigDecimal calcularValorPedido(List<ItemPedido> itens) {

		BigDecimal valorTotal = new BigDecimal("0.0");
		for (ItemPedido item : itens) {
			String quantidadeString = String.valueOf(item.getQuantidade());
			BigDecimal valorItem = item.getProduto().getValor();
			valorItem = valorItem.multiply(new BigDecimal(quantidadeString));
			valorTotal = valorTotal.add(valorItem);
		}
		return valorTotal;
	}

	@Override
	public Pedido buscarPedido(Integer id) {
		return pedidos.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException());
	}

}
