package pl.slawas.test.filter.impl;

import java.util.HashMap;
import java.util.Map;

import pl.slawas.filter.SearchProvider;
import pl.slawas.filter.beans.IndexFieldDefinition;
import pl.slawas.test.entities.TEntity;
import pl.slawas.test.filter.dao.TEntityDAO;

public class TSearchProvider
		extends
		SearchProvider<TSearcher, TRequest, TSearchQueryClause, TQueryCondition, TEntity, TResultRow, TEntity, TEntityDAO> {

	private static final long serialVersionUID = 5614733748662419679L;

	public static Map<String, IndexFieldDefinition> parMap = new HashMap<String, IndexFieldDefinition>();
	static {
		parMap.put("key", new IndexFieldDefinition("key"));
		parMap.put("id", new IndexFieldDefinition("id"));
		parMap.put("name", new IndexFieldDefinition("name"));
		parMap.put("date", new IndexFieldDefinition("date"));
		parMap.put("price", new IndexFieldDefinition("price"));
		parMap.put("user", new IndexFieldDefinition("user"));
	}

	/**
	 * @param searcher
	 */
	public TSearchProvider() {
		super(new TSearcher(), "TEST", new TEntity(), new TEntityDAO(), "key");
	}

	@Override
	public Map<String, IndexFieldDefinition> getParamsMap() {
		return parMap;
	}

	@Override
	protected void prepareIndexerParamsAfterExport() {
	}

	@Override
	protected void prepareIndexerParamsBeforeExport() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSearchQueryClause createNewQueryClauseInstance() {
		return new TSearchQueryClause(this.getParamsMap());
	}

}
