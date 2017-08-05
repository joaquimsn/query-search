package com.github.joaquimsn.querysearch.services;

import com.github.joaquimsn.querysearch.data.SearchRepository;

public class SearchServiceImpl implements SearchService {
	private static final long serialVersionUID = -7968284347534166829L;
	
	private SearchRepository repository;

	public SearchServiceImpl(SearchRepository repository) {
		this.repository = repository;
	}

	@Override
	public SearchRepository getRepository() {
		return repository;
	}
}
