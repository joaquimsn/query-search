package br.com.joaquimsn.querysearch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractSearchFilter implements SearchFilter, Serializable {
	private static final long serialVersionUID = -4980351803472614569L;
	
	private final StringBuilder filters = new StringBuilder();
	private final Map<String, Object> parameters = new HashMap<>();
	
	
	protected void addParameter(String name, Object value) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(value);
		
		this.parameters.put(name, value);
	}
	
	protected void addAndFilter(String conditional) {
		Objects.requireNonNull(conditional);
		filters.append(filters.length() > 0 ? " AND " : " WHERE ").append(conditional).append(" ");
	}
	
	protected void addOrFilter(String conditional) {
		Objects.requireNonNull(conditional);
		filters.append(filters.length() > 0 ? " OR " : " WHERE ").append(conditional).append(" ");
	}

	@Override
	public String filters() {
		return filters.toString();
	}
	
	@Override
	public Map<String, Object> parameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "AbstractSearchFilter [filters=" + filters + ", parameters=" + parameters + "]";
	}
}
