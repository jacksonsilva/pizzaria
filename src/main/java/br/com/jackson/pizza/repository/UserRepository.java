package br.com.jackson.pizza.repository;

import java.util.List;

import br.com.jackson.pizza.model.User;

public interface UserRepository {
	
	public void addUser(User user);
	public void updateUser(User user);
	public void removeUser(User user);
	public User getUserById(int id);
	public List<User> getUsers();
	public User findUserByLogin(String username);

}
