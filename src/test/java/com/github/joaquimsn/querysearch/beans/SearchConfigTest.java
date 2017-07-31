package com.github.joaquimsn.querysearch.beans;

import org.junit.Assert;
import org.junit.Test;

import com.github.joaquimsn.querysearch.SortType;

public class SearchConfigTest {
	
	@Test(expected = NullPointerException.class)
	public void throwNullPointExceptionIfSortTypeIsNull() {
		SearchConfig.of(1, 1, "orderBy", null);
	}
	
	@Test(expected = NullPointerException.class)
	public void throwNullPointExceptionIfOrderByIsNull() {
		SearchConfig.of(1, 1, null, SortType.ASC);
	}
	
	@Test
	public void useDefaultMaxPageSizeIfLessThenZero() {
		SearchConfig searchConfig = SearchConfig.of(1, -1);
		Assert.assertEquals(searchConfig.getMaxPageSize(), PageResult.DEFAULT_MAX_PAGE_SIZE);
	}
	
	@Test
	public void useDefaultMaxPageSizeIfEqualsZero() {
		SearchConfig searchConfig = SearchConfig.of(1, 0);
		Assert.assertEquals(searchConfig.getMaxPageSize(), PageResult.DEFAULT_MAX_PAGE_SIZE);
	}
	
	@Test
	public void forMaxPageSizeGreaterThenOne() {
		SearchConfig searchConfig = SearchConfig.of(1, Integer.MAX_VALUE);
		Assert.assertEquals(searchConfig.getMaxPageSize(), Integer.MAX_VALUE);
	}
	
	@Test
	public void firstPageIfPageIfLessThenZero() {
		SearchConfig searchConfig = SearchConfig.of(-1);
		Assert.assertEquals(searchConfig.getPage(), 1);
	}
	
	@Test
	public void firstPageIfPageEqualsZero() {
		SearchConfig searchConfig = SearchConfig.of(0);
		Assert.assertEquals(searchConfig.getPage(), 1);
	}
	
	@Test
	public void forPageGraterThenOne() {
		SearchConfig searchConfig = SearchConfig.of(5);
		Assert.assertEquals(searchConfig.getPage(), 5);
	}
	
	@Test
	public void forPageOneTheStartResultEqualsZero() {
		SearchConfig searchConfig = SearchConfig.of(1);
		Assert.assertEquals(searchConfig.getStartResult(), 0);
	}
	
	@Test
	public void startResultCalculeted() {
		SearchConfig searchConfig = SearchConfig.of(3, 30);
		Assert.assertEquals(searchConfig.getStartResult(), 60);
	}
	
	@Test
	public void useDefaultOrderBy() {
		PersonFilter personFilter = new PersonFilter();
		SearchConfig searchConfig = SearchConfig.of(1, PageResult.DEFAULT_MAX_PAGE_SIZE);
		Assert.assertNotEquals(searchConfig.getOrderByCondition(personFilter), "");
	}
	
	@Test
	public void useOrderByOfConfig() {
		PersonFilter personFilter = new PersonFilter();
		SearchConfig searchConfig = SearchConfig.of(1, PageResult.DEFAULT_MAX_PAGE_SIZE, "birthday", SortType.ASC);
		Assert.assertNotEquals(searchConfig.getOrderByCondition(personFilter), "");
	}
}
