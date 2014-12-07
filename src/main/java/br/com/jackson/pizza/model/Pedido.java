package br.com.jackson.pizza.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="Pedido")
public class Pedido implements Serializable{

	private static final long serialVersionUID = -1102627302743514602L;

	private Long idPedido;
	private Cliente cliente;
	private Date dataPedido;
	private StatusPedidoType status;
	private Float valorTotal;
	private Collection<ItemPedido> itemPedidos;

	@Id
	@GeneratedValue
	@Column(name="id_pedido")
	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_pedido")
	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)
	public StatusPedidoType getStatus() {
		return status;
	}

	public void setStatus(StatusPedidoType status) {
		this.status = status;
	}
	
	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_pedido")
	public Collection<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(Collection<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

}
