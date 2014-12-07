package br.com.jackson.pizza.repository.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jackson.pizza.model.Produto;
import br.com.jackson.pizza.model.User;
import br.com.jackson.pizza.repository.ProdutoRepository;


@Repository("ProdutoRepository")
public class ProdutoRepositoryImpl implements ProdutoRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addProduto(Produto produto) {
		getSessionFactory().getCurrentSession().save(produto);
		
	}

	@Override
	public void updateProduto(Produto produto) {
		getSessionFactory().getCurrentSession().update(produto);
		
	}

	@Override
	public void removeProduto(Produto produto) {
		getSessionFactory().getCurrentSession().delete(produto);
		
	}

	@Override
	public Produto getProdutoById(int id) {

		return (Produto)getSessionFactory().getCurrentSession().createQuery("from Produto where id=?")
				.setParameter(0, id).uniqueResult();
		
	}
	
	@Override
	public Produto getProdutoByCod(int cod) {

		return (Produto)getSessionFactory().getCurrentSession().createQuery("from Produto where codProduto=?")
				.setParameter(0, cod).uniqueResult();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> getProdutos() {
		List<Produto> listprod = getSessionFactory().getCurrentSession().createQuery("from Produto").list();
		
		return listprod;
	}
	
	@Override
	public Produto findProdutoByDescricao(String descricao){
		
		Criteria criteria  = getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.ilike("descricao", descricao));
		
		Produto produto = (Produto)criteria.list().get(0);
		
		return produto;
		
	}

	
	
	
}
