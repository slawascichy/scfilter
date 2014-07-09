package pl.slawas.filter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.beans.Accuracy;
import pl.slawas.filter.beans.IndexFieldDefinition;
import pl.slawas.filter.beans.QueryParam;
import pl.slawas.filter.beans.SubClauses;

/**
 * 
 * SearchQueryClause abstrakcja obiektu klauzuli zapytania
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 * @param <QueClause>
 *            klasa oryginalnej klauzuli zapytania, dla zapytań JDBC,
 *            najczęściej jest to obiekt zapytania.
 * @param <QueCondition>
 *            klasa oryginalnego obiektu klauzuli warunku zapytania
 */
@SuppressWarnings("serial")
public abstract class SearchQueryClause<QueClause, QueCondition> implements
		Serializable {

	final protected transient Logger logger = LoggerFactory
			.getLogger(getClass());

	private final Set<SubClauses<QueCondition>> subClauses = new HashSet<SubClauses<QueCondition>>();

	private final Set<QueCondition> clauseConditions = new HashSet<QueCondition>();

	/**
	 * mapa pól "nazwa pola w filtrze" - "nazwa pola w indeksie". W szczególnym
	 * wypadku nazwy pól mogą być takie same.
	 */
	private final Map<String, IndexFieldDefinition> paramsMap;

	/**
	 * @param paramsMap
	 *            mapa pól "nazwa pola w filtrze" - "nazwa pola w indeksie". W
	 *            szczególnym wypadku nazwy pól mogą być takie same.
	 */
	protected SearchQueryClause(Map<String, IndexFieldDefinition> paramsMap) {
		super();
		if (paramsMap == null || paramsMap.isEmpty())
			throw new IllegalArgumentException(
					"Lista mapowania nazw pol w filtrze do nazw pol indeksy nie moze byc pusta.");
		this.paramsMap = paramsMap;
	}

	/**
	 * Metoda pobierająca podzapytania. Jest możliwość zbudowania
	 * 
	 * @return the subqueries
	 */
	public Set<SubClauses<QueCondition>> getSubClauses() {
		return subClauses;
	}

	/**
	 * Metoda dodania klauzul zapytania składających sie na podzapytane.
	 * 
	 * @param subClauses
	 *            lista klauzul zapytania składających sie na podzapytane
	 * @param accuracy
	 *            trafność z jaką ma być spełniony ogólny wynik podzapytania
	 * @return zapytanie, które jest własnie budowane
	 */
	public SearchQueryClause<QueClause, QueCondition> addSubClauses(
			Set<QueCondition> subClauses, Accuracy accuracy) {
		this.subClauses.add(new SubClauses<QueCondition>(subClauses, accuracy));
		logger.debug("Dodalem podzapytanie - aktualna liczba podzapytan: {}",
				this.subClauses.size());
		return this;
	}

	/**
	 * Metoda dodania klauzuli warunku do zapytania.
	 * 
	 * @param clauseCondition
	 *            klauzula warunku zapytania
	 * @return zapytanie, które jest własnie budowane
	 */
	public SearchQueryClause<QueClause, QueCondition> addClause(
			QueCondition clauseCondition) {
		this.clauseConditions.add(clauseCondition);
		return this;
	}

	/**
	 * Dodawanie parametru zapytania - metoda własciwa. Na podstawie parametrów
	 * zostaną zbudowane odpowiednie klauzule warunków występujące w zapytaniu.
	 * 
	 * @param query
	 *            zapytanie, do którego zostaną dodane parametry wyszukiwania
	 * @param param
	 *            paramet zapytania
	 * @return zmienione zapytanie
	 */
	private SearchQueryClause<QueClause, QueCondition> addQueryParam(
			SearchQueryClause<QueClause, QueCondition> query, QueryParam param) {
		String indexFieldName = this.paramsMap.get(param.getFieldName())
				.getIndexValueFieldName();
		if (query != null && param != null) {
			switch (param.getOperationType()) {
			case GT: // (">", "większa", "gt"),
				query.addGTQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case LT: // ("<", "mniejsza", "lt"),
				query.addLTQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case EQ: // ("=", "równa", "eq"),
				query.addEQQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case GEQ: // (">=", "większa/równa", "geq"),
				query.addGEQQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case LEQ: // ("<=", "mniejsza/równa", "leq"),
				query.addLEQQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case RSI: // ("[", "pomiędzy (z)", "rsi"),
				query.addRSIQueryClause(indexFieldName, param.getFrom(),
						param.getTo(), param.getAccuracy());
				break;
			case RSE: // ("{", "pomiędzy (bez)", "rse"),
				query.addRSEQueryClause(indexFieldName, param.getFrom(),
						param.getTo(), param.getAccuracy());
				break;
			case WS: // ("*", "pełnotekstowo", "ws")
				query.addWSQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			case ST: // ("==", "proste", "st")
				query.addSimpleQueryClause(indexFieldName, param.getFrom(),
						param.getAccuracy());
				break;
			}
		}
		return query;
	}

	/**
	 * Dodawanie parametru zapytania
	 * 
	 * @param param
	 *            paramet zapytania
	 * @return budowane zapytanie
	 */
	public SearchQueryClause<QueClause, QueCondition> addQueryParam(
			QueryParam param) {
		return addQueryParam(this, param);
	}

	/**
	 * Metoda dołączająca listę paramerów z domyślnym określeniem trafności
	 * znalezienia wyniku dla calego podzapytania {@link Accuracy#MUST}.
	 * Wynikiem jest dodanie podzapytania, którgo wynik musi być spełniony, dla
	 * wyszukiwanego wiersza.
	 * 
	 * @param params
	 *            lista paremerów wyszukiwania.
	 * @return budowane zapytanie
	 */
	public SearchQueryClause<QueClause, QueCondition> addQueryParams(
			QueryParam[] params) {
		return addQueryParams(params, Accuracy.MUST);
	}

	/**
	 * Metoda dołączająca listę paramerów. Wynikiem jest dodanie podzapytania,
	 * którgo wynik musi być spełniony, dla wyszukiwanego wiersza w zależności
	 * od określonego paraemtru trafnośii. Jeżeli lista parametrów jest
	 * jednoelementowa to wtedy następuje przekierowanie do metody
	 * {@link SearchQueryClause#addQueryParam(QueryParam)}, czyli ewentualne
	 * podzapytanie zapienione zostaje do normalnej klauzuli elementu zapytania.
	 * 
	 * @param params
	 *            lista paremerów wyszukiwania.
	 * @param accuracy
	 *            trafność z jaką ma być spełniony ogólny wynik podzapytania
	 * @return budowane zapytanie
	 */
	public SearchQueryClause<QueClause, QueCondition> addQueryParams(
			QueryParam[] params, Accuracy accuracy) {

		if (params != null && params.length != 0) {
			if (params.length > 1) {
				SearchQueryClause<QueClause, QueCondition> subquery = this
						.createNewInstace(this.paramsMap);
				for (QueryParam param : params) {
					subquery = addQueryParam(subquery, param);
				}
				return this.addSubClauses(subquery.getClauseConditions(),
						accuracy);
			} else {
				return this.addQueryParam(params[0]);
			}
		} else {
			logger.warn("Lista parametrów jest pusta, nie moge dodac podzapytania.");
		}
		return this;
	}

	/**
	 * @return the clauses
	 */
	public Set<QueCondition> getClauseConditions() {
		return clauseConditions;
	}

	/**
	 * Pobieranie oryginalnego (pierwotnego) obiektu reprezentującego daną
	 * implementację klauzuli zapytania
	 * 
	 * @return zapytanie do wyszukiwacza
	 */
	public abstract QueClause getQueryClause();

	/**
	 * Implementacja oddawania klauzuli "wartość większa niż" (Greater There)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addGTQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli "wartość mniejsza niż" (Lower There)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addLTQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli "wartość równa się" (Equals There)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addEQQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli "wartość większa lub równa" (Greater or
	 * Equals There)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addGEQQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli "wartość mniejsza lub równa" (Greater or
	 * Equals There)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addLEQQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli
	 * "wartość zawarta pomiędzy lub równa wartościom podanym na granich" (Range
	 * Search Inclusive)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValueFrom
	 *            wartość kryterium, dolna granica
	 * @param fieldValueFrom
	 *            wartość kryterium, górna granica
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addRSIQueryClause(String fieldName,
			Object fieldValueFrom, Object fieldValueTo, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli
	 * "wartość zawarta pomiędzy ale różna od wartości podanych na granich"
	 * (Range Search Exclusive)
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValueFrom
	 *            wartość kryterium, dolna granica
	 * @param fieldValueFrom
	 *            wartość kryterium, górna granica
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addRSEQueryClause(String fieldName,
			Object fieldValueFrom, Object fieldValueTo, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli wyszukiwania "pełnotekstowego" (Wildcard
	 * Searches, full search). Dane zapytanie mozna porównać do SQL-owej
	 * klauzuli "LIKE".
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addWSQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Implementacja oddawania klauzuli wyszukiwania "prostego" dla tekstu. W
	 * odróżnieniu od metody {@link #addWSQueryClause(String, String, Accuracy)}
	 * , ta metoda porównuje dwa stringi za pomocą operatora równości ("=").
	 * 
	 * @param fieldName
	 *            nazwa pola, którego dotyczy kryterium
	 * @param fieldValue
	 *            wartość kryterium
	 * @param accuracy
	 *            informacja o trafności spełnienia warunku zapytania
	 *            {@link Accuracy}
	 */
	protected abstract void addSimpleQueryClause(String fieldName,
			Object fieldValue, Accuracy accuracy);

	/**
	 * Ponieważ na poziomie abstrakcji nie można odwoływać sie do abstrakcyjnego
	 * konstruktora zatem wymagane jest stworzenie implementacji metody tworząca
	 * nową instancje zapytania. Jej implementacja powinna zwrócić nowy obiekt
	 * implementujący niniejszą abstrakcję.
	 * <p>
	 * Przykład dla implementacji przkładowej klasy
	 * Query&lt;pl.wp.lucene.api.search.query.Query>
	 * </p>
	 * 
	 * <pre>
	 * protected Query&lt;pl.wp.lucene.api.search.query.Query&gt; createNewInstace(
	 * 		Map&lt;String, String&gt; paramsMap) {
	 * 	return new Query&lt;pl.wp.lucene.api.search.query.Query&gt;(paramsMap);
	 * }
	 * </pre>
	 * 
	 * @param paramsMap
	 *            mapa pól "nazwa pola w filtrze" - "nazwa pola w indeksie". W
	 *            szczególnym wypadku nazwy pól mogą być takie same.
	 * @return nowa instancja klauzuli zapytania
	 */
	protected abstract SearchQueryClause<QueClause, QueCondition> createNewInstace(
			Map<String, IndexFieldDefinition> paramsMap);

	/**
	 * Metoda dodania klauzul podzapytania. Implementaca wyciąga klauzule z
	 * zapytamia i przekazuje je do utworzenia zapytania głównego.
	 * 
	 * @param subQuery
	 *            zapytanie, z którego zostaną wyciągnięte klauzule do
	 *            podzaytania.
	 * @param accuracy
	 *            trafność z jaką ma zostać potraktowany wynik podzapytania
	 * @return własnie rworzone zapytanie.
	 */
	public SearchQueryClause<QueClause, QueCondition> addSubQuery(
			SearchQueryClause<QueClause, QueCondition> subQuery,
			Accuracy accuracy) {
		return this.addSubClauses(subQuery.getClauseConditions(), accuracy);
	}

	/**
	 * Metoda dodania klauzul podzapytania. Implementaca wyciąga klauzule z
	 * zapytamia i przekazuje je do utworzenia zapytania głównego. Jako, że nie
	 * podaje się trafności z jaką ma zostać ptraktowany wynik podzapytania,
	 * wstawiana jest domyślnie {@link Accuracy#MUST}.
	 * 
	 * @param subQuery
	 *            zapytanie, z którego zostaną wyciągnięte klauzule do
	 *            podzaytania.
	 * @return własnie rworzone zapytanie.
	 */
	public SearchQueryClause<QueClause, QueCondition> addSubQuery(
			SearchQueryClause<QueClause, QueCondition> subQuery) {
		return this
				.addSubClauses(subQuery.getClauseConditions(), Accuracy.MUST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((clauseConditions == null) ? 0 : clauseConditions.hashCode());
		result = prime * result
				+ ((subClauses == null) ? 0 : subClauses.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchQueryClause<?, ?> other = (SearchQueryClause<?, ?>) obj;
		if (clauseConditions == null) {
			if (other.clauseConditions != null)
				return false;
		} else if (!clauseConditions.equals(other.clauseConditions))
			return false;
		if (subClauses == null) {
			if (other.subClauses != null)
				return false;
		} else if (!subClauses.equals(other.subClauses))
			return false;
		return true;
	}

	/**
	 * @return the {@link #paramsMap}
	 */
	public Map<String, IndexFieldDefinition> getParamsMap() {
		return paramsMap;
	}

}
