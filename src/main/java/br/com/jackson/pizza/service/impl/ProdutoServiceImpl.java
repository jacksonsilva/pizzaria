package br.com.jackson.pizza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jackson.pizza.model.Produto;
import br.com.jackson.pizza.repository.ProdutoRepository;
import br.com.jackson.pizza.service.ProdutoService;

@Transactional(readOnly=true)
@Service("ProdutoService")
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	ProdutoRepository produtoRepository;


	public ProdutoRepository getProdutoRepository(){
		return produtoRepository;
	}
	
	public void setProdutoRepository(ProdutoRepository produtoRepository){
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addProduto(Produto produto) {
		getProdutoRepository().addProduto(produto);
		
	}


	@Override
	@Transactional(readOnly = false)
	public void updateProduto(Produto produto) {
		getProdutoRepository().updateProduto(produto);
		
	}


	@Override
	@Transactional(readOnly = false)
	public void removeProduto(Produto produto) {
		getProdutoRepository().removeProduto(produto);
		
	}


	@Override
	public Produto getProdutoById(int id) {
		return getProdutoRepository().getProdutoById(id);
	}


	@Override
	public List<Produto> getProdutos() {
		return getProdutoRepository().getProdutos();
	}
	
	
	@Override
	public Produto getProdutoByCod(int cod) {
		return getProdutoRepository().getProdutoByCod(cod);
	}

	
}
