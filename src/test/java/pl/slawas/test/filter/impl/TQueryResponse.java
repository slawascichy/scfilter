package pl.slawas.test.filter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.slawas.filter.QueryResponse;
import pl.slawas.paging.PagingParams;
import pl.slawas.test.entities.TEntity;

public class TQueryResponse extends QueryResponse<TResultRow, TEntity> {

	private static final long serialVersionUID = 5768035476121504692L;

	public TQueryResponse(PagingParams pagingParams, TResult results,
			TQuery query) {
		super(pagingParams, results, query, true);
	}

	/* Overridden (non-Javadoc) */
	@Override
	public Collection<TResultRow> getResults() {

		List<TResultRow> out = new ArrayList<TResultRow>();
		for (TResultRow o : this.result.getResult()) {
			out.add(o);
		}
		return out;

	}

}
