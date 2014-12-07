package br.com.jackson.pizza.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.dao.DataAccessException;

import br.com.jackson.pizza.model.User;
import br.com.jackson.pizza.service.UserService;

//@ManagedBean(name="userMB")
public class UserManagedBean implements Serializable{

	private static final long serialVersionUID = -3227560778828776418L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	//Spring User Service Injection
	@ManagedProperty(value="#{UserService}")
	UserService userService;
	
	List<User> userList;
	private int id;
	private String login;
	private String password;
	private String role;
	
	protected UserManagedBean (){}
	
	public UserManagedBean(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public String addUser(){
		
		try {
		User user = new User(getLogin(), getPassword(), getRole());
		
		getUserService().addUser(user);	
		return SUCCESS;
		
		}catch (DataAccessException e){
			e.printStackTrace();
		}
		
		return ERROR;
	}
	
	public void reset(){
		this.setId(0);
		this.setLogin("");
		this.setPassword("");
	}
	
	public List<User> getUserList(){
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		
		return userList;
	}
	
	public UserService getUserService(){
		return this.userService;
	}
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void setUserList(List<User> userList){
		this.userList = userList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
}
