package pl.slawas.filter.exceptions;

/**
 * 
 * SercherException - błąd wyszukiwarki
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public abstract class SearcherException extends Exception {

	private static final long serialVersionUID = -6818199532152800874L;

	/**
	 * 
	 */
	protected SearcherException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	protected SearcherException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	protected SearcherException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SearcherException(Throwable cause) {
		super(cause);
	}

}
