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
	 */
	public TResult(int startPosition, int endPosition,
			Integer firstRowPosition, int resultSize, Integer lastRowPosition,
			Collection<TResultRow> result, ResultMessage message,
			int absoluteFirstRowPosition, int resultMaxPages) {
		super(startPosition, endPosition, firstRowPosition, resultSize,
				lastRowPosition, result, message, absoluteFirstRowPosition,
				resultMaxPages);
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
