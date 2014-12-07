package br.com.jackson.pizza.repository;

import java.util.List;

import br.com.jackson.pizza.model.Produto;

public interface ProdutoRepository {
	
	public void addProduto(Produto produto);
	public void updateProduto(Produto produto);
	public void removeProduto(Produto produto);
	public Produto getProdutoByCod(int cod);
	public Produto getProdutoById(int id);
	public List<Produto> getProdutos();
	public Produto findProdutoByDescricao(String descricao);

}
