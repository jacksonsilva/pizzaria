package br.com.jackson.pizza.repository;

import br.com.jackson.pizza.model.LoginModel;

public interface LoginRepository extends GenericRepository<LoginRepository> {
	
	public LoginModel findUserByLogin();

}
