package pl.slawas.filter;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.beans.SortParams;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;

/**
 * Request - klasa abstrakcyjna reprezentująca żądanie realizacji zapytania do
 * Wyszukiwawcza, w SQL-u (JDBC) odpowiada zapytaniu.
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 * @param <Req>
 *            klasa oryginalnego żądania wykonania zapytania
 * @param <QueClause>
 *            klasa oryginalnej klauzuli zapytania.
 * @param <QueCondition>
 *            klasa oryginalnego obiektu klauzuli warunku zapytania
 */
@SuppressWarnings("serial")
public abstract class Request<Req, QueClause, QueCondition> implements
		Serializable {

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass());

	protected final String indexName;

	/**
	 * Flaga informująca o tym czy wynik zapytania jest interpretowany jako
	 * eksport danych (DUMP).
	 */
	private boolean resultIsForDump = false;

	protected final SearchQueryClause<QueClause, QueCondition> queryClause;

	protected final PagingParams pagingParams;

	public Request(String indexName,
			SearchQueryClause<QueClause, QueCondition> queryClause,
			PagingParams pagingParams) {
		this.indexName = indexName;
		this.queryClause = queryClause;
		this.pagingParams = pagingParams;
	}

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return this.indexName;
	}

	/**
	 * Ustawienie paramerów sortowania
	 * 
	 * @param sortParams
	 *            parametry sortowania
	 */
	public abstract void setSortParams(SortParams sortParams);

	/**
	 * Pobieranie paramerów sortowania, na potrzeby implementacji metody
	 * {@link #hasSortParams()} czy też potrzebne w implementacji wysłania
	 * requestu do wyszukiwarki (zobacz
	 * {@link SearchProvider#sendRequest(Request)}).
	 * 
	 * @return parametry sortowania
	 */
	public abstract SortParams getSortParams();

	/**
	 * Informacja czy w żądaniu wyniku, były parametry sortowania
	 * 
	 * @return jeżeli parametry sortowania są ustwione (wynik metody
	 *         {@link #getSortParams()}) nie jest pusty, to zwraca {@code true},
	 *         w przeciwnym razie {@code false}.
	 */
	public boolean hasSortParams() {
		return this.getSortParams() != null;
	}

	/**
	 * Pobranie oryginalnego obiektu reprezentująca żądanie realizacji zapytania
	 * do wyszukiwacza
	 * 
	 * @return obiekt żądania
	 */
	public abstract Req getRequest();

	public int getPageNr() {
		return this.pagingParams.getPage().getNumber();
	}

	public int getPageSize() {
		return this.pagingParams.getPage().getSize();
	}

	public boolean setPage(Page page) {
		return this.pagingParams.setPage(page);
	}

	/**
	 * @return the pagingParams
	 */
	public PagingParams getPagingParams() {
		return pagingParams;
	}

	/**
	 * @return the queryClause
	 */
	public SearchQueryClause<QueClause, QueCondition> getQueryClause() {
		return queryClause;
	}

	/**
	 * @return the {@link #resultIsForDump}
	 */
	public boolean isResultIsForDump() {
		return resultIsForDump;
	}

	/**
	 * @param resultIsForDump
	 *            the {@link #resultIsForDump} to set
	 */
	public void setResultIsForDump(boolean resultIsForDump) {
		this.resultIsForDump = resultIsForDump;
	}

}
