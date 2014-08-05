package pl.slawas.test.filter.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.beans.SortParams;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;
import pl.slawas.paging.ResultMessage;
import pl.slawas.paging._IPagedQuery;
import pl.slawas.test.entities.TIndex;
import pl.slawas.test.mock.providers.TEntityMockProvider;

public class TQuery implements _IPagedQuery<TResultRow> {

	private static final long serialVersionUID = -402776357435081923L;

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass());

	private final TSearchQueryClause queryClause;

	private final Enumeration<TIndex> indexElements;

	private final PagingParams pagingParams;

	private final List<SortParams> sortParams;

	private List<TResultRow> serachResult = new ArrayList<TResultRow>();

	private boolean newSearch = true;

	public TQuery(Enumeration<TIndex> indexElements,
			TSearchQueryClause queryClause, PagingParams pagingParams,
			List<SortParams> sortParams) {
		super();
		this.queryClause = queryClause;
		this.indexElements = indexElements;
		this.pagingParams = pagingParams;
		this.sortParams = sortParams;
	}

	public TQueryResponse getPagedResult() {
		return this.getPagedResult(null);
	}

	public TQueryResponse getPagedResult(Page page) {
		if (page != null)
			this.pagingParams.setPage(page);

		TResult r = this.execute();
		logger.debug("\n{}", r.toString());
		return new TQueryResponse(pagingParams, r, this);
	}

	public void setPage(Page page) {
		this.pagingParams.setPage(page);
	}

	public TResult execute() {

		/** rozmiar rezultatu */
		Long resultSize = 0L + (serachResult != null ? serachResult.size() : 0);
		/** numer pierwszej pozycji na stronie */
		Long startPosition = pagingParams.getPage().getFirstRowNumber();
		/** numer ostatniej pozycji na stronie */
		Long endPosition = pagingParams.getPage().getLastRowNumber();

		Long startTime = Calendar.getInstance().getTimeInMillis();
		if (newSearch) {
			while (indexElements.hasMoreElements()) {
				TIndex row = indexElements.nextElement();
				boolean searchLogicResult = true;
				if (queryClause != null) {
					for (TQueryCondition clause : queryClause.getQueryClause()
							.getClauseConditions()) {
						String rowValue = TEntityMockProvider.Fields.valueOf(
								clause.getFieldName()).getValue(row);
						String searchedValue = clause.getValues()[0];
						switch (clause.getOpType()) {
						case EQ:
							searchLogicResult = searchLogicResult
									&& rowValue.equalsIgnoreCase(searchedValue);
							logger.trace("Czy rowne? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						case GEQ:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) >= 0);
							logger.trace(
									"Czy wieksze lub rowne? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						case GT:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) > 0);
							logger.trace("Czy wieksze od? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						case LEQ:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) <= 0);
							logger.trace(
									"Czy mniejsze lub rowne? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						case LT:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) < 0);
							logger.trace("Czy mniejsze od? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						case RSE:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) > 0)
									&& (rowValue.compareToIgnoreCase(clause
											.getValues()[1]) < 0);
							logger.trace(
									"Czy zawarte ? ['{}', <'{}', '{}'>] : {}",
									new Object[] { rowValue, searchedValue,
											clause.getValues()[1],
											searchLogicResult });
							break;
						case RSI:
							searchLogicResult = searchLogicResult
									&& (rowValue
											.compareToIgnoreCase(searchedValue) >= 0)
									&& (rowValue.compareToIgnoreCase(clause
											.getValues()[1]) <= 0);
							logger.trace(
									"Czy zawarte ? ['{}', ['{}', '{}']] : {}",
									new Object[] { rowValue, searchedValue,
											clause.getValues()[1],
											searchLogicResult });
							break;
						case WS:
							boolean isStarAtStart = searchedValue
									.startsWith("*");
							boolean isStarAtEnd = searchedValue.endsWith("*");
							if (isStarAtStart && isStarAtEnd) {
								searchLogicResult = searchLogicResult
										&& rowValue.toLowerCase().contains(
												searchedValue.toLowerCase());
							} else if (isStarAtEnd) {
								searchLogicResult = searchLogicResult
										&& rowValue.toLowerCase().startsWith(
												searchedValue.toLowerCase());
							} else if (isStarAtStart) {
								searchLogicResult = searchLogicResult
										&& rowValue.toLowerCase().endsWith(
												searchedValue.toLowerCase());
							} else {
								searchLogicResult = searchLogicResult
										&& rowValue
												.equalsIgnoreCase(searchedValue);
							}
							logger.trace("Czy pelny tekst? ['{}', '{}'] : {}",
									new Object[] { rowValue, searchedValue,
											searchLogicResult });
							break;
						default:
							searchLogicResult = false;
							break;
						}
					}
				}

				if (searchLogicResult) {
					TResultRow res = new TResultRow(resultSize, 1);
					Map<String, Object> fields = new HashMap<String, Object>();
					for (TEntityMockProvider.Fields f : TEntityMockProvider.Fields
							.values()) {
						fields.put(f.toString(), f.getValue(row));
					}
					res.setFields(fields);
					serachResult.add(res);
					resultSize++;
				}
			}
		}

		/* obsluga sortowania */
		if (sortParams != null && !sortParams.isEmpty()) {
			for (SortParams sortParam : sortParams) {
				if (StringUtils.isNotBlank(sortParam.getSortParam())) {
					TEntityMockProvider.Fields sortColumn = TEntityMockProvider.Fields
							.valueOf(sortParam.getSortParam());
					for (TResultRow sr : serachResult) {
						switch (sortColumn) {
						case id:
							sr.setSortField(StringUtils.isNotBlank((String) sr
									.getFields().get("id")) ? (String) sr
									.getFields().get("id") : "");
							break;
						case name:
							sr.setSortField(StringUtils.isNotBlank((String) sr
									.getFields().get("name")) ? (String) sr
									.getFields().get("name") : "");
							break;
						case price:
							sr.setSortField(StringUtils.isNotBlank((String) sr
									.getFields().get("price")) ? (String) sr
									.getFields().get("price") : "");
							break;
						case date:
							sr.setSortField(StringUtils.isNotBlank((String) sr
									.getFields().get("date")) ? (String) sr
									.getFields().get("date") : "");
							break;
						case user:
							sr.setSortField(StringUtils.isNotBlank((String) sr
									.getFields().get("user")) ? (String) sr
									.getFields().get("user") : "");
							break;
						default:
							break;
						}
					}
					if (sortParam.isAsc()) {
						Collections.sort(serachResult,
								new Comparator<TResultRow>() {
									public int compare(TResultRow d1,
											TResultRow d2) {
										String sort1 = d1.getSortField();
										String sort2 = d2.getSortField();
										if (sort1.equals(sort2)) {
											return 0;
										} else {
											return (sort1
													.compareToIgnoreCase(sort2));
										}
									}
								});
					} else {
						Collections.sort(serachResult,
								new Comparator<TResultRow>() {
									public int compare(TResultRow d1,
											TResultRow d2) {
										String sort1 = d1.getSortField();
										String sort2 = d2.getSortField();
										if (sort1.equals(sort2)) {
											return 0;
										} else {
											return 0 - sort1
													.compareToIgnoreCase(sort2);
										}
									}
								});
					}
				}
			}
		}
		Long endTime = Calendar.getInstance().getTimeInMillis();

		/** pierwsza pozycja rezultatu */
		Long firstRowPosition = 0L;
		/** ostatnia pozycja rezultatu */
		Long lastRowPosition = resultSize - 1;

		/* obsluga stronicowania */
		List<TResultRow> pagedResult = new ArrayList<TResultRow>();
		int pageCounter = 0;
		int rowCounter = 0;
		Long firstNumberOfRow = pagingParams.getCursorOfPage();
		Long lastNumberOfRow = firstNumberOfRow + pagingParams.getPageSize();

		for (TResultRow qr : serachResult) {
			if (rowCounter >= firstNumberOfRow && rowCounter < lastNumberOfRow) {
				pagedResult.add(qr);
				pageCounter++;
			}
			rowCounter++;
		}
		startPosition = firstNumberOfRow + 1;
		endPosition = firstNumberOfRow + pageCounter;

		ResultMessage message = ResultMessage.ALL;
		if (resultSize > endPosition)
			message = ResultMessage.FRAGMENT;
		if (resultSize == 0)
			message = ResultMessage.NO_DATA_FOUND;

		return new TResult(startPosition, endPosition, firstRowPosition,
				resultSize, lastRowPosition, pagedResult, message,
				PagingParams.DEFAULT_PAGING_OFFSET, pagingParams.getMaxPages(),
				endTime - startTime);

	}

	/**
	 * @param newSearch
	 *            the newSearch to set
	 */
	public void setNewSearch(boolean newSearch) {
		this.newSearch = newSearch;
	}

}
