package br.com.jackson.pizza.service;

import java.util.List;

import br.com.jackson.pizza.model.Cliente;

public interface ClienteService {

	public void addCliente(Cliente cliente);
	public void updateCliente(Cliente cliente);
	public void removeCliente(Cliente cliente);
	public List<Cliente> getClienteByNome(String nome);
	public Cliente getClienteByTelefone(String telefone);
	public Cliente getClienteById(int id);
	public List<Cliente> getClientes();
	
}
