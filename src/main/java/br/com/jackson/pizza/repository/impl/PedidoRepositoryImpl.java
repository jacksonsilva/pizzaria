package br.com.jackson.pizza.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.repository.PedidoRepository;

@Repository("PedidoRepository")
public class PedidoRepositoryImpl implements PedidoRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Pedido buscarPeloPedido(Long idPedido) {
		
		return (Pedido)getSessionFactory().getCurrentSession().createQuery("from Pedido where id=?")
		.setParameter(0, idPedido).uniqueResult();
		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> buscarTodosPedidos() {
		List<Pedido> listaPedidos = getSessionFactory().getCurrentSession().createQuery("from Pedidos").list();
		
		return listaPedidos;
	}

	@Override
	//TODO Implementar a busca pelo Cliente
	public Cliente buscaPedidoPeloCliente(Long idCliente) {
		return null;
	}

	@Override
	public void adicionaPedido(Pedido pedido) {
		 getSessionFactory().getCurrentSession().save(pedido);
		
	}

	@Override
	public void atualizaPedido(Pedido pedido) {
		getSessionFactory().getCurrentSession().update(pedido);
		
	}

	@Override
	public void removePedido(Pedido pedido) {
		getSessionFactory().getCurrentSession().delete(pedido);
		
	}

	
}
