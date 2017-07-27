package br.com.joaquimsn.querysearch.beans;

import java.util.Objects;

import br.com.joaquimsn.querysearch.SearchFilter;

/**
 * @author joaquim.sneto
 * @Created Jul 11, 2017 - 8:35:46 PM
 */
public class SearchConfig {
	private int page;
	private int maxPageSize;
	private String orderBy;
	private String sortType;
	private boolean hasOrderBy = false;

	private SearchConfig(int page, int maxPageSize, String orderBy, String sortType) {
		Objects.requireNonNull(orderBy);
		Objects.requireNonNull(sortType);
		Objects.requireNonNull(sortType, "Parm method required");

		if (!sortType.toUpperCase().equals("ASC") && !sortType.toUpperCase().equals("DESC")) {
			throw new RuntimeException("method accepted ASC or DESC");
		}

		this.page = page;
		this.maxPageSize = maxPageSize;
		this.orderBy = orderBy;
		this.sortType = sortType;
		this.hasOrderBy = true;
	}

	private SearchConfig(int page, int maxPageSize) {
		this.page = page;
		this.maxPageSize = maxPageSize;
	}

	/**
	 * @param page current page number
	 * @param maxPageSize amount items per page
	 * @param orderBy 
	 * @param sortType "ASC" or "DESC"
	 * @return
	 * @autor joaquim.sneto
	 * @Created Jul 12, 2017 - 1:15:18 PM
	 */
	public static SearchConfig of(int page, int maxPageSize, String orderBy, String sortType) {
		return new SearchConfig((page <= 0 ? 1 : page), (maxPageSize <= 0 ? PageResult.DEFAULT_MAX_PAGE_SIZE : maxPageSize), orderBy, sortType);
	}

	/**
	 * Basic configuration with properties default maxPageSize = 10
	 * 
	 * @param page current page number
	 * @param orderBy 
	 * @param sortType "ASC" or "DESC"
	 * @return
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 8:30:40 PM
	 */
	public static SearchConfig of(int page, String orderBy, String sortType) {
		return new SearchConfig(page, PageResult.DEFAULT_MAX_PAGE_SIZE, orderBy, sortType);
	}

	/**
	 * Basic configuration without orderBy
	 * 
	 * @param page current page number
	 * @param maxPageSize amount items per page
	 * @return {@link SearchConfig}
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 7:16:08 PM
	 */
	public static SearchConfig of(int page, int maxPageSize) {
		return new SearchConfig((page <= 0 ? 1 : page), (maxPageSize <= 0 ? PageResult.DEFAULT_MAX_PAGE_SIZE : maxPageSize));
	}

	/**
	 * Basic configuration with properties default maxPageSize = 10 without
	 * orderBy
	 * 
	 * @param page current page number
	 * @return {@link SearchConfig}
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 7:16:08 PM
	 */
	public static SearchConfig of(int page) {
		return new SearchConfig((page <= 0 ? 1 : page), PageResult.DEFAULT_MAX_PAGE_SIZE);
	}

	/**
	 * Basic configuration with properties default maxPageSize = 10 and method =
	 * ASC
	 * 
	 * @param page current page number
	 * @param orderBy
	 * @return {@link SearchConfig}
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 7:16:08 PM
	 */
	public static SearchConfig of(int page, String orderBy) {
		return of(page, PageResult.DEFAULT_MAX_PAGE_SIZE, orderBy, SearchFilter.ORDER_BY_ASC);
	}

	/**
	 * @return currently page
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 8:34:26 PM
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return Max items per page
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 8:35:25 PM
	 */
	public int getMaxPageSize() {
		return maxPageSize;
	}

	/**
	 * @return the position of the first result to retrieve.
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 8:33:10 PM
	 */
	public int getStartResult() {
		return ((this.getPage() * this.getMaxPageSize()) - this.getMaxPageSize());
	}

	/**
	 * @param filter
	 * @return empty if not have orderBy
	 * @autor joaquim.sneto
	 * @Created Jul 11, 2017 - 8:31:26 PM
	 */
	public String getOrderByCondition(SearchFilter filter) {
		return hasOrderBy ? (" ORDER BY " + this.orderBy + " " + this.sortType)
				: (Objects.nonNull(filter.defaultOrderBy()) ? (" ORDER BY " + filter.defaultOrderBy() + " " + filter.defaultSortType()) : (""));
	}

	@Override
	public String toString() {
		return "SearchConfig [page=" + page + ", maxPageSize=" + maxPageSize + ", orderBy=" + orderBy + ", method=" + sortType + "]";
	}
}
