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

import br.com.domain.entities.Produto;
import br.com.domain.exceptions.ProdutoNaoEncontradoException;
import br.com.domain.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtos;

	// busca produto pelo id
	@GetMapping("/{id}")
	public Produto buscarProdutoById(@PathVariable("id") Integer id) {
		return produtos.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}

	// lista todos os produtos
	@GetMapping("/all")
	public List<Produto> buscarTodosProdutos(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return produtos.findAll(example);
	}

	// salva produto
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvarProduto(@RequestBody @Valid Produto produto) {
		return produtos.save(produto);
	}

	// atualiza produto pelo id
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarProduto(@RequestBody @Valid Produto produto, @PathVariable("id") Integer id) {
		produtos.findById(id).map(produtoFound -> {
			produto.setId(produtoFound.getId());
			produtos.save(produto);
			return produto;
		}).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}

	// deleta produto pelo id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProduto(@PathVariable Integer id) {
		produtos.findById(id).map(produtoFound -> {
			produtos.deleteById(produtoFound.getId());
			return null;
		}).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}
}
