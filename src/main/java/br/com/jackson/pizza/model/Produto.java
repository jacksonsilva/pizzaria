package br.com.jackson.pizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Produto")
public class Produto {

	private int id;
	private int codProduto;
	private String descricao;
	private Float preco;
	
	public Produto(){}
	
	public Produto(int codProduto, String descricao, Float preco){
		this.codProduto = codProduto;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="codProduto", unique=true, nullable=false)
	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	@Column(name="descricao",unique=false, nullable=false)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="preco", unique=false, nullable=true)
	public Float getPreco() {
		return preco;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: ").append(getId())
		.append(", descricao: ").append(getDescricao())
		.append(", preco: ").append(getPreco());
		
		return strBuilder.toString();
	}
	
	
	
}
