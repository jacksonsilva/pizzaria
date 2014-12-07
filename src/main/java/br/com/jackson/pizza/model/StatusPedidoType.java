package br.com.jackson.pizza.model;

public enum StatusPedidoType {
	
	ABERTO(1), FECHADO(2), CANCELADO(3);
	
	private int idStatus;

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	
	private StatusPedidoType(int idStatus){
		this.idStatus = idStatus;
	}
	
	public static StatusPedidoType fromValue(int idStatus){
		for (StatusPedidoType stp: StatusPedidoType.values()){
			if (stp.idStatus == idStatus) {
				return stp;
			}
		}
		
		throw new IllegalArgumentException("Status do Pedido invalido: " + idStatus);
	}
	
	public int toValue(){
		return idStatus;
	}

}
