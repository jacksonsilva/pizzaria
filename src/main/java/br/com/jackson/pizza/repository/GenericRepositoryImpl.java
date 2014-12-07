package br.com.jackson.pizza.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

	private Class<T> type;
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory session){
		this.sessionFactory = session;
	}
	
	public SessionFactory getSessionFactory(){
		if (sessionFactory == null){
			throw new IllegalStateException("SessionFactory has not been set on before usage");
		}
		
		return this.sessionFactory;
	}
	
	public Class<T> getType(){
		return type;
	}
	
	@SuppressWarnings("unchecked")
	public GenericRepositoryImpl(){
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<T> FindAllQuery(String query){
		return (List<T>) getSessionFactory().getCurrentSession().get(getType(), query);
	}
	
	
	
}
