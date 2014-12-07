package br.com.jackson.pizza.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import br.com.jackson.pizza.conveter.ItemPedidoConverter;
import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.ItemPedido;
import br.com.jackson.pizza.model.Pedido;
import br.com.jackson.pizza.model.Produto;
import br.com.jackson.pizza.model.StatusPedidoType;
import br.com.jackson.pizza.service.ClienteService;
import br.com.jackson.pizza.service.PedidoService;
import br.com.jackson.pizza.service.ProdutoService;

@ManagedBean(name = "pedidoMB")
@ViewScoped
public class PedidoManagedBean implements Serializable {

	private static final long serialVersionUID = 4375243375731329461L;

	private static final String TAG_MENSAGEM = "formItemPedido:msgs";

	List<Item> listaItens = new ArrayList<Item>();
	private Cliente cliente;
	private Produto produto;
	private Item item;
	private transient Float valorTotalPedido = 0.00f;
	private Integer idItemGenerator = 0;
	
	//TODO: Verificar essa implementacao
	List<Item> listaItensMontado = new ArrayList<Item>();
	private Item itemMontado;
	private Integer idItemMontatadoGenerator = 0;
	private transient float valorItemPedidoMontado = 0.0f;
	
	private Pedido pedido;
	
	@ManagedProperty(value = "#{PedidoService}")
	PedidoService pedidoService;

	@ManagedProperty(value = "#{ClienteService}")
	ClienteService clienteService;

	@ManagedProperty(value = "#{ProdutoService}")
	ProdutoService produtoService;
	
	@ManagedProperty(value="#{itemPedidoConverter}")
	ItemPedidoConverter itemPedidoConverter;
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float getValorItemPedidoMontado() {
		return valorItemPedidoMontado;
	}

	public void setValorItemPedidoMontado(float valorItemPedidoMontado) {
		this.valorItemPedidoMontado = valorItemPedidoMontado;
	}

	public Float getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Float valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	
	public ItemPedidoConverter getItemPedidoConverter() {
		return itemPedidoConverter;
	}

	public void setItemPedidoConverter(ItemPedidoConverter itemPedidoConverter) {
		this.itemPedidoConverter = itemPedidoConverter;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Item> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public List<Item> getListaItensMontado() {
		return listaItensMontado;
	}

	public void setListaItensMontado(List<Item> listaItensMontado) {
		this.listaItensMontado = listaItensMontado;
	}

	public Item getItemMontado() {
		return itemMontado;
	}

	public void setItemMontado(Item itemMontado) {
		this.itemMontado = itemMontado;
	}

	@PostConstruct
	public void init() {
		
		if (cliente == null) {
			setCliente(new Cliente());
		}

		if (produto == null) {
			setProduto(new Produto());
		}
		
		if (pedido == null){
			setPedido(new Pedido());
		}
		
		if(item == null){
			item = new Item();
			itemMontado = new Item();
		}
		
		
	}

	public void procuraCliente() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		Cliente clienteBusca = getClienteService().getClienteByTelefone(
				getCliente().getTelefone());
		if (clienteBusca == null) {
			
			setCliente(new Cliente());
			
			FacesMessage msg = new FacesMessage("Cliente não encontrado!");
			facesContext.addMessage(null, msg);
			return;
		}

		if (clienteBusca.getNumero() > 0) {
			clienteBusca.setEndereco(clienteBusca.getEndereco() + " ,");
		}

		if (clienteBusca.getBairro() != null) {
			clienteBusca.setBairro(" - " + clienteBusca.getBairro());
		}

		setCliente(clienteBusca);
	}

	public void procuraProduto() {

		if (getItem().getCodProduto() != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	
			Produto produto = getProdutoService().getProdutoByCod( Integer.valueOf(getItem().getCodProduto() ));
			if (produto == null) {
				
				FacesMessage msg = new FacesMessage("Produto não encontrado.");
				facesContext.addMessage(null, msg);
	
			}else {
				this.item.setDescricaoProduto(produto.getDescricao());
				this.item.setValorUnitProduto(produto.getPreco());
				this.item.setQtdeProduto(1);
				
				//TODO: Remover esse hardCode e adiconar na tabela para verificacao de produto montados
				if (produto.getCodProduto() == 99999) {
					this.item.setMontado(Boolean.TRUE);
					RequestContext.getCurrentInstance().execute("PF('dlgMontarPizza').show()");
				}
	
			}
		}
	}

