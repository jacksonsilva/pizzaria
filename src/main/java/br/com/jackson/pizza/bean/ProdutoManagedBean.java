package br.com.jackson.pizza.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.jackson.pizza.model.Produto;
import br.com.jackson.pizza.service.ProdutoService;

@ManagedBean(name="produtoMB")
@ViewScoped
public class ProdutoManagedBean implements Serializable{

	
	private static final long serialVersionUID = 1157542716619679409L;
	private static final String TAG_MENSAGEM = "Cadastro_Produto:mensagens";
	private static final String TAG_MENSAGEM_ERRO = "Cadastro_Produto:mensagens_erro";
	
	//Spring User Service Injection
	@ManagedProperty(value="#{ProdutoService}")
	ProdutoService produtoService;
	
	List<Produto> produtoList;
	Produto produtosSelecionado;
	List<Produto> filtroProdutos;
	
	private int id;
	private int codProduto;
	private String descricao;
	private Float preco;
	

	public ProdutoManagedBean(){
	}

	@PostConstruct
	public void init(){
		reset();
	}
	
	public void addProduto(){
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			Produto produto = new Produto(getCodProduto(), getDescricao(), getPreco());
			getProdutoService().addProduto(produto);
			facesContext.addMessage(TAG_MENSAGEM, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Produto adicionado com sucesso!"));
		
		} catch (DataIntegrityViolationException de){
			facesContext.addMessage(TAG_MENSAGEM_ERRO, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Produto com esse código já cadastrado, verifique."));
			
		} catch (DataAccessException e){
			facesContext.addMessage(TAG_MENSAGEM_ERRO, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocorreu um erro ao adicionar o produto! " + e.getMessage()));
		}
	
	}
	
	public void onRowEdit(RowEditEvent event) {
		
		produtoService.updateProduto((Produto)event.getObject());
		
        FacesMessage msg = new FacesMessage("Produto alterado - ", ((Produto) event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Alterção Cancelada - ", ((Produto) event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterada de:", "Old: " + oldValue + ", para:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void excluirProduto(String idProduto){
    	System.out.println("Cod Produto " + idProduto);
    	Produto produto = new Produto();
    	produto.setId(Integer.parseInt(idProduto));
    	
    	produtoService.removeProduto(produto);
    	
    	produtoList=null;
    	getProdutoList();
		
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão ", "Produto excluído");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
	
	
	
	public void reset(){
		this.setCodProduto(0);
		this.setDescricao("");
		this.setPreco(null);
	}
	
	public List<Produto> getProdutoList(){
		
		if (produtoList == null){
			produtoList = new ArrayList<Produto>();
			produtoList.addAll(getProdutoService().getProdutos());
		}
		
		return produtoList;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public Produto getProdutosSelecionados() {
		return produtosSelecionado;
	}

	public void setProdutosSelecionados(Produto produtosSelecionado) {
		this.produtosSelecionado = produtosSelecionado;
	}

	public List<Produto> getFiltroProdutos() {
		return filtroProdutos;
	}

	public void setFiltroProdutos(List<Produto> filtroProdutos) {
		this.filtroProdutos = filtroProdutos;
	}
	
	
}
