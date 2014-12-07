package br.com.jackson.pizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.DigestUtils;

import br.com.jackson.pizza.bean.Role;

@Entity
@Table(name="USUARIO")
public class LoginModel {

	@Id
	private int id;
	private String login;
	private String password;
	private Role role;

	protected LoginModel(){}
	
	public LoginModel(String login, String password, Role role){
		
		this.login = login;
		this.password = DigestUtils.md5DigestAsHex(password.getBytes());
		this.role = role;
		
	}
	
	
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="login",unique=true, nullable=false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name="password",unique=true, nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="role",unique=false, nullable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: ").append(getId())
		.append(", login: ").append(getLogin())
		.append(", role: ").append(getRole());
		
		return strBuilder.toString();
	}
	
	
	
}
