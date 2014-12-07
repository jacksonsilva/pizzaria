package br.com.jackson.pizza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jackson.pizza.model.User;
import br.com.jackson.pizza.repository.UserRepository;

@Transactional(readOnly=true)
@Service("UserService")
//public class UserServiceImpl implements UserService{
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;
	

	@Transactional(readOnly = false)
	public void addUser(User user) {
		getUserRepository().addUser(user);
	}


	@Transactional(readOnly = false)
	public void updateUser(User user) {
		getUserRepository().updateUser(user);
	}


	@Transactional(readOnly = false)
	public void removeUser(User user) {
		getUserRepository().removeUser(user);
	}


	public User getUserById(int id) {
		return getUserRepository().getUserById(id);
	}


	public List<User> getUsers() {
		return getUserRepository().getUsers();
	}
	
	public UserRepository getUserRepository(){
		return userRepository;
	}
	
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}


	/*@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User user = userRepository.findUserByLogin(login);
		
		if (user == null)
			throw new UsernameNotFoundException("Usuario não encontrado!");
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		
		
		return new org.springframework.security.core.userdetails.User( user.getLogin(), user.getPassword(), authorities);
	}*/
	
	

	
}
