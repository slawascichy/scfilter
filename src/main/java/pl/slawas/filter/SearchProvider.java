package pl.slawas.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.entities.IDUtils;
import pl.slawas.entities.IDUtilsErrorException;
import pl.slawas.entities._ICopyable;
import pl.slawas.filter.beans.FilterPaginatedList;
import pl.slawas.filter.beans.IndexFieldDefinition;
import pl.slawas.filter.beans.SearchResult;
import pl.slawas.filter.beans.SortParams;
import pl.slawas.filter.cache.CacheUsage;
import pl.slawas.filter.dao._ISearcherBaseDAO;
import pl.slawas.filter.exceptions.SearchNotResponseException;
import pl.slawas.filter.exceptions.SearchObjectIdException;
import pl.slawas.filter.exceptions.SearcherException;
import pl.slawas.filter.pagination.SortOrderEnum;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;
import pl.slawas.paging.PagingParamsReadOnly;

/**
 * 
 * SearchProvider - klasa abstrakcyjna providera wyszukującego/filtrującego dane
 * w indeksie wyszukiwania. Klasa tak zaimplementowana, że obsługuje tylko jeden
 * parametr sortowania.
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.12 $
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
 * @param <RowObj>
 *            klasa odpowiadająca oryginalnemu wierszowi wyniku zapytania
 * @param <Row>
 *            reprezentacja wiersza.
 * @param <Entity>
 *            klasa encji, której dotyczy wyszukiwanie
 * @param <DAO>
 *            klasa DAO encji
 */
