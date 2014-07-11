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
 * QueryResponse abstrakacja odpowiadająca odpowiedzi wyszukiwacza
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.4 $
 * 
 * @param <Row>
 *            klasa obiektu reprezentującego wiersz zwracanego wyniku
 */
public abstract class QueryResponse<Row extends ResultRow<Entity>, Entity>
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

	public int getAbsoluteFirstRowPosition() {
		return this.result.getAbsoluteFirstRowPosition();
	}

	public int getEndPosition() {
		return this.result.getEndPosition();
	}

	public int getFirstRowPosition() {
		return this.result.getFirstRowPosition();
	}

	public int getResultMaxPages() {
		return this.result.getResultMaxPages();
	}

	public int getStartPosition() {
		return this.result.getStartPosition();
	}

	/* Overridden (non-Javadoc) */
	public Long getExecutionTime() {
		return this.result.getExecutionTime();
	}

}
