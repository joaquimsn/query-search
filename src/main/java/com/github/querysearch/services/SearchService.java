package com.github.querysearch.services;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;

import com.github.querysearch.JpqlFilter;
import com.github.querysearch.SqlFilter;
import com.github.querysearch.beans.PageResult;
import com.github.querysearch.beans.SearchConfig;
import com.github.querysearch.data.SearchRepository;

public interface SearchService extends Serializable {

	public default PageResult search(JpqlFilter filter, SearchConfig searchConfig) {
		Objects.requireNonNull(filter, "jpqlFilter is required");
		Objects.requireNonNull(searchConfig, "SearchConfig is required");

		try {
			return getRepository().search(filter, searchConfig);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default PageResult search(SqlFilter filter, SearchConfig searchConfig) {
		Objects.requireNonNull(filter, "SqlFilter is required");
		Objects.requireNonNull(searchConfig, "SearchConfig is required");

		try {
			return getRepository().search(filter, searchConfig);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default List<?> search(JpqlFilter filter) {
		Objects.requireNonNull(filter, "jpqlFilter is required");

		try {
			return getRepository().search(filter);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default List<?> search(SqlFilter filter) {
		Objects.requireNonNull(filter, "sqlFilter is required");

		try {
			return getRepository().search(filter);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public abstract SearchRepository getRepository();
}
