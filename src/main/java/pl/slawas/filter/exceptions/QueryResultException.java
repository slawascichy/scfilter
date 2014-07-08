package pl.slawas.filter.exceptions;

/**
 * 
 * QueryResultException błąd utworzenia występujący podczas użycia obiektu
 * wyniku zapytania
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class QueryResultException extends RuntimeException {

	private static final long serialVersionUID = 4402962767804201131L;

	/**
	 * 
	 */
	public QueryResultException() {
		super("Blad analizy wyniku zapyatania");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueryResultException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public QueryResultException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QueryResultException(Throwable cause) {
		super("Blad analizy wyniku zapyatania", cause);
	}

}
