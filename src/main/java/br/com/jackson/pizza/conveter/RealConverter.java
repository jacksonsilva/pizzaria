package br.com.jackson.pizza.conveter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class RealConverter implements Converter {
	
	public RealConverter(){}

	@Override
	//TODO Precisa fazer a implementacao desse metodo 
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		
		if (value == null || (value.trim().length() ==0)) {
			return value;
		}
				
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
