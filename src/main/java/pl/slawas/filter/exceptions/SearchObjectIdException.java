package pl.slawas.filter.exceptions;


/**
 * 
 * SearchObjectIdException
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 */
public class SearchObjectIdException extends SearcherException {

	private static final long serialVersionUID = 1602501396214379967L;

	/**
	 * 
	 */
	public SearchObjectIdException() {
		super("Blad obslugi identyfikatora obiektu.");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SearchObjectIdException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SearchObjectIdException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SearchObjectIdException(Throwable cause) {
		super(cause);
	}

}
