package br.com.jackson.pizzaria.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.ClienteBuilder;
import br.com.jackson.pizza.model.ItemPedido;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.model.Produto;
import br.com.jackson.pizza.model.StatusPedidoType;
import br.com.jackson.pizza.repository.PedidoRepository;
import br.com.jackson.pizza.service.impl.PedidoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTestes {

	@Mock
	private PedidoRepository pedidoRepository;

	@Mock
	private PedidoServiceImpl pedidoService;

	@Before
	public void setUp() throws Exception {
		pedidoService = new PedidoServiceImpl();
		pedidoService.setPedidoRepository(pedidoRepository);
	}
	
	@Test
	public void addPedido() {
		
		ClienteBuilder cliente = new ClienteBuilder();
		cliente.nome("Jackson").endereco("Rua Teste").telefone("99999");
		Cliente c = cliente.build();
		
		Produto produto = new Produto();
		produto.setCodProduto(1);
		produto.setDescricao("Pizza Bla");
		produto.setPreco(12.9F);
		
		List<ItemPedido> listaItemPedidos = new ArrayList<ItemPedido>();
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId_item_pedido(1L);
		//itemPedido.setProduto(produto);
		itemPedido.setQdte(2);
		
		listaItemPedidos.add(itemPedido);
		
		
		Pedido pedido = new Pedido();
		pedido.setCliente(c);
		pedido.setIdPedido(1L);
		pedido.setItemPedidos(listaItemPedidos);
		pedido.setStatus(StatusPedidoType.ABERTO);
		
		pedidoService.adicionaPedido(pedido);
		
		
		Mockito.verify(pedidoService, Mockito.times(1)).adicionaPedido(pedido);
		
	}

}
