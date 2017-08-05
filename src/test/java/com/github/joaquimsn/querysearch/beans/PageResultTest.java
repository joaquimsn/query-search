package com.github.joaquimsn.querysearch.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.joaquimsn.querysearch.beans.PageResult;

public class PageResultTest {
	
	@Test(expected = NullPointerException.class)
	public void throwNullPointExceptionIfTotalCountIsNull() {
		PageResult.of(null, new ArrayList<>(), 1);
	}
	
	@Test
	public void useDefaultPageSize() {
		PageResult result = PageResult.of(BigDecimal.ONE, getDataMock(), 0);
		Assert.assertEquals(result.getMaxPageSize(), PageResult.DEFAULT_MAX_PAGE_SIZE);
	}
	
	@Test
	public void objectPageResultShouldCustomPageSoze() {
		int customPageSize = 20;
		PageResult result = PageResult.of(BigDecimal.ONE, getDataMock(), customPageSize);
		Assert.assertEquals(result.getMaxPageSize(), customPageSize);
	}
	
	@Test
	public void totalCountShouldSameOfBuildObject() {
		PageResult result = PageResult.of(BigDecimal.TEN, getDataMock(), 0);
		Assert.assertEquals(result.getTotalCount(), BigDecimal.TEN);
	}
	
	@Test(expected = RuntimeException.class)
	public void dataIsRequiredIfTotalCountIsGreaterThanZero() {
		PageResult.of(BigDecimal.TEN, new ArrayList<>(), 0);
	}
	
	@Test
	public void dataIsRequired() {
		PageResult result = PageResult.of(BigDecimal.TEN, getDataMock(), 0);
		Assert.assertArrayEquals(result.getData().toArray(), getDataMock().toArray());
	}
	
	private List<String> getDataMock() {
		return Arrays.asList(new String[] {"A", "B", "C"});
	}
}
