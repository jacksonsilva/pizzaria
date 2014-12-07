package br.com.jackson.pizza.repository;

import java.util.Collection;
import java.util.List;

import br.com.jackson.pizza.model.ItemPedido;
import br.com.jackson.pizza.model.Pedido;

public interface ItemPedidoRepository {
	public void adicionaItemPedido(ItemPedido itemPedido);
	public void adicionaItemPedido(Collection<ItemPedido> itemPedido);
	public void atualizaItemPedido(ItemPedido itemPedido);
	public void removeItemPedido(ItemPedido itemPedido);
	public Pedido buscarPeloItemPedido(Long idItemPedido);
	public List<ItemPedido> buscarTodosItens();
	public Pedido buscaItemPeloPedido(Long idPedido);

}
