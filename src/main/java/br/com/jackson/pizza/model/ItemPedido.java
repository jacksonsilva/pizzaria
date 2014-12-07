package br.com.jackson.pizza.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ItemPedido")
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = -8857141788379260557L;

	@Id
	@GeneratedValue
	private Long id_item_pedido;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@Column(name="id_produto")
	private int id_produto;
	
	@Column(name="qtde")
	private int qdte;
	
	@Column(name="idTipoPedidoItem")
	private ItemPedidoTipo tipoItemPedido = ItemPedidoTipo.NORMAL;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idItemPedido")
	private Collection<ItemPedidoMontada> itemPedidoMontada;

	public Long getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Long id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getQdte() {
		return qdte;
	}

	public void setQdte(int qdte) {
		this.qdte = qdte;
	}

	public ItemPedidoTipo getTipoItemPedido() {
		return tipoItemPedido;
	}

	public void setTipoItemPedido(ItemPedidoTipo tipoItemPedido) {
		this.tipoItemPedido = tipoItemPedido;
	}

	public Collection<ItemPedidoMontada> getItemPedidoMontada() {
		return itemPedidoMontada;
	}

	public void setItemPedidoMontada(Collection<ItemPedidoMontada> itemPedidoMontada) {
		this.itemPedidoMontada = itemPedidoMontada;
	}
	
}
