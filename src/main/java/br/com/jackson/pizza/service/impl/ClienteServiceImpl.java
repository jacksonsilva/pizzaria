package br.com.jackson.pizza.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.repository.ClienteRepository;
import br.com.jackson.pizza.service.ClienteService;

@Transactional(readOnly=true)
@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService, Serializable{

	private static final long serialVersionUID = -2145193559130823988L;
	
	@Autowired
	ClienteRepository clienteRepository;

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public void addCliente(Cliente cliente) {
		this.clienteRepository.adicionaCliente(cliente);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCliente(Cliente cliente) {
		this.clienteRepository.atualizaCliente(cliente);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void removeCliente(Cliente cliente) {
		this.clienteRepository.removeCliente(cliente);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Cliente> getClienteByNome(String nome) {
		return this.clienteRepository.buscaClientePeloNome(nome);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente getClienteByTelefone(String telefone) {
		return this.clienteRepository.buscaClientePeloTelefone(telefone);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente getClienteById(int id) {
		return this.clienteRepository.buscaClientePeloId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> getClientes() {
		return this.clienteRepository.buscaTodosClientes();
	}
		
}
