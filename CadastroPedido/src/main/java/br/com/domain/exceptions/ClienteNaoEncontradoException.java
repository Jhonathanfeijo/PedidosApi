package br.com.domain.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
	
	public ClienteNaoEncontradoException () {
		super("Cliente não encontrado");
	}
}
