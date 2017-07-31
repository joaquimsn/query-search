package com.github.querysearch;

import java.util.Objects;

public enum SortType {
	DESC("DESC"),
	ASC("ASC");
	
	private String sortOrder;

	private SortType(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/**
	 * @param value sort type for order by
	 * @return  SortType if value is invalid then return default @see SortType#ASC
	 */
	public static SortType enumByValue(String value) {
		if (Objects.isNull(value) || (!SortType.ASC.getSortOrder().equals(value.toUpperCase()) && !SortType.DESC.getSortOrder().equals(value.toUpperCase()))) {
			return SortType.ASC;			
		}
		
		return SortType.valueOf(value.toUpperCase());
	}
	
	public String getSortOrder() {
		return sortOrder;
	}
}