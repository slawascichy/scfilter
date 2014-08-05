package pl.slawas.filter;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.paging.PagedResultSupport;
import pl.slawas.paging.PagingParams;
import pl.slawas.paging.ResultSupport;
import pl.slawas.paging._IPagedQuery;
import pl.slawas.paging._IResultInfo;

/**
 * 
 * QueryResponse abstrakcja odpowiadająca odpowiedzi wyszukiwacza
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 * @param <RowObj>
 *            klasa odpowiadająca oryginalnemu wierszowi wyniku zapytania
 * @param <Row>
 *            reprezentacja wiersza.
 * @param <Entity>
 *            klasa encji, której dotyczy wyszukiwanie
 */
public abstract class QueryResponse<RowObj, Row extends ResultRow<RowObj>, Entity>
		extends PagedResultSupport<Row> implements _IResultInfo {

	private static final long serialVersionUID = -5210221106589109534L;

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass().getName());

	/**
	 * 
	 * @param pagingParams
	 * @param result
	 * @param query
	 * @param checkPagingParamRestrictions
	 */
	public QueryResponse(PagingParams pagingParams, ResultSupport<Row> result,
			_IPagedQuery<Row> query, boolean checkPagingParamRestrictions) {
		super((pagingParams.isReadOnly() ? pagingParams.copy() : pagingParams),
				result, query, checkPagingParamRestrictions);
	}

	/**
	 * @return the results
	 */
	public abstract Collection<Row> getResults();

	public Long getAbsoluteFirstRowPosition() {
		return this.result.getAbsoluteFirstRowPosition();
	}

	public Long getEndPosition() {
		return this.result.getEndPosition();
	}

	public Long getFirstRowPosition() {
		return this.result.getFirstRowPosition();
	}

	public Integer getResultMaxPages() {
		return this.result.getResultMaxPages();
	}

	public Long getStartPosition() {
		return this.result.getStartPosition();
	}

	/* Overridden (non-Javadoc) */
	public Long getExecutionTime() {
		return this.result.getExecutionTime();
	}

}
