package pl.slawas.filter;

import java.util.List;
import java.util.Map;

import pl.slawas.entities._ICopyable;
import pl.slawas.filter.beans.SearchResult;
import pl.slawas.filter.cache.CacheUsage;
import pl.slawas.filter.dao._ISearcherBaseDAO;
import pl.slawas.filter.exceptions.SearchNotResponseException;
import pl.slawas.filter.exceptions.SearcherException;
import pl.slawas.filter.pagination.PaginatedList;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;

/**
 * 
 * _ISearchProvider interfejs providera wyszukiania (wyszukiwarki)
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 * @param <OSearcher>
 *            klasa oryginalnej wyszukiwarki
 * @param <Req>
 *            klasa oryginlnego żądania wysłania zapytania
 * @param <QueClause>
 *            klasa oryginalnej klauzuli zapytania. Dla SQL-a (JDBC) ten
 *            parametr może być taki sam jak parametr 'Que'.
 * @param <QueCondition>
 *            klasa oryginalnej klauzuli warunku zapytania.
 * @param <Row>
 *            klasa odpowiadająca oryginalnemu wierszowi wyniku zapytania
 * @param <Obj>
 *            klasa encji, której dotyczy wyszukiwanie
 * @param <DAO>
 *            klasa DAO encji
 */
public interface _ISearchProvider<OSearcher, Req, QueClause, QueCondition, Row, Obj extends _ICopyable<Obj>, DAO extends _ISearcherBaseDAO<Obj>> {

	/**
	 * @return the dao
	 */
	public DAO getDao();

