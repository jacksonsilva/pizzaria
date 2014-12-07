package br.com.jackson.pizza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ItemPedidoMontada")
public class ItemPedidoMontada implements Serializable {

	private static final long serialVersionUID = 1928914461036495717L;

	@Id
	@GeneratedValue
	private int idItemPedidoMontada;
	
	@ManyToOne
	@JoinColumn(name="idItemPedido")
	private ItemPedido itemPedido;
	
	@Column(name="idProduto")
	private int idProduto;
	
	public int getIdItemPedidoMontada() {
		return idItemPedidoMontada;
	}

	public void setIdItemPedidoMontada(int idItemPedidoMontada) {
		this.idItemPedidoMontada = idItemPedidoMontada;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

}
	
