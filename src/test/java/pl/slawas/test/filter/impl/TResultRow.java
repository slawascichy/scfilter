package pl.slawas.test.filter.impl;

import pl.slawas.filter.ResultRow;
import pl.slawas.test.entities.TEntity;

public class TResultRow extends ResultRow<TEntity> {

	private static final long serialVersionUID = 3010622803726455166L;

	public TResultRow() {
		super();
	}

	/**
	 * @param docNum
	 * @param docScore
	 */
	public TResultRow(int docNum, float docScore) {
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
