package pl.slawas.filter.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.slawas.filter.pagination.PaginatedList;
import pl.slawas.filter.pagination.SortOrderEnum;
import pl.slawas.paging.Page;
import pl.slawas.paging.PagingParams;

// TODO

/**
 * Klasa wspomaga stronicowanie list
 * 
 * @version $Revision: 1.1 $
 * 
 * @param <E>
 */
public class FilterPaginatedList<E> extends ArrayList<E> implements
		PaginatedList<E> {

	private static final long serialVersionUID = -2651083322173937767L;

	private Long fullListSize = PagingParams.DEFAULT_PAGING_OFFSET;

	private int objectsPerPage = Page.DEFAULT_PAGE_SIZE;

	private int pageNumber = Page.DEFAULT_PAGE_NR;

	private String sortCriterion = null;

	private SortOrderEnum sortDirection = null;

	public FilterPaginatedList() {
		super();
	}

	public FilterPaginatedList(int size) {
		super(size);
	}

	public FilterPaginatedList(Collection<? extends E> collection) {
		super(collection);
	}

	public Long getFullListSize() {
		return fullListSize;
	}

	public void setFullListSize(Long fullListSize) {
		this.fullListSize = fullListSize;
	}

	public List<E> getList() {
		return this;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSearchId() {
		return null;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}
}
