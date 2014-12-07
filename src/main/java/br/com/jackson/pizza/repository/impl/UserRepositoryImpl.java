package br.com.jackson.pizza.repository.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jackson.pizza.model.User;
import br.com.jackson.pizza.repository.UserRepository;


@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
		
	}

	@Override
	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().update(user);
		
	}

	@Override
	public void removeUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
		
	}

	@Override
	public User getUserById(int id) {
		List listUserById = getSessionFactory().getCurrentSession().createQuery("from User where id=?")
				.setParameter(0, id).list();
		
		return (User)listUserById.get(0);
		
	}

	@Override
	public List<User> getUsers() {
		List listUser = getSessionFactory().getCurrentSession().createQuery("from User").list();
		
		return listUser;
		
	}
	
	@Override
	public User findUserByLogin(String login){
		
		Criteria criteria  = getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		
		User user = (User)criteria.list().get(0);
		
		return user;
		
	}

	
	
	
}
