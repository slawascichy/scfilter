package pl.slawas.filter.exceptions;

/**
 * 
 * IndexerErrorException - błąd wykonania operacji przez indekser-a
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
@SuppressWarnings("serial")
public abstract class IndexerErrorException extends Throwable {

	/**
	 * 
	 */
	protected IndexerErrorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	protected IndexerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	protected IndexerErrorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public IndexerErrorException(Throwable cause) {
		super(cause);
	}

}
