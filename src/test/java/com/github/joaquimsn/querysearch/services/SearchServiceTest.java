package com.github.joaquimsn.querysearch.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.joaquimsn.querysearch.JpqlFilter;
import com.github.joaquimsn.querysearch.SqlFilter;
import com.github.joaquimsn.querysearch.beans.PageResult;
import com.github.joaquimsn.querysearch.beans.SearchConfig;
import com.github.joaquimsn.querysearch.data.SearchRepository;

@RunWith(value = MockitoJUnitRunner.class)
public class SearchServiceTest {
	
	@Mock
	private SearchRepository repository;
	
	private SearchService searchService;
	
	@Before
	public void setup() {
		searchService = new SearchServiceImpl(repository);
	}
	
	@Test
	public void searchSqlShouldReturnResult() {
		List<?> list = searchService.search(Mockito.mock(SqlFilter.class));
		Assert.assertTrue(Objects.nonNull(list));
	}
	
	@Test
	public void paginationSqlShouldReturnResult() {
		PageResult pageResultMock = PageResult.of(BigDecimal.ZERO, new ArrayList<>(), 1);
		SqlFilter filterMock = Mockito.mock(SqlFilter.class);
		SearchConfig configMock = Mockito.mock(SearchConfig.class);
		Mockito.when(repository.search(filterMock, configMock)).thenReturn(pageResultMock);
		
		PageResult result = searchService.search(filterMock, configMock);
		Assert.assertTrue(Objects.nonNull(result));
	}
	
	@Test
	public void searchJpqlShouldReturnResult() {
		List<?> list = searchService.search(Mockito.mock(JpqlFilter.class));
		Assert.assertTrue(Objects.nonNull(list));
	}
	
	@Test
	public void paginationShouldReturnResult() {
		PageResult pageResultMock = PageResult.of(BigDecimal.ZERO, new ArrayList<>(), 1);
		JpqlFilter filterMock = Mockito.mock(JpqlFilter.class);
		SearchConfig configMock = Mockito.mock(SearchConfig.class);
		Mockito.when(repository.search(filterMock, configMock)).thenReturn(pageResultMock);
		
		PageResult result = searchService.search(filterMock, configMock);
		Assert.assertTrue(Objects.nonNull(result));
	}
}
