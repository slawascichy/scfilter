package pl.slawas.test.filter.impl;

import java.util.Collection;

import pl.slawas.paging.ResultMessage;
import pl.slawas.paging.ResultSupport;

public class TResult extends ResultSupport<TResultRow> {

	private static final long serialVersionUID = -4030797784607063255L;

	/**
	 * @param startPosition
	 *            numer pierwszej pozycji na stronie
	 * @param endPosition
	 *            numer ostatniej pozycji na stronie
	 * @param firstRowPosition
	 *            pierwsza pozycja rezultatu
	 * @param resultSize
	 *            rozmiar rezultatu
	 * @param lastRowPosition
	 *            ostatnia pozycja rezultatu
	 * @param result
	 *            lista wyników rezultatu
	 * @param message
	 *            komunikat związany z rezultatem ({@link ResultMessage})
	 * @param absoluteFirstRowPosition
	 *            absolutny pierwszy numer wiersza w wyniku zapytania
	 * @param resultMaxPages
	 *            predefiniowana maksymalna liczba stron w rezultacie.
	 * @param executionTime
	 *            czas wykonania zapytania (w milisekundach)
	 */
	public TResult(Long startPosition, Long endPosition, Long firstRowPosition,
			Long resultSize, Long lastRowPosition,
			Collection<TResultRow> result, ResultMessage message,
			Long absoluteFirstRowPosition, Integer resultMaxPages,
			Long executionTime) {
		super(startPosition, endPosition, firstRowPosition, resultSize,
				lastRowPosition, result, message, absoluteFirstRowPosition,
				resultMaxPages, executionTime);
	}

	@Override
	public String toString() {
		return "TResult [getAbsoluteFirstRowPosition()="
				+ getAbsoluteFirstRowPosition() + ", getEndPosition()="
				+ getEndPosition() + ", getFirstRowPosition()="
				+ getFirstRowPosition() + ", getLastRowPosition()="
				+ getLastRowPosition() + ", getMessage()=" + getMessage()
				+ ", getResultMaxPages()=" + getResultMaxPages()
				+ ", getResultSize()=" + getResultSize()
				+ ", getStartPosition()=" + getStartPosition()
				+ ", hasMoreResultRows()=" + hasMoreResultRows() + "]";
	}

}
