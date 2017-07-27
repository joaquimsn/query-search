package br.com.joaquimsn.querysearch;

import br.com.joaquimsn.querysearch.template.TemplateObjectQueryConstruct;

public interface SqlFilter extends SearchFilter {

	public TemplateObjectQueryConstruct buildTemplateResult();
}