@SuppressWarnings("serial")
public abstract class SearchProvider<OSearcher, Req, QueClause, QueCondition, RowObj, Row extends ResultRow<RowObj>, Entity extends _ICopyable<Entity>, DAO extends _ISearcherBaseDAO<Entity>>
		implements
		Serializable,
		_ISearchProvider<OSearcher, Req, QueClause, QueCondition, RowObj, Row, Entity, DAO> {

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass().getName());

	/**
	 * Maksymalna liczba wartości jako argumenty klauzuli IN. Nie wolno go
	 * przektoczyć, ze względu na mechanizmy związane z pobieraniem encji (encje
	 * pobierane są klauzulą IN, i nie ważne z jakiego serwera bazy danych
	 * korzystamy, zbyt duża liczba argumentów prowadzi do katastrofy). Wartość
	 * parametru została wyznaczona na podstawie doświadzczenia i {@code 500}
	 * daje optyymalny wynik.
	 */
	public final static int MAX_IN_VALUES = 500;

	protected DAO dao;

	private boolean wasExport = false;

	private PagingParams pagingParamsBeforeExport;

	public DAO getDao() {
		return dao;
	}

	protected final String indexName;

	protected final Entity unknownObject;

	protected final String primaryKeyColumnName;

	protected final Searcher<Req, QueClause, QueCondition, RowObj, Row, Entity> searcher;

	/**
	 * Aby utworzyć wyszukiwarke trzeba podać nazwę indeksu.
	 * 
	 * @param indexName
	 */
	protected SearchProvider(
			Searcher<Req, QueClause, QueCondition, RowObj, Row, Entity> searcher,
			String indexName, Entity unknownObject, DAO dao,
			String primaryKeyColumnName) {
		this.searcher = searcher;
		this.indexName = indexName;
		this.unknownObject = unknownObject;
		this.primaryKeyColumnName = primaryKeyColumnName;
		this.dao = dao;
		init();
	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			Integer maxResults) throws SearcherException {
		return find(queryClause, this.searcher.getPagingParams().getPage(),
				null, null, false, maxResults);
	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			boolean export, Integer maxResults) throws SearcherException {
		return this.find(queryClause,
				this.searcher.getPagingParams().getPage(), null, null, export,
				maxResults);

	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			Integer maxResults) throws SearcherException {
		return find(queryClause, page, null, null, false, maxResults);
	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			String sortName, String sortDir, Integer maxResults)
			throws SearcherException {
		return find(queryClause, this.searcher.getPagingParams().getPage(),
				sortName, sortDir, false, maxResults);
	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause,
			String sortName, String sortDir, boolean export, Integer maxResults)
			throws SearcherException {
		return this.find(queryClause,
				this.searcher.getPagingParams().getPage(), sortName, sortDir,
				export, maxResults);
	}

	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir, Integer maxResults)
			throws SearcherException {
		return this.find(queryClause, page, sortName, sortDir, false,
				maxResults);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchResult<RowObj, Row, Entity> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir, boolean export, Integer maxResults)
			throws SearcherException {

		List<SortParams> sorts = createSortPatamsList(sortName, sortDir);
		Request request = searcher.getRequest(indexName, queryClause, sorts,
				maxResults);
		if (export) {
			if (!this.wasExport) {
				this.pagingParamsBeforeExport = searcher.getPagingParams()
						.copy();
				this.wasExport = true;
			}
			PagingParams dumpPagingParams = new PagingParams(page.getSize(),
					0L, page.getSize(), 0L + page.getSize());
			request.getPagingParams().copy(dumpPagingParams);
			request.setResultIsForDump(true);
			prepareIndexerParamsBeforeExport();
		} else if (this.wasExport) {
			request.getPagingParams().copy(pagingParamsBeforeExport);
			this.wasExport = false;
			request.setResultIsForDump(false);
			prepareIndexerParamsAfterExport();
		}

		if (!request.setPage(page)) {
			logger.warn("Nie udalo sie ustwic parametrow strony.");
		}

		return this.sendRequest(request);
	}

	/**
	 * Utworzenie jednoelementowej listy parametrów do sortowania.
	 * 
	 * @param sortName
	 *            nazwa parametru do sortowania
	 * @param sortDir
	 *            kierunek do sortowania.
	 * @return
	 */
	private List<SortParams> createSortPatamsList(String sortName,
			String sortDir) {
		List<SortParams> sorts = null;
		if (sortName != null
				&& sortDir != null
				&& (sortDir.toLowerCase().equals("asc") || sortDir
						.toLowerCase().equals("desc"))) {
			logger.trace("Ustawiam pole sortowania: '{}'", sortName);
			String sortParam = getParamsMap().get(sortName)
					.getIndexSortFieldName();
			if (sortParam != null) {
				SortParams sortParams = new SortParams(sortParam, sortDir
						.toLowerCase().equals("asc"));
				sorts = new ArrayList<SortParams>();
				sorts.add(sortParams);
			}
		}
		return sorts;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> find(
			SearchQueryClause<QueClause, QueCondition> queryClause, Page page,
			String sortName, String sortDir, boolean export,
			boolean downloadList, Integer maxResults) throws SearcherException {

		List<SortParams> sorts = createSortPatamsList(sortName, sortDir);
		Request request = searcher.getRequest(indexName, queryClause, sorts,
				maxResults);
		if (export) {
			if (!this.wasExport) {
				this.pagingParamsBeforeExport = searcher.getPagingParams()
						.copy();
				this.wasExport = true;
			}
			PagingParams dumpPagingParams = new PagingParams(page.getSize(),
					0L, page.getSize(), 0L + page.getSize());
			request.getPagingParams().copy(dumpPagingParams);
			request.setResultIsForDump(true);
			prepareIndexerParamsBeforeExport();
		} else if (this.wasExport) {
			request.getPagingParams().copy(pagingParamsBeforeExport);
			this.wasExport = false;
			request.setResultIsForDump(false);
			prepareIndexerParamsAfterExport();
		}

		if (!request.setPage(page)) {
			logger.warn("Nie udalo sie ustwic parametrow strony.");
		}

		QueryResponse<RowObj, Row, Entity> qResponse = this.searcher
				.search(request);
		if (qResponse == null) {
			throw new SearchNotResponseException();
		}
		Collection<Row> result = qResponse.getResults();
		FilterPaginatedList<Entity> list = new FilterPaginatedList<Entity>(
				result.size());
		List<Object> idList = new ArrayList<Object>(result.size());
		if (result.size() > 0) {
			try {
				Map<Object, Integer> resultOrderMap = new Hashtable<Object, Integer>(
						result.size());
				Object id;
				Map<String, Object> map;
				Iterator<Row> iterator = result.iterator();
				int iCount = 0;
				while (iterator.hasNext()) {
					Row qr = iterator.next();
					map = qr.getFields();
					id = map.get(this.primaryKeyColumnName);
					if (id instanceof String) {
						id = IDUtils.stringToId(unknownObject.getClass(),
								(String) id);
					}
					if (logger.isTraceEnabled()) {
						logger.trace("Mam id: {} ({})",
								new Object[] { id, id.getClass() });
					}
					idList.add(id);
					list.add((Entity) IDUtils.setObjectId(unknownObject.copy(),
							id));
					resultOrderMap.put(id, iCount);
					iCount++;
				}
			} catch (IDUtilsErrorException e) {
				throw new SearchObjectIdException(e);
			}
		}
		return idList;
	}

	public void setCacheUsage(CacheUsage cacheUsage) {
		logger.debug("Ustawiam uzycie kesza na {}", cacheUsage);
		this.searcher.setCacheUsage(cacheUsage);
	}

	/**
	 * Metoda wykorzystywana do pobrania mapowania nazw pól obiektu filtru do
	 * nazw pól w istniejącym indeksie. Istotne wykorzystanie jest podczas
	 * budowania klauzuli sortowania (
	 * {@link #find(SearchQueryClause, Page, String, String, boolean)}), ale
	 * może być również przydatna do budowania kryteriów wyszukiwania.
	 * 
	 * @return mapa pól "nazwa pola w filtrze" - "definicja pola w indeksie". W
	 *         szczególnym wypadku nazwy pól mogą być takie same.
	 */
	public abstract Map<String, IndexFieldDefinition> getParamsMap();

	public String getIndexName() {
		return indexName;
	}

	private Object requestLock = new Object();

	@SuppressWarnings("unchecked")
	public SearchResult<RowObj, Row, Entity> sendRequest(
			Request<Req, QueClause, QueCondition> request)
			throws SearcherException {
		synchronized (requestLock) {
			QueryResponse<RowObj, Row, Entity> qResponse = this.searcher
					.search(request);
			if (qResponse == null) {
				throw new SearchNotResponseException();
			}
			Collection<Row> result = qResponse.getResults();
			FilterPaginatedList<Entity> list = new FilterPaginatedList<Entity>(
					result.size());
			if (result.size() > 0) {
				try {
					Map<Object, Integer> resultOrderMap = new Hashtable<Object, Integer>(
							result.size());
					List<Object> idList = new ArrayList<Object>(result.size());
					Object id;
					Map<String, Object> map;
					Iterator<Row> iterator = result.iterator();
					int iCount = 0;
					while (iterator.hasNext()) {
						Row qr = iterator.next();
						map = qr.getFields();
						id = map.get(this.primaryKeyColumnName);
						if (id instanceof String) {
							id = IDUtils.stringToId(unknownObject.getClass(),
									(String) id);
						}
						if (logger.isTraceEnabled()) {
							logger.trace("Mam id: {} ({})", new Object[] { id,
									id.getClass() });
						}
						idList.add(id);
						list.add((Entity) IDUtils.setObjectId(
								unknownObject.copy(), id));
						resultOrderMap.put(id, iCount);
						iCount++;
					}
					List<Entity> partialResult = null;
					List<Entity> fullResult = new ArrayList<Entity>();
					if (logger.isTraceEnabled()) {
						logger.trace("Getting data from DB");
					}
					PagingParams pagingParamsForLoadEntities = new PagingParams(
							MAX_IN_VALUES, 0L, MAX_IN_VALUES,
							0L + MAX_IN_VALUES);
					for (int i = 0; i <= result.size() / MAX_IN_VALUES; i++) {
						int from = i * MAX_IN_VALUES;
						int to = (i + 1) * MAX_IN_VALUES > result.size() ? result
								.size() : (i + 1) * MAX_IN_VALUES;
						if (from < to) {
							if (logger.isTraceEnabled()) {
								logger.trace(
										"Getting results: {} - {} at {}",
										new Object[] { from, to, result.size() });
							}
							partialResult = this.dao.findByIdList(
									idList.subList(from, to),
									request.isResultIsForDump(),
									pagingParamsForLoadEntities);
							fullResult.addAll(partialResult);
						}
					}
					for (Entity f : fullResult) {
						list.set(resultOrderMap.get(IDUtils.getObjectId(f)), f);
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Data downloaded");
					}
				} catch (IDUtilsErrorException e) {
					throw new SearchObjectIdException(e);
				}
			}
			list.setFullListSize(qResponse.getResultSize());
			list.setObjectsPerPage(request.getPageSize());
			list.setPageNumber(request.getPageNr());
			if (request.hasSortParams()) {
				List<SortParams> sorts = request.getSortParams();
				SortParams sortParams = sorts.get(0);
				list.setSortCriterion(sortParams.getSortParam());
				list.setSortDirection(sortParams.isAsc() ? SortOrderEnum.ASCENDING
						: SortOrderEnum.DESCENDING);
			}

			switch (this.searcher.getCacheUsage()) {
			case REFRESH:
				this.searcher.setCacheUsage(CacheUsage.TO_USE);
				break;
			default:
				break;
			}

			return new SearchResult<RowObj, Row, Entity>(qResponse, list);
		}
	}

	public Page getFirstPage() {
		return this.searcher.getPagingParams().getFirstPage();
	}

	public Page getDumpPage() {
		return new Page(PagingParams.MAX_DOC_COUNT.intValue(), Page.MIN_PAGE_NR);
	}

	protected void prepareIndexerParamsAfterExport() {
		if (this.searcher.isSupportedCustomPagingParams()) {
			PagingParams pp = this.dao.getPagingParams();
			if (pp != null && pp.isReadOnly()) {
				pp = pp.copy();
			}
			logger.trace(
					"\n******\n Synchronizacja parametrow z DAO: \n {}\n******",
					pp);
			this.searcher.setCustomPagingParams(pp);
		}
		this.searcher.getPagingParams().setOffset(0L);
		this.setCacheUsage(CacheUsage.REFRESH);
	}

	protected void prepareIndexerParamsBeforeExport() {
		this.setCacheUsage(CacheUsage.NONE);
	}

	/**
	 * Inicjalizacja wyszukiwarki.
	 */
	protected void init() {
		prepareIndexerParamsAfterExport();
	}

	public void setCustomPagingParams(PagingParams customPagingParams) {
		this.searcher.setCustomPagingParams(customPagingParams);
		this.setCacheUsage(CacheUsage.REFRESH);
	}

	public void restorePagingParams() {
		if (this.searcher.restorePagingParams())
			prepareIndexerParamsAfterExport();
	}

	public PagingParams getPagingParams() {
		return new PagingParamsReadOnly(this.searcher.getPagingParams());
	}

	public void setDao(DAO dao) {
		synchronized (requestLock) {
			if (dao == null)
				throw new IllegalArgumentException(
						"Obiekt DAO nie moze byc pusty");
			this.dao = dao;
		}
	}

	public abstract <T extends SearchQueryClause<QueClause, QueCondition>> T createNewQueryClauseInstance();

}
