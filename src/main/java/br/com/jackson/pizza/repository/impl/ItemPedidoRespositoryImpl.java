package br.com.jackson.pizza.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jackson.pizza.model.ItemPedido;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.repository.ItemPedidoRepository;

@Repository("ItemPedidoRepository")
public class ItemPedidoRespositoryImpl implements ItemPedidoRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void adicionaItemPedido(ItemPedido itemPedido) {
		Collection<ItemPedido> listItemPedido = new ArrayList<ItemPedido>();
		listItemPedido.add(itemPedido);
		adicionaItemPedido(listItemPedido);
		
	}

	@Override
	public void atualizaItemPedido(ItemPedido itemPedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeItemPedido(ItemPedido itemPedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pedido buscarPeloItemPedido(Long idItemPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemPedido> buscarTodosItens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido buscaItemPeloPedido(Long idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adicionaItemPedido(Collection<ItemPedido> itemPedido) {
		
		for (ItemPedido _itemPedido : itemPedido){
			getSessionFactory().getCurrentSession().save(_itemPedido);
		}
		
	}

}
