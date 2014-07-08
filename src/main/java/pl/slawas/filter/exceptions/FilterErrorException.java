package pl.slawas.filter.exceptions;

/**
 * 
 * ExecuteSQLQueryException - błąd wykonania zapytania SQL
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public abstract class FilterErrorException extends Throwable {

	private static final long serialVersionUID = -6818199532152800874L;

	/**
	 * 
	 */
	protected FilterErrorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	protected FilterErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	protected FilterErrorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FilterErrorException(Throwable cause) {
		super(cause);
	}

}
