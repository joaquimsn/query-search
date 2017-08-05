package com.github.joaquimsn.querysearch.data;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.joaquimsn.querysearch.BaseJpaTest;
import com.github.joaquimsn.querysearch.beans.PageResult;
import com.github.joaquimsn.querysearch.beans.SearchConfig;
import com.github.joaquimsn.querysearch.mock.AddressFilterSql;
import com.github.joaquimsn.querysearch.mock.PersonFilterJpql;

public class SearchRepositoryTest extends BaseJpaTest {
	private PersonFilterJpql personFilter;
	private SearchRepositoryImpl searchRepository;
	
	@Before
	public void initializeFilter() {
		personFilter = new PersonFilterJpql();
		personFilter.toString();
		searchRepository = new SearchRepositoryImpl(this.entityManager);
	}
	
	@Test
	public void searchForAgeShouldReturnResult() {
		personFilter.setInteger(20);
		List<?> list = searchRepository.search(personFilter);
		Assert.assertTrue(!list.isEmpty());
	}
	
	@Test
	public void paginationShouldReturnSingleResult() {
		PageResult result = searchRepository.search(personFilter, SearchConfig.of(3, 2));
		Assert.assertEquals(result.getData().size(), 1);
	}
	
	@Test
	public void searchWithSqlShouldReturnResult() {
		List<?> list = searchRepository.search(new AddressFilterSql());
		Assert.assertTrue(!list.isEmpty());
	}
	
	@Test
	public void paginationWithSqlShouldReturnSingleResult() {
		PageResult result = searchRepository.search(new AddressFilterSql(), SearchConfig.of(1, 2));
		Assert.assertEquals(result.getData().size(), 1);
	}
}
