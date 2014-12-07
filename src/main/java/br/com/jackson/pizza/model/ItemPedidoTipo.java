package br.com.jackson.pizza.model;

public enum ItemPedidoTipo {
	
	NORMAL(1), MONTADA(2);
	
	private int idType;

	public int getIdStatus() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	private ItemPedidoTipo(int idType){
		this.idType = idType;
	}
	
	public static ItemPedidoTipo fromValue(int idType){
		for (ItemPedidoTipo stp: ItemPedidoTipo.values()){
			if (stp.idType == idType) {
				return stp;
			}
		}
		
		throw new IllegalArgumentException("Tipo Item Pedido invalido: " + idType);
	}
	
	public int toValue(){
		return idType;
	}

}
