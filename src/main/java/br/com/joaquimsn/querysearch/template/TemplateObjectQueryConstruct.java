package br.com.joaquimsn.querysearch.template;

import javax.persistence.Query;

public interface TemplateObjectQueryConstruct {
	public TemplateObjectQueryResult create(Query query);
}
