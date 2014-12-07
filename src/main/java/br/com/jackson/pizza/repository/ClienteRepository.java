package br.com.jackson.pizza.repository;

import java.util.List;

import br.com.jackson.pizza.model.Cliente;

public interface ClienteRepository {

	public void adicionaCliente(Cliente cliente);
	public void atualizaCliente(Cliente cliente);
	public void removeCliente(Cliente cliente);
	public List<Cliente> buscaClientePeloNome(String nome);
	public Cliente buscaClientePeloTelefone(String telefone);
	public Cliente buscaClientePeloId(int id);
	public List<Cliente> buscaTodosClientes();
}
