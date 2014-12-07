package br.com.jackson.pizzaria.servicos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jackson.pizza.model.ClienteBuilder;
import br.com.jackson.pizza.repository.ClienteRepository;
import br.com.jackson.pizza.service.impl.ClienteServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ClienteServicoTestes {
	
	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private ClienteServiceImpl clienteService;

	@Before
	public void setUp() throws Exception {
		clienteService = new ClienteServiceImpl();
		clienteService.setClienteRepository(clienteRepository);
	}
	
	@Test
	public void addCliente() {
		ClienteBuilder cliente = new ClienteBuilder();
		cliente.nome("Jackson")
		.telefone("39231995")
		.endereco("Rua Oscar José Alves")
		.sobrenome("William");
		
		clienteService.addCliente(cliente.build());
		
		Mockito.verify(clienteService, Mockito.times(1)).addCliente(cliente.build());
		
	}
	
/*
	@Override
	@Transactional(readOnly = false)
	public void updateCliente(Cliente cliente) {
		this.clienteRepository.atualizaCliente(cliente);
		
	}

	@Override
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
*/
}
