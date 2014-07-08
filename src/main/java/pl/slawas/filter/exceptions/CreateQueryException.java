package pl.slawas.filter.exceptions;

/**
 * 
 * CreateQueryException błąd podczas tworzenia zapytania
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class CreateQueryException extends RuntimeException {

	private static final long serialVersionUID = 4402962767804201131L;

	/**
	 * 
	 */
	public CreateQueryException() {
		super("Blad budowy zapytania");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CreateQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CreateQueryException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CreateQueryException(Throwable cause) {
		super("Blad budowy zapytania", cause);
	}

}
