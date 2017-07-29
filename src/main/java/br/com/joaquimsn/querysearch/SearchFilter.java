package com.github.querysearch;

import java.util.Map;

public interface SearchFilter {
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

	public default SortType defaultSortType() {
		return SortType.ASC;
	}
}
