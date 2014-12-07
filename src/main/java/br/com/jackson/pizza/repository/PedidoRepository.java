package br.com.jackson.pizza.repository;

import java.util.List;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.model.Produto;

public interface PedidoRepository {
	
	public void adicionaPedido(Pedido pedido);
	public void atualizaPedido(Pedido pedido);
	public void removePedido(Pedido pedido);
	public Pedido buscarPeloPedido(Long idPedido);
	public List<Pedido> buscarTodosPedidos();
	public Cliente buscaPedidoPeloCliente(Long idCliente);

}
