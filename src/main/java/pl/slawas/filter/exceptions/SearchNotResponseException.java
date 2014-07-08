package pl.slawas.filter.exceptions;


/**
 * 
 * SearchNotResponseException
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 */
public class SearchNotResponseException extends SearcherException {

	private static final long serialVersionUID = 1602501396214379967L;

	/**
	 * 
	 */
	public SearchNotResponseException() {
		super("Brak odpowiedzi, mozliwe ze zapytanie jest zbyt ogolne.");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SearchNotResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SearchNotResponseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SearchNotResponseException(Throwable cause) {
		super(cause);
	}

}
