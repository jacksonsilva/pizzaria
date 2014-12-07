package br.com.jackson.pizza.model;


public class ClienteBuilder {

	private Cliente cliente;
	
	public ClienteBuilder(){
		cliente = new Cliente();
	}
	
	
	public ClienteBuilder nome(String nome){
		cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder sobrenome(String sobrenome){
		cliente.setSobrenome(sobrenome);
		return this;
	}
	
	public ClienteBuilder telefone(String telefone){
		cliente.setTelefone(telefone);
		return this;
	}
	
	
	public ClienteBuilder celular(String celular){
		cliente.setCelular(celular);
		return this;
	}
	
	public ClienteBuilder endereco(String endereco){
		cliente.setEndereco(endereco);
		return this;
	}

	public ClienteBuilder numero(int numero){
		cliente.setNumero(numero);
		return this;
	}
	
	public ClienteBuilder bairro(String bairro){
		cliente.setBairro(bairro);
		return this;
	}
	
	public Cliente build(){
		return cliente;
	}
	
}