	/**
	 * Wyszukuje dokumenty w/g zadanej klauzuli zapytania. Domyślnie zostanie
	 * pobrana strona wyników, która została zdefiniowana w wyszukiwarce poprzez
	 * za pomocą metody {@link Searcher#setPage(Page)}. Jeżeli nie została
	 * ustwiona bedzi brana pod uwagę strona domyślna.
	 * 
	 * @param queryClause
	 *            obiekt klauzuli zapytania
	 * @return wynik zapytania {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>lista elementów</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause)
			throws SearcherException;

	/**
	 * Wyszukuje dokumenty w/g zadanej klauzuli zapytania. Domyślnie zostanie
	 * pobrana strona wyników, która została zdefiniowana w wyszukiwarce poprzez
	 * za pomocą metody {@link Searcher#setPage(Page)}. Jeżeli nie została
	 * ustwiona bedzi brana pod uwagę strona domyślna.
	 * 
	 * @param queryClause
	 *            obiekt klauzuli zapytania
	 * @param export
	 *            jeżeli ustawione na <code>true</code>, wtedy zwracane są
	 *            wszystkie znalezione elementy.
	 * @return wynik zapytania {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>lista elementów</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			boolean export) throws SearcherException;

	/**
	 * Wyszukuje dokumenty w/g zadanej klauzuli zapytania
	 * 
	 * @param queryClause
	 *            obiekt klauzuli zapytania
	 * @param page
	 *            obiekt poszukiwanej strony rezultatu
	 * @return wynik zapytania {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>lista elementów</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page)
			throws SearcherException;

	/**
	 * Wyszukuje dokumenty w/g zadanej klauzuli zapytania. Domyślnie zostanie
	 * pobrana strona wyników, która została zdefiniowana w wyszukiwarce poprzez
	 * za pomocą metody {@link Searcher#setPage(Page)}. Jeżeli nie została
	 * ustwiona bedzi brana pod uwagę strona domyślna.
	 * 
	 * @param queryClause
	 *            obiekt klauzuli zapytania
	 * @param sortName
	 *            element po którym należy sortować
	 * @param sortDir
	 *            kierunek sortowania. <b>"asc"</b> lub <b>"desc"</b>.
	 * @return wynik zapytania {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>lista elementów</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			String sortName, String sortDir) throws SearcherException;

	/**
	 * Wyszukuje dokumenty w/g zadanej klauzuli zapytania. Domyślnie zostanie
	 * pobrana strona wyników, która została zdefiniowana w wyszukiwarce poprzez
	 * za pomocą metody {@link Searcher#setPage(Page)}. Jeżeli nie została
	 * ustwiona bedzi brana pod uwagę strona domyślna.
	 * 
	 * @param queryClause
	 *            obiekt klauzuli zapytania
	 * @param sortName
	 *            Element po którym należy sortować
	 * @param sortDir
	 *            Kierunek sortowania. <b>"asc"</b> lub <b>"desc"</b>.
	 * @param export
	 *            jeżeli ustawione na <code>true</code>, wtedy zwracane są
	 *            wszystkie znalezione elementy.
	 * @return wynik zapytania {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>lista elementów</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			String sortName, String sortDir, boolean export)
			throws SearcherException;

	/**
	 * Wyszukuje elementy FixedAssetInventory przy pomocy WPLucene.
	 * 
	 * @param queryClause
	 *            klauzla zapytania
	 * @param page
	 *            Definicja strony
	 * @param sortName
	 *            Element po którym należy sortować
	 * @param sortDir
	 *            Kierunek sortowania. <b>"asc"</b> lub <b>"desc"</b>.
	 * @return Obiekt {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>rezultat typu {@link List}, {@link PaginatedList}</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir) throws SearcherException;

	/**
	 * Wyszukuje elementy FixedAssetInventory przy pomocy WPLucene.
	 * 
	 * @param queryClause
	 *            klauzla zapytania
	 * @param page
	 *            Definicja strony (zobacz również uwagi dotyczące argumentu
	 *            'export')
	 * @param sortName
	 *            Element po którym należy sortować
	 * @param sortDir
	 *            Kierunek sortowania. <b>"asc"</b> lub <b>"desc"</b>.
	 * @param export
	 *            Jeżeli ustawiony na <code>true</code>, wtedy parametr
	 *            stronicowania ({@link PagingParams#getMaxCount()}) związny z
	 *            maksymalną liczbą zwracanych wierszy, parametr stronicowania
	 *            związany z maksymalną liczbą wierszy na stronie (
	 *            {@link PagingParams#getMaxPageSize()}) oraz parametr rozmiaru
	 *            strony ( {@link PagingParams#getPageSize()}) jest zależny od
	 *            definicji strony przesłanej w argumencie 'page' (odpowiednio
	 *            {@link Page#getSize()}). Dlatego też w tym przypadku argument
	 *            'page' powinien mieć możliwie maksymalne ustawienia, można go
	 *            stworzyć np. za pomocą metody
	 *            {@link SearchProvider#getDumpPage()}.
	 * @return Obiekt {@link SearchResult}, który zawiera:
	 *         <ul>
	 *         <li>rezultat typu {@link List}, {@link PaginatedList}</li>
	 *         <li>numer strony</li>
	 *         <li>rozmiar strony</li>
	 *         <li>liczbę wszystkich wyników</li>
	 *         </ul>
	 * @throws SearcherException
	 */
	public SearchResult<Obj> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir, boolean export)
			throws SearcherException;

	public void setCacheUsage(CacheUsage cacheUsage);

	/**
	 * @return the indexName
	 */
	public String getIndexName();

	/**
	 * Wysłanie zapytania do wyszukiwarki
	 * 
	 * @param request
	 *            obiekt żądania wyszukiwania
	 * @return obiekt rezultatu wyszukiwania
	 * @throws SearchNotResponseException
	 */
	public SearchResult<Obj> sendRequest(
			Request<Req, QueClause, QueCondition> request)
			throws SearcherException;

	/**
	 * Pobieranie definicji (obiektu) pierwszej strony
	 * 
	 * @return definicja pierwszej strony
	 */
	public Page getFirstPage();

	/**
	 * Pobieranie definicji strony pozwalającej na ściągnięcie
	 * {@link PagingParams#MAX_DOC_COUNT} na jednej stronie.
	 * 
	 * @return definicja strony ściągnięcia strony.
	 */
	public Page getDumpPage();

	public void setCustomPagingParams(PagingParams customPagingParams);

	public void restorePagingParams();

	public PagingParams getPagingParams();

	/**
	 * @param dao
	 *            the {@link #dao} to set
	 */
	public void setDao(DAO dao);

	/**
	 * Ze wzgledu na to, ze wymaganym parametrem konstruktora klauzuli zapytania
	 * ({@link SearchQueryClause#SearchQueryClause(Map)}) jest mapa pól
	 * "nazwa pola w filtrze" - "nazwa pola w indeksie", a obiekt wyszukiwarki
	 * ma również zdefiniowaną taką mapę (można ją pobrać za pomocą
	 * {@link #getParamsMap()}), więc aby ułatwić sobie życie niech własnie
	 * wyszukiwarka tworzy instancje obiektu klauzuli zapytania jednocześnie
	 * wstrzykując własną mapę ({@link #getParamsMap()}) do nowego obiektu.
	 * 
	 * <p>
	 * Przykład implementacji dla
	 * {@link WpLSearchProvider#createNewQueryClauseInstance()}:
	 * </p>
	 * 
	 * <pre>
	 * public SearchQueryClause&lt;Query, QueryClause&gt; createNewQueryClauseInstance() {
	 * 	return new WpLSearchQueryClause(this.getParamsMap());
	 * }
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	public <T extends SearchQueryClause<QueClause, QueCondition>> T createNewQueryClauseInstance();

	public List<Object> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir, boolean export,
			boolean downloadList) throws SearcherException;

}