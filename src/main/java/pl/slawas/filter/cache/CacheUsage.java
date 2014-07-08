package pl.slawas.filter.cache;

/**
 * 
 * CacheUsage - metody/statusy użycia keszowania elementów
 * 
 * @see #NONE
 * @see #TO_USE
 * @see #REFRESH
 * @see #TO_REMOVE
 * @see #TO_USE_IN_FIRST_LEVEL_CACHE
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public enum CacheUsage {

	/**
	 * Nie używaj kesza
	 */
	NONE,
	/**
	 * Użyj kesza
	 */
	TO_USE,
	/**
	 * Użyj kesza, ale odśwież element w keszu
	 */
	REFRESH,
	/**
	 * W trakcie aktywnej transakcji, użyj kesza z elementem, który właśnie
	 * został dodany, i którego jeszcze nie ma w bazie danych, użyj go tylko w
	 * keszu lokalnym.
	 */
	TO_USE_IN_FIRST_LEVEL_CACHE,
	/**
	 * Użyj kesza, ale usuń element z kesza
	 */
	TO_REMOVE;
}
