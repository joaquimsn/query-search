package br.com.joaquimsn.querysearch;

import java.util.Map;

public interface SearchFilter {
	static final String ORDER_BY_DESC = "DESC";
	static final String ORDER_BY_ASC = "ASC";
	
	public String queryFilter();

	public String queryCount();
	
	public Map<String, Object> parameters();

	public String filters();

	public default String buildQuery() {
		return queryFilter().concat(filters());
	}

	public default String buildQueryCount() {
		return queryCount().concat(filters());
	}

	public default String defaultOrderBy() {
		return null;
	}

	public default String defaultSortType() {
		return ORDER_BY_ASC;
	}
}
