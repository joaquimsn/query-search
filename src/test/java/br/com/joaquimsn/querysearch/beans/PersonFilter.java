package br.com.joaquimsn.querysearch.beans;

import br.com.joaquimsn.querysearch.AbstractJpqlSearchFilter;
import br.com.joaquimsn.querysearch.SortType;

public class PersonFilter extends AbstractJpqlSearchFilter  {
	private static final long serialVersionUID = 3926878146414338882L;

	@Override
	public String queryFilter() {
		return "SELECT p FROM Person p";
	}

	@Override
	public String queryCount() {
		return "SELECT COUNT(*) FROM Person p";
	}
	
	@Override
	public String defaultOrderBy() {
		return "name";
	}
	
	@Override
	public SortType defaultSortType() {
		return SortType.DESC;
	}
}
