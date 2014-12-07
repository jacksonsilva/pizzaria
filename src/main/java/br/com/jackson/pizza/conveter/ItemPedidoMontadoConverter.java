package br.com.jackson.pizza.conveter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jackson.pizza.bean.PedidoManagedBean.Item;
import br.com.jackson.pizza.model.ItemPedidoMontada;

@Service
public class ItemPedidoMontadoConverter {

	public Collection<ItemPedidoMontada> converter(List<Item> listaItemMontada){
		
		if (listaItemMontada == null){
			return null;
		}
		
		Collection<ItemPedidoMontada> listaItemPedidoMontada = new ArrayList<ItemPedidoMontada>();
		
		for (Item _item: listaItemMontada){
			ItemPedidoMontada itemMontada = new ItemPedidoMontada();
			itemMontada.setIdProduto(Integer.valueOf(_item.getCodProduto()));
			listaItemPedidoMontada.add(itemMontada);
		}
		
		return listaItemPedidoMontada;
	
	}
}
