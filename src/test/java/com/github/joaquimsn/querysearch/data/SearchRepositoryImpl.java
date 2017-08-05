package com.github.joaquimsn.querysearch.data;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchRepositoryImpl implements SearchRepository {
	
	private EntityManager entityManager;
	
	public SearchRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Query getQueryJpql(String jpql) {
		return entityManager.createQuery(jpql);
	}

	@Override
	public Query getQueryNativeQuery(String sqlQuery) {
		return entityManager.createNativeQuery(sqlQuery);
	}

}
