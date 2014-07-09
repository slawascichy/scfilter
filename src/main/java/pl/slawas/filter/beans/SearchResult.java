package pl.slawas.filter.beans;

import java.util.Collection;
import java.util.Enumeration;

import pl.slawas.filter.QueryResponse;
import pl.slawas.filter.ResultRow;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagedResultException;
import pl.slawas.paging.PagingParams;
import pl.slawas.paging.ResultSupport;
import pl.slawas.paging._IPagingInfo;

/**
 * 
 * SearchResult - obiekt rezultatu wyszukiwania
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.4 $
 * 
 * @param <Entity>
 */
public class SearchResult<Row extends ResultRow<Entity>, Entity> extends
		ResultSupport<Entity> implements _IPagingInfo {

	private static final long serialVersionUID = 8586577008473662383L;

	private final QueryResponse<Row, Entity> baseQueryResponse;

	/**
	 * 
	 * @param baseQueryResponse
	 *            wynik wyszukiwania, na podstwie, którego został zbudowany
	 *            rezultat
	 * @param list
	 *            lista obiektów stanowiąca rezultat wyszukiwania
	 */
	public SearchResult(QueryResponse<Row, Entity> baseQueryResponse,
			Collection<Entity> list) {
		super(baseQueryResponse.getStartPosition(), baseQueryResponse
				.getEndPosition(), baseQueryResponse.getFirstRowPosition(),
				baseQueryResponse.getResultSize(), baseQueryResponse
						.getLastRowPosition(), list, baseQueryResponse
						.getMessage(), baseQueryResponse
						.getAbsoluteFirstRowPosition(), baseQueryResponse
						.getResultMaxPages());
		this.baseQueryResponse = baseQueryResponse;

	}

	public QueryResponse<Row, Entity> getBaseQueryResponse() {
		return this.baseQueryResponse;
	}

	public int getPageSize() {
		return this.baseQueryResponse.getPageSize();
	}

	public int getPageNr() {
		return this.baseQueryResponse.getPageNr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getAllPages()
	 */
	public Enumeration<Page> getAllPages() {
		return this.baseQueryResponse.getAllPages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getCurrentPageInfo()
	 */
	public Page getCurrentPageInfo() {
		return this.baseQueryResponse.getCurrentPageInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getFirstPageInfo()
	 */
	public Page getFirstPageInfo() {
		return this.baseQueryResponse.getFirstPageInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.wp.andro.paging._IPagingInfo#getFirstRowPositionOfPreviousResultRows()
	 */
	public Integer getFirstRowPositionOfPreviousResultRows() {
		return this.baseQueryResponse.getFirstRowPositionOfPreviousResultRows();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getLastPageInfo()
	 */
	public Page getLastPageInfo() {
		return this.baseQueryResponse.getLastPageInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getNextPageInfo()
	 */
	public Page getNextPageInfo() throws PagedResultException {
		return this.baseQueryResponse.getNextPageInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getPageInfo(java.lang.Integer)
	 */
	public Page getPageInfo(Integer pageNumber) throws PagedResultException {
		return this.baseQueryResponse.getPageInfo(pageNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getPreviousPageInfo()
	 */
	public Page getPreviousPageInfo() throws PagedResultException {
		return this.baseQueryResponse.getPreviousPageInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#hasPreviousResultRows()
	 */
	public boolean hasPreviousResultRows() {
		return this.baseQueryResponse.hasPreviousResultRows();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getPagingParams()
	 */
	public PagingParams getPagingParams() {
		return this.baseQueryResponse.getPagingParams();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging.ResultSupport#hasMoreResultRows()
	 */
	@Override
	public boolean hasMoreResultRows() {
		return this.baseQueryResponse.hasMoreResultRows();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging.ResultSupport#setHasMoreResultRows(boolean)
	 */
	@Override
	public void setHasMoreResultRows(boolean hasMoreResultRows) {
		throw new UnsupportedOperationException("Wynik jest tylko do odczytu");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.paging._IPagingInfo#getNumberOfRowsOnThePage()
	 */
	public int getNumberOfRowsOnThePage() {
		return this.baseQueryResponse.getNumberOfRowsOnThePage();
	}

}
