package br.com.domain.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.entities.Cliente;
import br.com.domain.exceptions.ClienteNaoEncontradoException;
import br.com.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clientes;

	// Busca cliente pelo id
	@GetMapping("/{id}")
	public Cliente buscarClienteById(@PathVariable("id") Integer id) {
		return clientes.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	// Salva Cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvarCliente(@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);
	}

	// Atualiza Cliente pelo id
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable("id") Integer id, @RequestBody @Valid Cliente cliente) {
		clientes.findById(id).map(clienteFound -> {
			cliente.setId(clienteFound.getId());
			clientes.save(cliente);
			return cliente;
		}).orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	// Deleta cliente pelo ID
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable("id") Integer id) {
		clientes.findById(id).map(clienteFound -> {
			clientes.deleteById(clienteFound.getId());
			return null;
		}).orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	// Lista todos clientes
	@GetMapping("/all")
	public List<Cliente> buscarTodosClientes(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientes.findAll(example);
		return lista;
	}

}
