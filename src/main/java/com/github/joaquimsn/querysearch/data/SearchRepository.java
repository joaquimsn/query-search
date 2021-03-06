package com.github.joaquimsn.querysearch.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.github.joaquimsn.querysearch.JpqlFilter;
import com.github.joaquimsn.querysearch.SearchFilter;
import com.github.joaquimsn.querysearch.SqlFilter;
import com.github.joaquimsn.querysearch.beans.PageResult;
import com.github.joaquimsn.querysearch.beans.SearchConfig;

/**
 * @author joaquim.sneto
 * Created Jul 11, 2017 - 8:36:31 PM
 */
public interface SearchRepository {
	
	public default PageResult search(JpqlFilter filter, SearchConfig searchConfig) {
		Query querySearch = setParameters(getQueryJpql(makeOrderBy(filter, searchConfig)), filter.parameters());
		Query queryCount = setParameters(getQueryJpql(filter.buildQueryCount()), filter.parameters());

		List<?> data = executeSearch(querySearch, searchConfig);
		BigDecimal totalCount = executeCount(queryCount);

		return PageResult.of(totalCount, data, searchConfig.getMaxPageSize());
	}

	/**
	 * @param filter an object implementation of @see SearchFilter
	 * @return List with all data find
	 * @throws PersistenceException from Query.getResultList()
	 * @author joaquim.sneto
	 * Created Jul 12, 2017 - 10:46:49 AM
	 */
	public default List<?> search(JpqlFilter filter) {
		Query querySearch = setParameters(getQueryJpql(filter.buildQuery()), filter.parameters());

		return querySearch.getResultList();
	}
	
	/**
	 * @param filter an object implementation of @see SearchFilter
	 * @return List with all data find
	 * @throws PersistenceException from Query.getResultList()
	 * @author joaquim.sneto
	 * Created Jul 27, 2017 - 10:42:49 AM
	 */
	public default List<?> search(SqlFilter filter) {
		Query querySearch = setParameters(getQueryNativeQuery(filter.buildQuery()), filter.parameters());
		List<?> data = filter.buildTemplateResult().create(querySearch).build().getResultList();
		
		return data;
	}

	public default PageResult search(SqlFilter filter, SearchConfig searchConfig) {
		Query querySearch = setParameters(getQueryNativeQuery(makeOrderBy(filter, searchConfig)), filter.parameters());
		Query queryCount = setParameters(getQueryNativeQuery(filter.buildQueryCount()), filter.parameters());

		BigDecimal totalCount = executeCount(queryCount);
		List<?> data = executeSearch(filter.buildTemplateResult().create(querySearch).build(), searchConfig);

		return PageResult.of(totalCount, data, searchConfig.getMaxPageSize());
	}

	public default Query setParameters(Query query, Map<String, Object> parameters) {
		if (Objects.nonNull(parameters)) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return query;
	}

	public default String makeOrderBy(SearchFilter filter, SearchConfig searchConfig) {
		return filter.buildQuery().concat(searchConfig.getOrderByCondition(filter));
	}

	public default BigDecimal executeCount(Query query) {
		Object result = query.getSingleResult();

		if (result instanceof BigDecimal) {
			return (BigDecimal) result;
		}

		if (result instanceof Integer) {
			return BigDecimal.valueOf(((Integer) result).longValue());
		}

		if (result instanceof BigInteger) {
			return BigDecimal.valueOf(((BigInteger) result).longValue());
		}

		return BigDecimal.valueOf((Long) result);
	}

	public default List<?> executeSearch(Query query, SearchConfig searchConfig) {
		return query.setFirstResult(searchConfig.getStartResult())
				.setMaxResults(searchConfig.getMaxPageSize())
				.getResultList();
	}

	/**
	 * Required implementation, use EntityManager#createQuery(jpql) for
	 * build @see Query
	 * 
	 * @param jpql Query JPQL see more in <a href="http://docs.oracle.com/html/E13946_04/ejb3_langref.html">JPA docs</a>
	 * @return  Query
	 * @author joaquim.sneto
	 * Created Jul 12, 2017 - 10:47:56 AM
	 */
	public Query getQueryJpql(String jpql);

	/**
	 * Required implementation, use EntityManager#createNativeQuery(sqlQuery) for 
	 * build @see Query
	 * 
	 * @param sqlQuery Query JPQL see more in <a href="http://docs.oracle.com/html/E13946_04/ejb3_langref.html">JPA docs</a>
	 * @return  Query
	 * @author joaquim.sneto
	 * Created Jul 12, 2017 - 10:47:56 AM
	 */
	public Query getQueryNativeQuery(String sqlQuery);
}
