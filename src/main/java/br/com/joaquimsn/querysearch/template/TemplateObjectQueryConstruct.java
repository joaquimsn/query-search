package com.github.querysearch.template;

import javax.persistence.Query;

public interface TemplateObjectQueryConstruct {
	public TemplateObjectQueryResult create(Query query);
}
