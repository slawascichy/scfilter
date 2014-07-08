package pl.slawas.test.filter.impl;

import java.util.ArrayList;
import java.util.List;

import pl.slawas.filter.QueryResponse;
import pl.slawas.filter.QueryResult;
import pl.slawas.paging.PagingParams;

public class TQueryResponse extends QueryResponse<TQueryResult> {

	private static final long serialVersionUID = 5768035476121504692L;

	public TQueryResponse(PagingParams pagingParams, TResult results,
			TQuery query) {
		super(pagingParams, results, query, true);
	}

	@Override
	public List<QueryResult<TQueryResult>> getResults() {
		List<QueryResult<TQueryResult>> out = new ArrayList<QueryResult<TQueryResult>>();
		for (TQueryResult o : this.result.getResult()) {
			out.add(o);
		}
		return out;
	}

}
