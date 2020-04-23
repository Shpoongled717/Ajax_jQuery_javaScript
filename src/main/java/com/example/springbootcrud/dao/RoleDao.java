package com.example.springbootcrud.dao;

import com.example.springbootcrud.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Set<Role> getRoleById(Long ids[]) {
		return new HashSet<Role>(sessionFactory.getCurrentSession().createQuery("FROM Role r where r.id in (:ids)").setParameterList("ids", ids).list());
	}
	
	@Transactional
	public Role getRoleByName(String name) {
		Role role = (Role) sessionFactory.getCurrentSession().createQuery("from Role where name = :name").setParameter("name", name).getSingleResult();
		return role;
	}
	
	@Transactional
	public Set<Role> roleList() {
		return new HashSet<Role>(sessionFactory.getCurrentSession().createQuery("from Role").list());
	}
}
