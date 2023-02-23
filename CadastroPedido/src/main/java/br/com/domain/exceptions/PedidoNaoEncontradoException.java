package br.com.domain.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}

}
