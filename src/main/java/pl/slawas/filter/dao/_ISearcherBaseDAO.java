package pl.slawas.filter.dao;

import java.util.List;

import pl.slawas.filter.SearchProvider;
import pl.slawas.filter.exceptions.SearcherException;
import pl.slawas.paging.PagingParams;

/**
 * 
 * _ISearcherBaseDAO
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 * @param <Obj>
 */
public interface _ISearcherBaseDAO<Obj> {

	/**
	 * Wyszukiwanie listy obiektów na podstawie listy kluczy głownych. Dla baz
	 * SQL najczęściej w takim wyszukiwaniu wykorzystywana jest klauzula IN w
	 * warunku wyszukiwania. Metoda wykorzystywana jest w implementacji
	 * {@link SearchProvider#sendRequest(pl.wp.andro.filter.Request)} do
	 * łączenia rezultatu wyszukiwarki z encjami.
	 * 
	 * @param idList
	 *            lista kluczy głownych (identyfiaktorów) encji
	 * @param resultIsForDump
	 *            flaga mówiąca o tym czy dana metoda wykorzystywana jest do
	 *            dump-a (exportu) danych. Jeżeli ustawimy ją na wartość
	 *            {@code true}, to programista implementujący tę metodę poinien
	 *            zadbać by wyłączyć kesze pierwszego poziomu związane z
	 *            managerem encji, aby poprawić wydajność wyszukiwarki.
	 * @param searcherPagingParams
	 *            Gdy parametr 'resultIsForDump' jest ustawiony na {@code true}
	 *            i jeżeli obiekt DAO obsługuje przesyłanie stronicowanych
	 *            wyników zapytań, to jest potrzebna synchronizowania
	 *            stronicowania DAO z wyszukiwarką (parametry stronicowania z
	 *            wyszukiwarki są przekazywane obiektowi DAO) ponieważ z prawie
	 *            na pewno proces exportu danych narusza nałożone restrykcje
	 *            stronicowana, w którym jest implementowny obiekt DAO.
	 * @return lista encji
	 * @throws SearcherException
	 */
	public List<Obj> findByIdList(List<? extends Object> idList, boolean resultIsForDump,
			PagingParams searcherPagingParams) throws SearcherException;

	/**
	 * Jeżeli obiekt DAO obsługuje przesyłanie stronicowanych wyników zapytań,
	 * to jest potrzebna synchronizacja stronicowania wyszukiwarki ze
	 * stronicowaniem DAO. Operacja taka musi być wykonywana, aby nie nastąpił
	 * konflikt pomiedzy ideą implementacji DAO, a zaimplementowanym
	 * stronicowaniem w wyszukiwarce. <i><b>Przykład:</b> Domyślne ustwienia
	 * wyszukiwarki definiują rozmiar strony jako wartość {@code 50}, a system,
	 * który wykorzystuje stronicowane DAO, ma stronę wielkości {@code 60}.
	 * Trzeba tę informację przesłać wyszukiwarce.</i>
	 * 
	 * @return parametry stronicowania obiektu DAO
	 */
	public PagingParams getPagingParams();

}
