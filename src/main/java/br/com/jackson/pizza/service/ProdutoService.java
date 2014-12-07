package br.com.jackson.pizza.service;

import java.util.List;

import br.com.jackson.pizza.model.Produto;

public interface ProdutoService {

	public void addProduto(Produto produto);
	public void updateProduto(Produto produto);
	public void removeProduto(Produto produto);
	public Produto getProdutoByCod(int cod);
	public Produto getProdutoById(int id);
	public List<Produto> getProdutos();
	
}
