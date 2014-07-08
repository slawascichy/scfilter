package pl.slawas.filter.exceptions;

/**
 * 
 * QueryRequestException błąd utworzenia/obsługi requestu zapytania
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class QueryRequestException extends RuntimeException {

	private static final long serialVersionUID = 4402962767804201131L;

	/**
	 * 
	 */
	public QueryRequestException() {
		super("Blad obslugi wywolania zapytania");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueryRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public QueryRequestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QueryRequestException(Throwable cause) {
		super("Blad obslugi wywolania zapytania", cause);
	}

}
