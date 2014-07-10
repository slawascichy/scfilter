package pl.slawas.filter;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.beans.SortParams;
import pl.slawas.filter.cache.CacheUsage;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;

/**
 * 
 * Searcher - klasa abstrakcyjna reprezentująca wyszukiwacza
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 * @param <Req>
 *            klasa oryginlnego żądania wysłania zapytania
 * @param <QueClause>
 *            klasa oryginalnej klauzuli zapytania. Dla SQL-a (JDBC) ten
 *            parametr może być taki sam jak parametr 'Que'.
 * @param <QueCondition>
 *            klasa oryginalnego obiektu klauzuli warunku zapytania
 * @param <Row>
 * @param <Entity>
 */
@SuppressWarnings("serial")
public abstract class Searcher<Req, QueClause, QueCondition, Row extends ResultRow<Entity>, Entity>
		implements Serializable {

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass());

	protected CacheUsage cacheUsage = CacheUsage.TO_USE;

	private final PagingParams pagingParams;

	private PagingParams customPagingParams;

	/**
	 * Konstruktor ustawia niedomyślny obiekt parametrów stronicowania
	 * 
	 * @param pagingParams
	 *            obiekt parametrów stronicowania
	 */
	protected Searcher(PagingParams pagingParams) {
		this.pagingParams = pagingParams;
	}

	/**
	 * Konstruktor ustawia domyślne parametry stronicowania
	 */
	protected Searcher() {
		this.pagingParams = new PagingParams(new Page());
	}

	/**
	 * Metoda wyszukująca
	 * 
	 * @param request
	 *            zapytanie do wyszukiwacza
	 * @return odpowiedz od wyszukiwacza
	 */
	public abstract QueryResponse<Row, Entity> search(
			Request<Req, QueClause, QueCondition> request);

	/**
	 * Metoda budująca żądanie realizacji zapytania do wyszukiwacza
	 * 
	 * @param indexName
	 *            nazwa indeksu
	 * @param query
	 *            zapytanie
	 * @param sorts
	 * @param maxResults
	 *            maksymalna liczba wyników w rezultacie zapytania
	 * @return zbudowane żądanie realizacji zapytania
	 */
	public abstract Request<Req, QueClause, QueCondition> getRequest(
			String indexName, SearchQueryClause<QueClause, QueCondition> query,
			List<SortParams> sorts, Integer maxResults);

	/**
	 * @param cacheUsage
	 *            the cacheUsage to set
	 */
	public void setCacheUsage(CacheUsage cacheUsage) {
		if (cacheUsage == null)
			throw new IllegalArgumentException(
					"Nie wolno ustawiac uzycia kesza do wartosci null");

		this.cacheUsage = cacheUsage;
	}

	/**
	 * @return the pagingParams
	 */
	public PagingParams getPagingParams() {
		return (this.customPagingParams != null ? this.customPagingParams
				: this.pagingParams);
	}

	public boolean setPage(Page page) {
		return getPagingParams().setPage(page);
	}

	/**
	 * @param customPagingParams
	 *            the {@link #customPagingParams} to set
	 */
	public void setCustomPagingParams(PagingParams customPagingParams) {
		if (!isSupportedCustomPagingParams())
			throw new UnsupportedOperationException(
					"Operacja modyfikacji parametrow stronicowania nie jest wspierana "
							+ "dla tej implementacji wyszukiwarki.");
		this.customPagingParams = customPagingParams;
	}

	public boolean isSetCustomPagingParams() {
		return this.customPagingParams == null;
	}

	public boolean restorePagingParams() {
		this.customPagingParams = null;
		return isSetCustomPagingParams();
	}

	public boolean isSupportedCustomPagingParams() {
		return false;
	}

	/**
	 * @return the {@link #cacheUsage}
	 */
	public CacheUsage getCacheUsage() {
		return cacheUsage;
	}
}
