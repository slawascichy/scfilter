package pl.slawas.test.filter.dao;

import java.util.ArrayList;
import java.util.List;

import pl.slawas.filter.dao._ISearcherBaseDAO;
import pl.slawas.filter.exceptions.SearcherException;
import pl.slawas.paging.PagingParams;
import pl.slawas.test.entities.TEntity;
import pl.slawas.test.mock.providers.TEntityMockProvider;

public class TEntityDAO implements _ISearcherBaseDAO<TEntity> {

	public List<TEntity> findByIdList(List<? extends Object> idList)
			throws SearcherException {
		List<TEntity> result = new ArrayList<TEntity>();
		for (Object id : idList) {
			TEntity row = (TEntity) TEntityMockProvider.rows.get(id);
			if (row != null) {
				result.add(row);
			}
		}
		return result;
	}

	public List<TEntity> findByIdList(List<? extends Object> idList,
			boolean resultIsForDump, PagingParams searcherPagingParams)
			throws SearcherException {
		return findByIdList(idList);
	}

	public PagingParams getPagingParams() {
		return null;
	}

}
