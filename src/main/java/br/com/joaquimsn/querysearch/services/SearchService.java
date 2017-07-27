package br.com.joaquimsn.querysearch.services;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;

import br.com.joaquimsn.querysearch.JpqlFilter;
import br.com.joaquimsn.querysearch.SqlFilter;
import br.com.joaquimsn.querysearch.beans.PageResult;
import br.com.joaquimsn.querysearch.beans.SearchConfig;
import br.com.joaquimsn.querysearch.data.SearchRepository;

public interface SearchService extends Serializable {

	public default PageResult search(final JpqlFilter filter, SearchConfig searchConfig) {
		Objects.requireNonNull(filter, "jpqlFilter is required");
		Objects.requireNonNull(searchConfig, "SearchConfig is required");

		try {
			return getRepository().search(filter, searchConfig);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default PageResult search(final SqlFilter filter, SearchConfig searchConfig) {
		Objects.requireNonNull(filter, "SqlFilter is required");
		Objects.requireNonNull(searchConfig, "SearchConfig is required");

		try {
			return getRepository().search(filter, searchConfig);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default List<?> search(final JpqlFilter filter) throws RuntimeException {
		Objects.requireNonNull(filter, "jpqlFilter is required");

		try {
			return getRepository().search(filter);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public default List<?> search(final SqlFilter filter) throws RuntimeException {
		Objects.requireNonNull(filter, "sqlFilter is required");

		try {
			return getRepository().search(filter);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
	}

	public abstract SearchRepository getRepository();
}
