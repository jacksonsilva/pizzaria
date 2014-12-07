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

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.jackson.pizza.model.Cliente;
import br.com.jackson.pizza.model.ClienteBuilder;
import br.com.jackson.pizza.service.ClienteService;

@ManagedBean(name="clienteMB")
@ViewScoped
public class ClienteManagedBean implements Serializable{


	private static final long serialVersionUID = -3332874567814842976L;
	private static final String TAG_MENSAGEM = "Cadastro_Cliente:mensagens";
	private static final String TAG_MENSAGEM_ERRO = "Cadastro_Cliente:mensagens_erro";
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String celular;
	private String endereco;
	private int numero;
	private String bairro;

	@ManagedProperty(value="#{ClienteService}")
	ClienteService clienteService;
	
	List<Cliente> clienteList;
	Cliente clientesSelecionados;
	List<Cliente> filtroClientes;
	
	@PostConstruct
	public void init(){
		reset();
	}
	
	public void reset(){
		this.setId(0L);
		this.setNome("");
		this.setSobrenome("");
		this.setTelefone("");
		this.setCelular("");
		this.setEndereco("");
		this.setNumero(0);
		this.setBairro("");
	}
	
	
	
	public Cliente getClientesSelecionados() {
		return clientesSelecionados;
	}

	public void setClientesSelecionados(Cliente clientesSelecionados) {
		this.clientesSelecionados = clientesSelecionados;
	}

	public List<Cliente> getFiltroClientes() {
		return filtroClientes;
	}

	public void setFiltroClientes(List<Cliente> filtroClientes) {
		this.filtroClientes = filtroClientes;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public List<Cliente> getClienteList(){
		
		if (clienteList == null){
			clienteList = new ArrayList<Cliente>();
			clienteList.addAll(getClienteService().getClientes());
		}
		
		return clienteList;
	}
	
	public String addCliente(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try{
		
			ClienteBuilder clienteBuilder = new ClienteBuilder();
			 clienteBuilder.nome(nome).sobrenome(sobrenome).telefone(telefone)
			.celular(celular).endereco(endereco).numero(numero)
			.bairro(bairro);
			
			Cliente cliente = clienteBuilder.build();
			getClienteService().addCliente(cliente);
			
			facesContext.addMessage(TAG_MENSAGEM, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cliente adicionado com sucesso!"));
			
		} catch (DataIntegrityViolationException de){
			facesContext.addMessage(TAG_MENSAGEM_ERRO, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cliente com esse código já cadastrado, verifique."));
		} catch (DataAccessException e){
			facesContext.addMessage(TAG_MENSAGEM_ERRO, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocorreu um erro ao adicionar o cliente! " + e.getMessage()));
		}
		
		return null;
	}
	
	public void onRowEdit(RowEditEvent event) {
		
		clienteService.updateCliente((Cliente)event.getObject());
		
        FacesMessage msg = new FacesMessage("Cliente alterado - ", ((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Alterção Cancelada - ", ((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void excluirCliente(String idCliente){
		
    	System.out.println("Id Cliente " + idCliente);
    	Cliente cliente = new Cliente();
    	cliente.setId(Long.parseLong(idCliente));
    	
    	clienteService.removeCliente(cliente);
    	
    	clienteList=null;
    	getClienteList();
		
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão ", "Cliente excluído");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" Codigo Cliente: ").append(id)
		  .append(" Nome: ").append(nome)
		  .append(" Sobrenome: ").append(sobrenome)
		  .append(" Telefone: ").append(telefone)
		  .append(" Celular: ").append(celular)
		  .append(" Endereco: ").append(endereco)
		  .append(" Numero: ").append(numero)
		  .append(" Bairro: ").append(bairro);
		
		return sb.toString();
	}

}
