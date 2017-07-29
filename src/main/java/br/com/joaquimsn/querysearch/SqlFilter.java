package com.github.querysearch;

import com.github.querysearch.template.TemplateObjectQueryConstruct;

public interface SqlFilter extends SearchFilter {

	public TemplateObjectQueryConstruct buildTemplateResult();
}
