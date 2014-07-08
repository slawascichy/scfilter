package pl.slawas.test.filter.impl;

import pl.slawas.filter.Request;
import pl.slawas.filter.SearchQueryClause;
import pl.slawas.filter.beans.SortParams;
import pl.slawas.paging.PagingParams;

public class TRequest extends
		Request<TRequest, TSearchQueryClause, TQueryCondition> {

	private boolean newSearch = true;

	private boolean newQuery = true;

	public TRequest(String indexName,
			SearchQueryClause<TSearchQueryClause, TQueryCondition> queryClause,
			PagingParams pagingParams) {
		super(indexName, queryClause, pagingParams);
	}

	private SortParams sortParams;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3281923509908671296L;

	@Override
	public TRequest getRequest() {
		return this;
	}

	@Override
	public SortParams getSortParams() {
		return this.sortParams;
	}

	@Override
	public void setSortParams(SortParams sortParams) {
		this.sortParams = sortParams;
	}

	/**
	 * @return the newSearch
	 */
	public boolean isNewSearch() {
		return newSearch;
	}

	/**
	 * @param newSearch
	 *            the newSearch to set
	 */
	public void setNewSearch(boolean newSearch) {
		this.newSearch = newSearch;
	}

	/**
	 * @return the newQuery
	 */
	public boolean isNewQuery() {
		return newQuery;
	}

	/**
	 * @param newQuery
	 *            the newQuery to set
	 */
	public void setNewQuery(boolean newQuery) {
		this.newQuery = newQuery;
	}

	@Override
	public String toString() {
		return "TRequest [newQuery=" + newQuery + ", newSearch=" + newSearch
				+ ", sortParams=" + sortParams + "]";
	}

}
