package br.com.jackson.pizza.repository.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.repository.ClienteRepository;

@Repository("ClienteRepository")
public class ClienteRepositoryImpl implements ClienteRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void adicionaCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().save(cliente);
		
	}

	@Override
	public void atualizaCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().update(cliente);
		
	}

	@Override
	public void removeCliente(Cliente cliente) {
		getSessionFactory().getCurrentSession().delete(cliente);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> buscaClientePeloNome(String nome) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery("procuraClienteNome");
		query.setParameter("clienteNome", nome +"%");
		
		return Collections.checkedList(query.list(), Cliente.class);
		
	}

	@Override
	public Cliente buscaClientePeloTelefone(String telefone) {
		
		Query query = getSessionFactory().getCurrentSession().getNamedQuery("procuraClienteTelefone");
		query.setParameter("telefone", telefone);
		
		return (Cliente)query.uniqueResult();
		
		
	}

	@Override
	public Cliente buscaClientePeloId(int id) {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery("procuraClienteId");
		query.setParameter("id", id);
		
		return (Cliente)query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> buscaTodosClientes() {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery("procuraClienteTodos");
		
		return Collections.checkedList(query.list(), Cliente.class);
	}

}
