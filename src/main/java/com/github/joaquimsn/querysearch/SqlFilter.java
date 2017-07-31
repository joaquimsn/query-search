package com.github.joaquimsn.querysearch;

import com.github.joaquimsn.querysearch.template.TemplateObjectQueryConstruct;

public interface SqlFilter extends SearchFilter {

	public TemplateObjectQueryConstruct buildTemplateResult();
}
