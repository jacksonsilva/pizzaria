package br.com.jackson.pizza.repository;

import java.util.List;

public interface GenericRepository<T> {

	List<T> FindAllQuery(String query);
	List<T> FindAllQueryParam(String query, Object[] param);
	void Add(T item);
	void remove(T item);
	
	
}
