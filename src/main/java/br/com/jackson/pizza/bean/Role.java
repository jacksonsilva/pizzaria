package br.com.jackson.pizza.bean;

public enum Role {

	ADMINISTRADOR(1), USUARIO(2);
	
	private final int role_opcao;
	
	Role(int role){
		role_opcao = role;
	}
	
	public int getRole(){
		return role_opcao;
	}
	
}
