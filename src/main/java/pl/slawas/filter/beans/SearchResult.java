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
public class SearchResult<RowObj, Row extends ResultRow<RowObj>, Entity>
		extends ResultSupport<Entity> implements _IPagingInfo {

	private static final long serialVersionUID = 8586577008473662383L;

	private final QueryResponse<RowObj, Row, Entity> baseQueryResponse;

	/**
	 * 
	 * @param baseQueryResponse
	 *            wynik wyszukiwania, na podstwie, którego został zbudowany
	 *            rezultat
	 * @param list
	 *            lista obiektów stanowiąca rezultat wyszukiwania
	 */
	public SearchResult(QueryResponse<RowObj, Row, Entity> baseQueryResponse,
			Collection<Entity> list) {
		super(baseQueryResponse.getStartPosition(), baseQueryResponse
				.getEndPosition(), baseQueryResponse.getFirstRowPosition(),
				baseQueryResponse.getResultSize(), baseQueryResponse
						.getLastRowPosition(), list, baseQueryResponse
						.getMessage(), baseQueryResponse
						.getAbsoluteFirstRowPosition(), baseQueryResponse
						.getResultMaxPages(), baseQueryResponse
						.getExecutionTime());
		this.baseQueryResponse = baseQueryResponse;

	}

	public QueryResponse<RowObj, Row, Entity> getBaseQueryResponse() {
		return this.baseQueryResponse;
	}

	public int getPageSize() {
		return this.baseQueryResponse.getPageSize();
	}

	public int getPageNr() {
		return this.baseQueryResponse.getPageNr();
	}

	public Enumeration<Page> getAllPages() {
		return this.baseQueryResponse.getAllPages();
	}

	public Page getCurrentPageInfo() {
		return this.baseQueryResponse.getCurrentPageInfo();
	}

	public Page getFirstPageInfo() {
		return this.baseQueryResponse.getFirstPageInfo();
	}

	public Integer getFirstRowPositionOfPreviousResultRows() {
		return this.baseQueryResponse.getFirstRowPositionOfPreviousResultRows();
	}

	public Page getLastPageInfo() {
		return this.baseQueryResponse.getLastPageInfo();
	}

	public Page getNextPageInfo() throws PagedResultException {
		return this.baseQueryResponse.getNextPageInfo();
	}

	public Page getPageInfo(Integer pageNumber) throws PagedResultException {
		return this.baseQueryResponse.getPageInfo(pageNumber);
	}

	public Page getPreviousPageInfo() throws PagedResultException {
		return this.baseQueryResponse.getPreviousPageInfo();
	}

	public boolean hasPreviousResultRows() {
		return this.baseQueryResponse.hasPreviousResultRows();
	}

	public PagingParams getPagingParams() {
		return this.baseQueryResponse.getPagingParams();
	}

	@Override
	public boolean hasMoreResultRows() {
		return this.baseQueryResponse.hasMoreResultRows();
	}

	@Override
	public void setHasMoreResultRows(boolean hasMoreResultRows) {
		throw new UnsupportedOperationException("Wynik jest tylko do odczytu");
	}

	public int getNumberOfRowsOnThePage() {
		return this.baseQueryResponse.getNumberOfRowsOnThePage();
	}

}
