package com.github.querysearch.beans;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PageResult {
	public static final int DEFAULT_MAX_PAGE_SIZE = 10;

	private final BigDecimal totalCount;
	private final List<?> data;
	private int pageSize;

	private PageResult(BigDecimal totalCount, List<?> data, int pageSize) {
		Objects.requireNonNull(totalCount);
		checkConsistencyBetweenTotalCounAndData(totalCount, data);
		
		this.totalCount = totalCount;
		this.data = data;
		this.pageSize = pageSize;
	}

	public static PageResult of(BigDecimal totalCount, List<?> data, int pageSize) {
		return new PageResult(totalCount, data, pageSize);
	}
	
	private void checkConsistencyBetweenTotalCounAndData(BigDecimal totalCount, List<?> data) {
		if (totalCount.longValue() > 0 && (Objects.isNull(data) || data.isEmpty())) {
			throw new RuntimeException("The totalCount is greater than 0, but data is empty");
		}
	}

	public BigDecimal getTotalCount() {
		return totalCount;
	}

	public List<?> getData() {
		return data;
	}

	public int getMaxPageSize() {
		return pageSize > 0 ? pageSize : DEFAULT_MAX_PAGE_SIZE;
	}

	@Override
	public String toString() {
		return "PageResult [totalCount=" + totalCount + ", data=" + data + ", pageSize=" + pageSize + "]";
	}
}
