package br.com.jackson.pizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.DigestUtils;

@Entity
@Table(name="USER")
public class User {

	private int id;
	private String login;
	private String password;
	private String role;
	
	
	protected User(){}
	
	public User(String login, String password, String role){
		this.login = login;
		this.password = DigestUtils.md5DigestAsHex(password.getBytes());
		this.role = role;
	}
	
	@Id
	@GeneratedValue
	@Column(name="ID", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name",unique=true, nullable=false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="password", unique=true, nullable=false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="role", unique=false, nullable=false)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: ").append(getId())
		.append(", login: ").append(getLogin())
		.append(", password: ").append(getPassword())
		.append(", role: ").append(getRole());
		
		return strBuilder.toString();
	}
	
	
	
}
