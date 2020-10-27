package com.web.repo;

import java.util.List;

public interface DaoContract<T, I, S> {
	
	List<T> findAll();
	
	T findById(I i);
	
	List<T> findByName(S s);
	
	T findByNameSingle(S s);
	
	List<T> findByStatus(S s);
	
	T findByNamePass(S s, S s2);
	
	int update(T t);
	
	int create(T t);
	
	int delete(I i);
	
}
