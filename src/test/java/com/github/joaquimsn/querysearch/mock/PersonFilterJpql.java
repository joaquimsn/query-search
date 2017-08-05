package com.github.joaquimsn.querysearch.mock;

import com.github.joaquimsn.querysearch.AbstractJpqlSearchFilter;

public class PersonFilterJpql extends AbstractJpqlSearchFilter  {
	private static final long serialVersionUID = 3926878146414338882L;
	
	public void setInteger(Integer age) {
		addAndFilter(" p.age = :age");
		addParameter("age", age);
	}
	
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
}
