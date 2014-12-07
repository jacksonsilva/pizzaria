package br.com.jackson.pizza.conveter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jackson.pizza.bean.PedidoManagedBean.Item;
import br.com.jackson.pizza.model.ItemPedido;
import br.com.jackson.pizza.model.ItemPedidoMontada;
import br.com.jackson.pizza.model.Produto;

@Service
public class ItemPedidoConverter {
	
	@Autowired
	ItemPedidoMontadoConverter itemPedidoMontadaConverter;

	public Collection<ItemPedido> conveter(List<Item> item){
		
		Collection<ItemPedido> listItemPedido = new ArrayList<ItemPedido>();
		
		for (Item _item : item){
			ItemPedido itemPedido = new ItemPedido();
			Produto produto = new Produto();
			produto.setCodProduto(Integer.parseInt(_item.getCodProduto()));
			produto.setDescricao(_item.getDescricaoProduto());
			
			itemPedido.setId_produto(Integer.parseInt(_item.getCodProduto()));
			itemPedido.setQdte(_item.getQtdeProduto());
			
			itemPedido.setItemPedidoMontada(itemPedidoMontadaConverter.converter(_item.getListaItem()));
			
			listItemPedido.add(itemPedido);
		}
		
		return listItemPedido;
		
	}
	
}
