package pl.slawas.test.filter.impl;

import java.util.Enumeration;

import pl.slawas.filter.QueryResponse;
import pl.slawas.filter.Request;
import pl.slawas.filter.SearchQueryClause;
import pl.slawas.filter.Searcher;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;
import pl.slawas.test.entities.TIndex;
import pl.slawas.test.mock.providers.TEntityMockProvider;

public class TSearcher extends
		Searcher<TRequest, TSearchQueryClause, TQueryCondition, TQueryResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6408629388317879311L;

	private TSearchQueryClause queryClause;

	private TRequest request;

	private TQuery query;

	/**
	 * @param pagingParams
	 */
	public TSearcher() {
		super(new PagingParams(new Page()));
	}

	@Override
	public Request<TRequest, TSearchQueryClause, TQueryCondition> getRequest(
			String indexName,
			SearchQueryClause<TSearchQueryClause, TQueryCondition> queryClause) {
		if ((this.queryClause != null && !this.queryClause.equals(queryClause))
				|| this.request == null || this.queryClause == null) {
			logger.debug("[getRequest] Tworze nowe zapytanie: {}...",
					(this.queryClause != null && !this.queryClause
							.equals(queryClause)));

			this.queryClause = (TSearchQueryClause) queryClause;
			this.request = new TRequest(indexName, queryClause,
					getPagingParams());
			this.request.setNewQuery(true);
			this.request.setNewSearch(true);
		} else {
			logger.debug("[getRequest] Stare zapytanie...");
			this.request.setNewQuery(false);
			this.request.setNewSearch(false);
		}
		return this.request;
	}

	@Override
	public QueryResponse<TQueryResult> search(
			Request<TRequest, TSearchQueryClause, TQueryCondition> request) {

		TRequest rq = (TRequest) request;
		logger.debug("Request: {}", rq.toString());
		if (query == null || rq.isNewQuery()) {
			Enumeration<TIndex> indexElements = TEntityMockProvider.searchIndex
					.elements();
			logger.debug("Tworze nowe zapytanie...");
			query = new TQuery(indexElements, queryClause, getPagingParams(),
					rq.getSortParams());
		}
		query.setNewSearch(rq.isNewSearch());
		return query.getPagedResult(rq.getPagingParams().getPage());
	}

}
