package br.com.domain.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
	
	public ProdutoNaoEncontradoException() {
		super("Produto não encontrado");
	}

}
