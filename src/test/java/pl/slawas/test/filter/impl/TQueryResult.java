package pl.slawas.test.filter.impl;

import pl.slawas.filter.QueryResult;

public class TQueryResult extends QueryResult<TQueryResult> {

	private static final long serialVersionUID = 3010622803726455166L;

	public TQueryResult() {
		super();
	}

	/**
	 * @param docNum
	 * @param docScore
	 */
	public TQueryResult(int docNum, float docScore) {
		super(docNum, docScore);
	}

	private String sortField;

	/**
	 * @return the sortField
	 */
	String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField
	 *            the sortField to set
	 */
	void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
