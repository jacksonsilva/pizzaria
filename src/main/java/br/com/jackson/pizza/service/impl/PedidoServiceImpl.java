package br.com.jackson.pizza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.repository.ItemPedidoRepository;
import br.com.jackson.pizza.repository.PedidoRepository;
import br.com.jackson.pizza.service.PedidoService;


@Transactional(readOnly=true)
@Service("PedidoService")
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public PedidoRepository getPedidoRepository() {
		return pedidoRepository;
	}

	public void setPedidoRepository(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	public ItemPedidoRepository getItemPedidoRepository() {
		return itemPedidoRepository;
	}

	public void setItemPedidoRepository(ItemPedidoRepository itemPedidoRepository) {
		this.itemPedidoRepository = itemPedidoRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public void adicionaPedido(Pedido pedido) {
		
		getPedidoRepository().adicionaPedido(pedido);
		
		//getItemPedidoRepository().adicionaItemPedido(pedido.getItemPedidos());
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public void atualizaPedido(Pedido pedido) {
		getPedidoRepository().atualizaPedido(pedido);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void removePedido(Pedido pedido) {
		getPedidoRepository().removePedido(pedido);
		
	}

	@Override
	public Pedido buscarPeloPedido(Long idPedido) {
		return getPedidoRepository().buscarPeloPedido(idPedido);
	}

	@Override
	public List<Pedido> buscarTodosPedidos() {
		List<Pedido> listaPedidos = getPedidoRepository().buscarTodosPedidos();
		return listaPedidos;
	}

	@Override
	public Cliente buscaPedidoPeloCliente(Long idCliente) {
		return getPedidoRepository().buscaPedidoPeloCliente(idCliente);
	}
	
	//TODO: Deixar aqui a responsabilidade de montar na lista de Item Pedido os Pedidos Montados
	//remover o private
	private void adicionaItemMontadoPedido(){
		
	}

}
