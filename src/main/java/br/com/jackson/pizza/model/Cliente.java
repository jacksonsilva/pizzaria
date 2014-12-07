package br.com.jackson.pizza.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
@Table(name="cliente")
@NamedQueries({
	@NamedQuery(name="procuraClienteId", query="from Cliente c where c.id = :id"),
	@NamedQuery(name="procuraClienteNome", query="from Cliente c where c.nome like :clienteNome"),
	@NamedQuery(name="procuraClienteTelefone", query="from Cliente c where c.telefone = :telefone"),
	@NamedQuery(name="procuraClienteTodos", query="from Cliente c")
	
})

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1662955237026967722L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String celular;
	private String endereco;
	private int numero;
	private String bairro;
	private Calendar data_nascimento;
	
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Index(name="ix_nome")
	@Column(name="nome", unique=false, nullable=false, length=50)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="sobrenome", unique=false, nullable=true, length=100)
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@Index(name="ix_telefone")
	@Column(name="telefone", unique=false, nullable=false, length=10)
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Column(name="celular", unique=false, nullable=true, length=10)
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Column(name="endereco", unique=false, nullable=false, length=100)
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column(name="numero", unique=false, nullable=false, length=5)
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Column(name="bairro", unique=false, nullable=true, length=100)
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_nascimento", unique=false, nullable=true)
	public Calendar getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: ").append(getNome())
		.append(" Sobrenome: ").append(getSobrenome())
		.append(" Telefone: ").append(getTelefone())
		.append(" Sobrenome: ").append(getSobrenome());
		
		return sb.toString();
	}
	
	
}