	public void addItemPedido(ActionEvent event) {
		
		addItemPedido();
		/*if (this.item.getQtdeProduto() != null){
			this.item.setValorSubProduto(this.item.getValorUnitProduto() * this.item.getQtdeProduto());
		}
		
		idItemGenerator += 1;
		this.item.setIdItem(idItemGenerator);
		
		listaItens.add(this.item);
		this.item = new Item();
		
		recalculaTotal();*/
		
	}
	
	private void addItemPedido(){
		
		if (this.item.getQtdeProduto() != null){
			this.item.setValorSubProduto(this.item.getValorUnitProduto() * this.item.getQtdeProduto());
		}
		
		idItemGenerator += 1;
		this.item.setIdItem(idItemGenerator);
		
		listaItens.add(this.item);
		this.item = new Item();
		
		recalculaTotal();
		
	}

	public void onRowEdit(RowEditEvent event) {

		Item _item = (PedidoManagedBean.Item)event.getObject();
		_item.setValorSubProduto(_item.getValorUnitProduto() * _item.getQtdeProduto());
		recalculaTotal();
		
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Alterção Cancelada - ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private void recalculaTotal(){
		
		valorTotalPedido = 0.00f;
		for (Item it :listaItens){
			valorTotalPedido += Float.valueOf(it.getValorSubProduto());
		}
	}
	
	public void excluirItem(Integer id){
		for (Item item : listaItens){
			if (item.idItem == id){
				listaItens.remove(item);
				break;
			}
		}
		recalculaTotal();
	}
	
	
	public void addPedido(){
		
		boolean error = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try{
		
			if (getCliente().getId() == null) {
				facesContext.addMessage(TAG_MENSAGEM, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não foi encontrado o cliente."));
				error = true;
			}
			
			if (getListaItens() == null && getListaItens().get(0) == null) {
				facesContext.addMessage(TAG_MENSAGEM, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Não existe itens para o pedido."));
				error = true;
			}
			
			if (!error){
				//TODO Verificar o processo que ira salvar o pedido
				Pedido pedido = new Pedido();
				pedido.setCliente(getCliente());
				pedido.setItemPedidos(itemPedidoConverter.conveter(getListaItens()));
				pedido.setStatus(StatusPedidoType.ABERTO);
				pedido.setDataPedido(Calendar.getInstance().getTime());
				pedido.setValorTotal(valorTotalPedido);
				
				pedidoService.adicionaPedido(pedido);
				
				facesContext.addMessage(TAG_MENSAGEM, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido", "Pedido Realizado!"));
				
			}
		} catch (Exception e ){
			e.printStackTrace();
		}
		
	}
	
	public void addItemPedidoMontado(ActionEvent event) {
		
		idItemMontatadoGenerator += 1;
		this.itemMontado.setIdItem(idItemMontatadoGenerator);
		
		listaItensMontado.add(this.itemMontado);
		recalculaTotalM();
		
		this.itemMontado = new Item();

	}
	
	public void addPedidoMontado(){
	
		getListaItens();
		
		//TODO: Criar o ItemPedidoMontadoConverter para ser possivel fazer o add no ItemPedido e o Montado
		Collection<ItemPedido> ip = itemPedidoConverter.conveter(getListaItensMontado());
		Item it = new Item();
		it.setCodProduto("99999");
		it.setDescricaoProduto("Pizza Montada");
		it.setQtdeProduto(1);
		it.setValorSubProduto(getValorItemPedidoMontado());
		it.setValorSubProduto(getValorItemPedidoMontado());
		
	}
	
	public void procuraProdutoMontado() {

		if (getItemMontado().getCodProduto() != ""){
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
	
			Produto produto = getProdutoService().getProdutoByCod( Integer.valueOf(getItemMontado().getCodProduto() ));
			if (produto == null) {
				
				FacesMessage msg = new FacesMessage("Produto não encontrado.");
				facesContext.addMessage(null, msg);
	
			}else {
				
				this.itemMontado.setDescricaoProduto(produto.getDescricao());
				this.itemMontado.setValorUnitProduto(produto.getPreco());
	
			}
		}
		
	}
	
	public void onRowEditM(RowEditEvent event) {

		Item _item = (PedidoManagedBean.Item)event.getObject();
		_item.setValorSubProduto(_item.getValorUnitProduto() * _item.getQtdeProduto());
		recalculaTotalM();
		
	}
	
	public void onRowCancelM(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Alterção Cancelada - ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void excluirItemM(Integer id){
		for (Item item : listaItensMontado){
			if (item.idItem == id){
				listaItensMontado.remove(item);
				break;
			}
		}
		
		recalculaTotalM();
	}
	
	private void recalculaTotalM(){
		
		float maiorValorItemMontado = 0.0f;
		
		for (Item itm : listaItensMontado){
			if (itm.getValorUnitProduto() > maiorValorItemMontado){
				maiorValorItemMontado = itm.getValorUnitProduto();
				valorItemPedidoMontado = maiorValorItemMontado;
			}
		}
	}
	
	public void confirmarPedidoMontado(){
		
		this.item.setListaItem(getListaItensMontado());
		this.item.setValorSubProduto(getValorItemPedidoMontado());
		this.item.setValorUnitProduto(getValorItemPedidoMontado());
		this.item.setQtdeProduto(1);
		
		addItemPedido();
		this.listaItensMontado = new ArrayList<Item>();
		this.itemMontado = new Item();
		RequestContext.getCurrentInstance().execute("PF('dlgMontarPizza').hide()");
		
	}
	
	public void cancelarPedidoMontado(){
		
		this.listaItensMontado = new ArrayList<Item>();
		this.itemMontado = new Item();
	}
	
	public static class Item {

		Integer idItem;
		String codProduto;
		Integer qtdeProduto;
		String descricaoProduto;
		Float valorUnitProduto;
		Float valorSubProduto;
		Boolean montado = Boolean.FALSE;
		List<Item> listaItem;

		Item() {
		}

		Item(String codProduto, Integer qtde, String descricaProduto,
				Float valorUnitProduto, Float valorSubProduto) {
			
			this.codProduto = codProduto;
			this.qtdeProduto = qtde;
			this.descricaoProduto = descricaProduto;
			this.valorUnitProduto = valorUnitProduto;
			this.valorSubProduto = valorSubProduto;
			
		}

		public void setIdItem(Integer id){
			this.idItem = id;
		}
		
		public Integer getIdItem(){
			return idItem;
		}
		public String getCodProduto() {
			return codProduto;
		}

		public void setCodProduto(String codProduto) {
			this.codProduto = codProduto;
		}

		public Integer getQtdeProduto() {
			return qtdeProduto;
		}

		public void setQtdeProduto(Integer qtdeProduto) {
			this.qtdeProduto = qtdeProduto;
		}

		public String getDescricaoProduto() {
			return descricaoProduto;
		}

		public void setDescricaoProduto(String descricaoProduto) {
			this.descricaoProduto = descricaoProduto;
		}

		public Float getValorUnitProduto() {
			return valorUnitProduto;
		}

		public void setValorUnitProduto(Float valorUnitProduto) {
			this.valorUnitProduto = valorUnitProduto;
		}

		public Float getValorSubProduto() {
			return valorSubProduto;
		}

		public void setValorSubProduto(Float valorSubProduto) {
			this.valorSubProduto = valorSubProduto;
		}
		
		public Boolean isMontado() {
			return montado;
		}

		public void setMontado(Boolean montado) {
			this.montado = montado;
		}

		public List<Item> getListaItem() {
			return listaItem;
		}

		public void setListaItem(List<Item> listaItem) {
			this.listaItem = listaItem;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 5>>>35;
			result = prime * result
					+ ((idItem == null) ? 0 : idItem.hashCode());
			
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			
			if (idItem == null) {
				if (other.idItem != null)
					return false;
			} else if (!idItem.equals(other.idItem))
				return false;
			
			return true;
		}
		
		

	}
}
